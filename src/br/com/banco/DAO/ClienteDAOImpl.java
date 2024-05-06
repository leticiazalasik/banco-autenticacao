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
		Cliente cliente = (Cliente) object; 
		PreparedStatement stmt =null; 
		String sql = "INSERT INTO cliente (nome, email,senha, isativo, saldo)"
				+"VALUES (?,?,MD5(?),?,?)"; 
		
		try { 
			stmt=conn.prepareStatement(sql); 
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getSenha());
			stmt.setBoolean(4, cliente.getIsAtivo());
			stmt.setDouble(5, cliente.getSaldo());

			stmt.execute();
			return true;  
		} catch (SQLException ex) { 
			System.out.println("Problemas ao cadastrar cliente.");
			ex.printStackTrace();
			return false; 
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt);
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão.");
				ex.printStackTrace();
				
			}
		}
	}

	@Override
	public Boolean alterar(Object object) {
		Cliente cliente = (Cliente) object; 
		PreparedStatement stmt = null; 
		String sql = "UPDATE cliente SET nome=?, email=?, senha=?,istativo=?, saldo=? WHERE id=? "; 
		
		try { 
			stmt=conn.prepareStatement(sql); 
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getEmail());
			stmt.setString(3, cliente.getSenha());
			stmt.setBoolean(4, cliente.getIsAtivo());
			stmt.setDouble(5, cliente.getSaldo());

			stmt.execute();
			return true;  
			
		} catch (SQLException ex) { 
			System.out.println("Problemas na DAO ao cadastrar cliente.");
			ex.printStackTrace();
			return false; 
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt);
			} catch (Exception ex) {
				System.out.println("Problemas ao fechar conexão");
				ex.printStackTrace();
			}
		}
	}

	@Override
	public void excluir (String nome) {
		PreparedStatement stmt = null; 
		String sql = "DELETE FROM cliente WHERE nome=?"; 
		
		try { 
			stmt=conn.prepareStatement(sql);
			stmt.setString(1, nome);
			stmt.executeUpdate();
		} catch (SQLException ex) { 
			System.out.println("Problemas na DAO ao excluir cliente.");
			ex.printStackTrace();
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt);
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
			}
		
	public Boolean realizar
	
	
	
	
	}

