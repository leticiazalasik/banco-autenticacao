package br.com.bancomain;

import java.util.ArrayList;
import java.util.List;

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
				.concat("9) Buscar cliente por código id\n")
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
				
			novoCliente.setNome(JOptionPane.showInputDialog("Nome: "));
			novoCliente.setEmail(JOptionPane.showInputDialog("Email: "));
			novoCliente.setSenha(JOptionPane.showInputDialog("Senha: ")); 
			novoCliente.setIsAtivo(Boolean.valueOf(JOptionPane.showInputDialog("Ativar cliente?(true/false)")));
			novoCliente.setSaldo(Integer.valueOf(JOptionPane.showInputDialog("Saldo:"))); 
			
			controller.cadastrar(novoCliente);
			break; 
			
			case 2: 
				//Editar 
				
					Cliente clienteAlterado = new Cliente(); 
					
					int idModificar = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente a ser alterado: "));
					
					clienteAlterado.setNome(JOptionPane.showInputDialog("Nome: ")); 
					clienteAlterado.setEmail(JOptionPane.showInputDialog("Email: ")); 
					clienteAlterado.setSenha(JOptionPane.showInputDialog("Senha: "));
					
					String inputAlterado = JOptionPane.showInputDialog("Ativo/inativo: ");
					boolean isAtivoAlterado = "ativo".equalsIgnoreCase(inputAlterado);
					clienteAlterado.setIsAtivo(isAtivoAlterado);
					
					String inputSaldo = JOptionPane.showInputDialog("Digite o saldo: ");
					Double saldo = Double.parseDouble(inputSaldo);
					clienteAlterado.setSaldo(saldo);
		break; 

			case 6: 
			//Listar  todos
			
			Cliente cliente = new Cliente(); 
			List<Cliente>lista = new ArrayList<Cliente>(); 
			lista = controller.listarTodos(); 
			
			String mensagemLista = " "
			.concat("-- Lista de todos os clientes --\n"); 
			for (Cliente cliente2 :lista) { 
				mensagemLista=mensagemLista
				.concat("\n")
				.concat(String.valueOf(cliente2.getId()))
				.concat(" -")
				.concat(cliente2.getNome())
				.concat("\n Email: ")
				.concat(String.valueOf(cliente2.getEmail()))
				.concat("\n Cliente ativo? ")
				.concat(String.valueOf("Ativo? "+ (cliente2.getIsAtivo())))
				.concat("\n Saldo: R$ ")
				.concat(String.valueOf(cliente2.getSaldo()));
			}
			JOptionPane.showMessageDialog(null, mensagemLista);
			break; 
			
		case 3: 
		//Listar por id 
			
		int opcaoId =Integer.parseInt(JOptionPane.showInputDialog("Digite o id: "));
		
		Cliente clienteEncontrado = controller.listarPorId(opcaoId);
		
		if (clienteEncontrado!=null) { 
			String mensagemLista2=" "
					.concat("Id: ") 
					.concat(String.valueOf(clienteEncontrado.getId())) //concat só recebe string entao preciso converter 
					.concat("\n")
					.concat("Nome: ")
					.concat(clienteEncontrado.getNome())
					.concat("\n")
					.concat(String.valueOf("Ativo? "+ (clienteEncontrado.getIsAtivo()))); 
			
			JOptionPane.showMessageDialog(null, mensagemLista2);
		} else {
			JOptionPane.showMessageDialog(null, "Não existe produto com esse código na lista");
		}
		
		break; 
		
		case 4: 
		//Excluir 
		opcaoId = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente a ser excluído: "));
		
		if (controller.excluir(opcaoId)) { 
		JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");	
		} else { 
		controller.excluir(opcaoId);
		JOptionPane.showMessageDialog(null, "Erro ao excluir produto!"); 
		}
		break; 
		
		
		case 5: 
		//Autenticar/login 
		novoCliente.setEmail(JOptionPane.showInputDialog("E-mail: ")); 
		novoCliente.setSenha(JOptionPane.showInputDialog("Senha: ")); 
		
		controller.realizarLogin(novoCliente.getEmail(), novoCliente.getSenha()); 
		break; 
		
		case 7: 
		//Desativar Conta do cliente 
		int idDesativar = Integer.parseInt(JOptionPane.showInputDialog("Digite o id do cliente a ser desativado: ")); 
		
		novoCliente.setId(idDesativar); 
		controller.fecharConta(idDesativar); 
		break; 
		
		case 8: 
		//Listar por nome 
			String nomeBuscar = (JOptionPane.showInputDialog("Digite o id: "));
			
			clienteEncontrado = controller.listarPorNome(nomeBuscar);
			
			if (clienteEncontrado!=null) { 
				String mensagemLista2=" "
						.concat("Id: ") 
						.concat(String.valueOf(clienteEncontrado.getId())) //concat só recebe string entao preciso converter 
						.concat("\n")
						.concat("Nome: ")
						.concat(clienteEncontrado.getNome())
						.concat("\n")
						.concat(String.valueOf("Ativo? "+ (clienteEncontrado.getIsAtivo())))
						.concat(String.valueOf("Saldo: "+ (clienteEncontrado.getSaldo()))); 

				
				JOptionPane.showMessageDialog(null, mensagemLista2);
			} else {
				JOptionPane.showMessageDialog(null, "Não existe produto com esse código na lista");
			}
			
			break; 
			
		case 9: 
		//listar ativos
		lista=controller.listarAtivos(); 
		
		String mensagemListaAtivos=" "
				.concat("- Clientes ATIVOS no sistema: - ")
				.concat("\n");
		for  (Cliente cliente1:lista) {
			mensagemListaAtivos=mensagemListaAtivos
					.concat("\n")
					.concat(String.valueOf(cliente1.getId()))
					.concat("-")
					.concat(cliente1.getNome())
					.concat(String.valueOf(cliente1.getEmail()))
					.concat("\n")
					.concat(String.valueOf("Ativo? "+ (cliente1.getIsAtivo())))
					.concat("\n")
					.concat(String.valueOf(cliente1.getSaldo()))
					.concat("\n");
		}
		JOptionPane.showMessageDialog(null, mensagemListaAtivos); 
		break; 
			
		case 10: 
		JOptionPane.showMessageDialog(null, "Fechando o sistema...Sistema finalizado!");
		break; 
		
		default: 
			JOptionPane.showMessageDialog(null, "Opção digitada inexistente.\n Tente novamente!"); 
		break; 	
	}
			if (opcaoMenu!=10) { 
				opcao = JOptionPane.showInputDialog(menu);
				opcaoMenu = Integer.parseInt(opcao); 
				
			} else { 
				break; 
			}
	}
		
	}
}
