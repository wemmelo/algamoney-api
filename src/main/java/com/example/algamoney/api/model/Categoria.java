package com.example.algamoney.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


//Essa é a classe responsável por criar o meu objeto categoria

/*
 * A anotação @Entity torna a nossa classe como uma entidade de banco de dados.
 */
@Entity
@Table(name = "categoria")//Aqui eu coloco o nome da tabela
public class Categoria {
	
	//como essa variavél codigo vai receber o id eu preciso anotar com @Id
	// (ou seja, a chave primária no banco de dados)
	@Id
	//a linha abaixo faz com que o código seja autogeravel
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	//essas duas linhas abaixo são o mapeamento das colunas
	private Long codigo;
	@NotNull
	@Size(min = 3, max = 20)
	private String nome;
	
	
	//gerar gets and setters com o ggas
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	//gerar o hashCode e equals ghca, só em cima do código
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
	

}
