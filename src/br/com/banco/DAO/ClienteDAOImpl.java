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
		
		String sql = "SELECT id,nome, email, isAtivo, saldo FROM cliente ORDER BY saldo DESC";
		
		try { 
			stmt = conn.prepareStatement(sql); 
			rs=stmt.executeQuery();
			while(rs.next()) {
				Cliente cliente = new Cliente(); 
				cliente.setNome(rs.getString("nome"));
				cliente.setId(rs.getInt("id"));
				cliente.setEmail(rs.getNString("email"));
				cliente.setIsAtivo(rs.getBoolean("isAtivo"));
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
		
		String sql = "SELCT id,nome,email,isAtivo, saldo FROM cliente WHERE nome=?"; 
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
			cliente.setIsAtivo(rs.getBoolean("isAtivo"));
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
		String sql = "INSERT INTO cliente (nome, email,senha, isAtivo, saldo)"
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
		
	public Boolean realizarLogin (String email, String senha) { 
		ResultSet rs = null; 
		PreparedStatement stmt =null; 
		String sql = "SELECT id, nome FROM cliente WHERE "
					 + "email=?"
					 + "AND senha =MD5(?)"; 
		try { 
			stmt =conn.prepareStatement(sql); 
			stmt.setString(1, email);
			stmt.setString(2, senha);
			rs=stmt.executeQuery(); 
			
			if (rs.next()) { 
				return true; 
			} else { 
				return false; 
			}
		} catch (Exception e) { 
			System.out.println("Erro na autenticação");
			e.printStackTrace();
			return false; 
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt); 
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro:" + ex.getMessage());
			}
		}
				
		}
	
	public void fecharConta (int id) { 
		PreparedStatement stmt =null; 
		
		String sql= "UPDATE usuario SET isAtivo='false' AND SET saldo=0" 
				+ "WHERE id=?"; 
		
		try { 
			stmt=conn.prepareStatement(sql); 
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException ex) { 
			System.out.println("Problemas na DAO ao excluir produto!");
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
	public List<Object> listarAtivos() {
		List <Object> lista = new ArrayList<Object>(); //Lista que vai retornar 
		PreparedStatement stmt =null;  //Objeto criado 
		ResultSet rs = null; //Objeto criado 
		
		String sql = "SELECT id, nome, email, isAtivo, saldo FROM cliente WHERE isAtivo=true ORDER BY nome"; //Var para armazenar o select que vais er executado no banco 
		
		try { 
			stmt = conn.prepareStatement(sql);  //aqui o objetivo é transformar o sql em algo que relamwente vai ser lido pelo banco, uma sql executável, é isso que o prepareStatemet faz
			rs = stmt.executeQuery(); //esse aqui é o que vai lá no banco e realmente executa, ele vais er armazenado aqui no rs 
			while(rs.next()) { //enquanto houver linha nessa tabela ele vai pulando 
				Cliente cliente = new Cliente(); //Crio um novo produto lá da classe produto 
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setEmail(rs.getString("email"));
				cliente.setIsAtivo(rs.getBoolean("isAtivo"));
				cliente.setSaldo(rs.getDouble("saldo"));


				lista.add(cliente);
			}
			
		} catch (SQLException ex) {
			System.out.println("Problemas na DAO ao listar usuário! Erro:" + ex.getMessage());
			ex.printStackTrace();
		} finally { 
			try { 
				ConnectionFactory.closeConnection(conn, stmt, rs);
			} catch (Exception ex) { 
				System.out.println("Problemas ao fechar conexão! Erro:" + ex.getMessage());
			}
		}
		
		return lista; 
	}


	}


	


