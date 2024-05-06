package br.com.banco.DAO;

import java.util.List;

public interface GenericDAO {

	public List<Object>listarTodos(); 
	public Object listarPorNome(String nome); 
	public Boolean cadastrar (Object object); 
	public Boolean alterar (Object object); 
	public void excluir (String id); 
	
}
