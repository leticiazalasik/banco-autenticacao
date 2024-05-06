package br.com.banco.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.banco.DAO.ClienteDAOImpl;
import br.com.banco.DAO.GenericDAO;
import br.com.banco.model.Cliente;

public class ClienteController {

	public List <Cliente> listarTodos() {
		List <Cliente>listaUsuarios = new ArrayList<Cliente>(); 
		try { 
			GenericDAO dao = new ClienteDAOImpl(); 
			
			for (Object object: dao.listarTodos()) {
				listaUsuarios.add((Cliente)object); 
			}
		} catch (Exception e) { 
			System.out.println("Erro na controller ao listar clientes.");
			e.printStackTrace();
		}
		return listaUsuarios; 
	}
	
	public Cliente listarPorNome (String nome) { 
		try { 
			GenericDAO dao = new ClienteDAOImpl(); 
			Cliente cliente = (Cliente) dao.listarPorNome(nome); 
			return cliente; 
		} catch (Exception e) { 
			System.out.println("Erro na controller ao listar cliente por nome.");
			e.printStackTrace();
			return null; 
		}
	}
	
	public void cadastrar (Cliente cliente) { 
		
		try { 
			GenericDAO dao = new ClienteDAOImpl(); 
			if (dao.cadastrar(cliente)) { 
				JOptionPane.showMessageDialog(null, "Usuário casdatrado com sucesso!");
			} else { 
				JOptionPane.showMessageDialog(null, "Problemas ao cadastrar usuário.");
			}
		} catch (Exception e ) { 
			System.out.println("Erro na controller ao cadastrar usuário");
			e.printStackTrace();
		}
		
	} 
	
	public Cliente listarPorId(int id) { 
		try { 
			ClienteDAOImpl dao = new ClienteDAOImpl();
			
			Cliente cliente = (Cliente) dao.listarPorId(id); 
			return cliente;
		} catch (Exception e) { 
			System.out.println("Erro na controller ao listar Cliente por id.");
			e.printStackTrace();
			return null; 
		}
	}
		
		public boolean validarId (int id) { 
			try { 
				ClienteDAOImpl dao = new ClienteDAOImpl();
				Cliente cliente = (Cliente) dao.listarPorId(id); 
				
				if (cliente==null) {
					return false; 
			} else { 
				return true; 
			}
			} catch (Exception e) { 
				System.out.println("Erro na controller ao validar o usuario.");
				return false; 
			}	
		}
		
		public boolean excluir (int id) { 
			try { 
				GenericDAO dao = new ClienteDAOImpl();
				if (!validarId(id)) { 
					System.out.println("Id inválido. Não é possível ecluir o usuário. ");
					return false; 
			}
				
				dao.excluir(id);
				return true; 
		} catch (Exception e) { 
			e.printStackTrace();
			return false; 
		}
		}

	
	
	
}
