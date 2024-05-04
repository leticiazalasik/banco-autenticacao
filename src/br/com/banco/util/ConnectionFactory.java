package br.com.banco.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {

	
		public static final String PATH = "jdbc:postgresql://localhost:5432/db_banco"; // Endereço do banco de dados, jdbc é
																						// // o driver, o tipo postgresql,
																						// onde
		// vai se localizar localhost que é o local a porta do banco onde está sendo
		// executado e db_adas é o nome da base de dados

		public static final String USER = "postgres"; // o nome do usuario
		public static final String PASSWORD = "2149"; // e a senha para conectar java e banco

		/**
		 * Método responsavel por abrir conexão com banco
		 * 
		 * @return retorna objeto da classe conection
		 */
		public static Connection getConnection() throws Exception { // o tipo desse método é do tipo Connection aqui entao
																	// vou retornar um objeto da classe connection

			try { // o que pode gerar erro: nesse caso o driverManager vai lá no banco e tenta
					// validar a conexao e passo as credenciais necessarias
				Class.forName("org.postgresql.Driver");
				return DriverManager.getConnection(PATH, USER, PASSWORD);
			} catch (Exception e) { // se dá errado lancamos o erro
				throw new Exception(e.getMessage()); // Aqui pode ainda ficar um erro, clique na dica e ele vai add o throws
														// Exception

			}
		}

		public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
			close(conn, stmt, rs);
		}

		public static void closeConnection(Connection conn, Statement stmt) throws Exception {
			close(conn, stmt, null);
		}

		public static void closeConnection(Connection conn) throws Exception {
			close(conn, null, null);
		}

	//Método reposável por fechar a conexão de um dos objetos por parâmetro 
		private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
			try {
				if (rs != null) {
					rs.close();
				}

				if (stmt != null) {
					stmt.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		}
	}
