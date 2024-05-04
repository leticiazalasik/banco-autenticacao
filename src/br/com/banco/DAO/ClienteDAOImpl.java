package br.com.banco.DAO;

import java.sql.Connection;
import java.util.List;

import br.com.banco.util.ConnectionFactory;

public class ClienteDAOImpl implements GenericDAO{
	
	private Connection conn; 
	
	public ClienteDAOImpl() throws Exception { 
		try {
			this.conn=ConnectionFactory.getConnection();
			System.out.println("Conectado com sucesso");
		}catch (Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public List<Object> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object listarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean cadastrar(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean alterar(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void excluir(int id) {
		// TODO Auto-generated method stub
		
	}

}
