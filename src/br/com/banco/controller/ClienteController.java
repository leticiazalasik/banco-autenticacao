package br.com.banco.controller;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	
	
}
