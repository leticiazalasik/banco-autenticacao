package br.com.bancomain;

import javax.swing.JOptionPane;

import br.com.banco.controller.ClienteController;
import br.com.banco.model.Cliente;

public class Main {

	public static void main(String[] args) {

		String menu=" "
				.concat(" Banco X - MENU: \n")
				.concat("1) Cadastrar novo cliente\n")
				.concat("2) Editar cliente\n")
				.concat("3) Desativar conta\n")
				.concat("4) Excluir cliente da base\n")
				.concat("5) Fazer login do cliente\n")
				.concat("6) Listar todos os clientes\n")
				.concat("7) Listar clientes ativos\n")
				.concat("8) Buscar cliente por nome\n")
				.concat("Buscar cliente por código id\n")
				.concat("10) Finalizar o sistema\n")
				.concat("Digite a opção desejada: \n");

		String opcao = JOptionPane.showInputDialog(menu);
		int opcaoMenu = Integer.parseInt(opcao); 
		
		Cliente novoCliente = new Cliente(); 
		ClienteController controller = new ClienteController(); 
		
		while (opcaoMenu!=0) {
			
			switch (opcaoMenu) { 
			
			case 1: 
			//Cadastrar 
				
			novoCliente.set
			}
			
			
			
			
		}
		
		
		
		
		
		
		
		
	}

}
