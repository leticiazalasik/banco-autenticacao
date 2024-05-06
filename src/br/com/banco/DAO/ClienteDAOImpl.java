package br.com.banco.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.banco.model.Cliente;
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
		List<Object>lista =new ArrayList<Object>();
		PreparedStatement stmt = null; 
		ResultSet rs = null; 
		
		String sql = "SELECT id,nome, email, isativo, saldo FROM cliente ORDER BY saldo DESC";
		
		try { 
			stmt = conn.prepareStatement(sql); 
			rs=stmt.executeQuery();
			while(rs.next()) {
				Cliente cliente = new Cliente(); 
				cliente.setNome(rs.getString("nome"));
				cliente.setId(rs.getInt("id"));
				cliente.setEmail(rs.getNString("email"));
				cliente.setIsAtivo(rs.getBoolean("isativo"));
				cliente.setSaldo(rs.getDouble("saldo"));
				
				lista.add(cliente); 
			}
		} catch (SQLException ex) { 
			System.out.println("Problemas na DAO ao listar usuário");
			ex.printStackTrace();
		} finally { 
			try {
				ConnectionFactory.closeConnection(conn, stmt, rs);
				
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar a DAO");
			}
		}
		
		return lista; 
	}

	@Override
	public Object listarPorNome(String nome) {
		PreparedStatement stmt =null; 
		Cliente cliente =null; 
		ResultSet rs = null; 
		
		String sql = "SELCT id,nome,email,isativo, saldo FROM cliente WHERE nome=?"; 
	try { 
		stmt=conn.prepareStatement(sql); 
		stmt.setString(1, nome);
		stmt.executeQuery(); 
		rs= stmt.executeQuery(); 
		
		if (rs.next()) {
	      JOptionPane.showMessageDialog(null, "Usuário localizado!");

			cliente = new Cliente(); 
			cliente.setNome(rs.getString("nome"));
			cliente.setId(rs.getInt("id"));
			cliente.setEmail(rs.getNString("email"));
			cliente.setIsAtivo(rs.getBoolean("isativo"));
			cliente.setSaldo(rs.getDouble("saldo"));
		}
	} catch (SQLException ex) {
		System.out.println("Problemas na DAO ao exibir usuário por nome");
		ex.printStackTrace();
	} finally { 
		try { 
			ConnectionFactory.closeConnection(conn, stmt, rs);
		} catch (Exception ex) { 
			System.out.println("Problemas ao fechar conexão.");
		}
	}
	return cliente; 
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
