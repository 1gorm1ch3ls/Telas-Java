package janeladarua;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
		
	public class ConectaBDProjeto {

		public static Connection conecta() throws SQLException{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				return DriverManager.getConnection("jdbc:mysql://localhost/projeto", "root", "");
			}catch (ClassNotFoundException e) {
				throw new SQLException(e.getException());
			}
		
		}

	}
