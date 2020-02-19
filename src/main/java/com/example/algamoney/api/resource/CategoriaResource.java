package com.example.algamoney.api.resource;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.algamoney.api.event.RecursoCriadoEvent;
import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

/*
 * Essa anotação é o que vai fazer essa classe se tornar um endpoint ou uma url válida
 * Assim quando eu marco a classe com o @RestController significa que ela já se tornou
 * um endpoint válido, mas agora eu preciso informar a qual URL o controller está associado
 * Essa notação serve para dizer que ela a classe é um componente Spring e que é um controller.
 */
@RestController
/*
 * Essa segunda anotação é a que vai associar a URL ao controller Assim esse
 * controler vai ficar na escuta pela url /
 */
@RequestMapping("/categorias")
/*
 * Portanto essa é a classe que vai usar o nosso recurso, ou seja, é o
 * controlador do nosso recurso
 */
public class CategoriaResource {

	@Autowired
	// Como aqui é um controlador eu não posso dar um new aqui para criar esse
	// objeto, então eu coloco o @Autowired
	// ele vai procurar uma implementação de CategoriaRepository e entregre em
	// categoriaRepository
	/*
	 * A anotação @Autowired significa dizer ao spring Procure a implementação da
	 * Classe CategoriaRepository e injete o objeto dessa classe em
	 * categoriaRepository
	 */
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	// com essa linha eu defino o ver http que esse controlador vai ouvir
	@GetMapping
	// Isso é uma entidade de resposta
	public List<Categoria> listar() {

		return categoriaRepository.findAll();
		// esse find.All já esta implementado pelo CategoriaReposiroty
		// ele vai buscar no BD um select * from categoria, ou seja,
		// ele entrega as categorias para o List que vai listar elas.

		/*
		 * Isso é uma entidade de resposta
		 * 
		 * public ResponseEntity<?> listar(){ List<Categoria> categorias =
		 * categoriaRepository.findAll(); return !categorias.isEmpty() ?
		 * ResponseEntity.ok(categorias) : ResponseEntity.noContent().build();
		 */

	}

	// agora eu vou fazer um método post para salvar a primeira categoria
	@PostMapping
	// @ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
		Categoria categoriaSalva = categoriaRepository.save(categoria);
		/*URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
		.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());
		return ResponseEntity.created(uri).body(categoriaSalva);*/
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}
	//Essa implementação retorna informações do código caso exista o mesmo exista
	/*@GetMapping("/{codigo}")
	public Categoria buscarPeloCodigo(@PathVariable Long codigo) {
		return this.categoriaRepository.findById(codigo).orElse(null);
	}*/
	
	/*Essa implementação é mais completa ue a anterior, pois no caso do código ser inválido ela retorna o not found*/
	/*@GetMapping("/{codigo}")
	public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
	  return this.categoriaRepository.findById(codigo)
	      .map(categoria -> ResponseEntity.ok(categoria))
	      .orElse(ResponseEntity.notFound().build());
	}*/
	//Mesma função da implementação a cima mas com o código diferente
	@GetMapping("/{codigo}")
	public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
	    Optional categoria = this.categoriaRepository.findById(codigo);
	    return categoria.isPresent() ? 
	            ResponseEntity.ok(categoria.get()) : ResponseEntity.notFound().build();
	}
	
	/* Implementação do método delete */
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		categoriaRepository.deleteById(codigo);
	}
}
