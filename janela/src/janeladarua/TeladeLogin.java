package janeladarua;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import janeladarua.ConectaBDProjeto;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class TeladeLogin {

	private JFrame telaloginUser;
	private JTextField txtUser;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeladeLogin window = new TeladeLogin();
					window.telaloginUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeladeLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telaloginUser = new JFrame();
		telaloginUser.getContentPane().setBackground(new Color(119, 136, 153));
		telaloginUser.setTitle("Login do Usuario - Igor Michels");
		telaloginUser.setBounds(100, 100, 450, 300);
		telaloginUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaloginUser.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(186, 21, 46, 14);
		telaloginUser.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User:");
		lblNewLabel_1.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(35, 78, 46, 14);
		telaloginUser.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Senha:");
		lblNewLabel_2.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(23, 129, 46, 14);
		telaloginUser.getContentPane().add(lblNewLabel_2);
		
		txtUser = new JTextField();
		txtUser.setBounds(74, 75, 86, 20);
		telaloginUser.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUser.setText(null);
				txtSenha.setText(null);
			}
		});
		btnLimpar.setBounds(281, 74, 89, 23);
		telaloginUser.getContentPane().add(btnLimpar);
		
		JButton btnAcessar = new JButton("Acessar");
		btnAcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = ConectaBDProjeto.conecta();
					String sql = ("select * from loginusuario where usuario=? and senha=?");
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, txtUser.getText());
					//stmt.setString(2, txtSenha.getText());
					stmt.setString(2, new String (txtSenha.getPassword()));
					
					ResultSet rs = stmt.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Acesso liberado");
						TeladeCadastro info = new TeladeCadastro();
						TeladeCadastro.main(null);
						telaloginUser.setVisible(false);
						//setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Acesso não liberado");
					}
					stmt.close();
					con.close();
					
				}catch (SQLException e1) {
					e1.printStackTrace();
				}
						
				
				}

			private void setVisible(boolean b) {
				// TODO Auto-generated method stub
				
			}
			});
				
				/*String senha = txtSenha.getText();
				String user = txtUser.getText();
				
				if(senha.contains("123") && user.contains("Igaum")){
					JOptionPane.showMessageDialog(null, "Seja bem-vindo");
					TeladeCadastro info = new TeladeCadastro();
					TeladeCadastro.main(null);
				}else {
					JOptionPane.showMessageDialog(null, "Dados incorretos");
				}	
			}
		});*/
		btnAcessar.setBounds(281, 108, 89, 23);
		telaloginUser.getContentPane().add(btnAcessar);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		btnSair.setBounds(281, 142, 89, 23);
		telaloginUser.getContentPane().add(btnSair);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(74, 125, 86, 20);
		telaloginUser.getContentPane().add(txtSenha);
		
		/*JLabel lblNewLabel_3 = new JLabel("User: Igaum");
		lblNewLabel_3.setBounds(74, 187, 86, 14);
		frmLoginDoUsuario.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Senha: 123");
		lblNewLabel_4.setBounds(74, 203, 86, 14);
		frmLoginDoUsuario.getContentPane().add(lblNewLabel_4);*/
	}
}
