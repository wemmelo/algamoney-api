/*
 * Essa classe é gerada pela própria IDE e serve para iniciar a aplicação
 */
package com.example.algamoney.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Essa anotação abaixo, informa que a minha aplicação é uma aplicação com SpringBoot
 * E dentro dessa anotação é possível verificar todas as configurações e automatizações
 * realizadas por estar utilizando o Spring boot.
 * A anotação também define o ponto de partida para a procura dos demais
 * componentes da aplicação, ou seja, todas as classes dos pacotes descendentes
 * de com.algaworks.api serão escaneadas e, se algum componente Spring for
 * encontrado, será gerenciado, e isso facilitará muito a nossa vida (você verá no
 * desenvolvimento da aplicação).
 */
@SpringBootApplication
public class AlgamoneyApiApplication {

	public static void main(String[] args) {
		/*
		 * Abaixo está sendo chamado o método run da classe SpringApplication
		 * Passando como parâmetro a própria classe e os argumentos possíveis de linha
		 * de comando
		 */
		SpringApplication.run(AlgamoneyApiApplication.class, args);
	}

}
