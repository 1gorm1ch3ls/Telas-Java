package janeladarua;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import java.awt.Font;

public class TeladeCadastro {

	private JFrame telacadGasto;
	private JTextField txtValor;
	private JTextField txtData;
	private JTextField txtGasto;
	private JTextField txtPag;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeladeCadastro window = new TeladeCadastro();
					window.telacadGasto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeladeCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		telacadGasto = new JFrame();
		telacadGasto.getContentPane().setBackground(new Color(119, 136, 153));
		telacadGasto.setBackground(new Color(255, 255, 255));
		telacadGasto.setTitle("Cadastro de Gasto - Igor Michels");
		telacadGasto.setBounds(100, 100, 450, 300);
		telacadGasto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telacadGasto.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cadastro de Gasto");
		lblNewLabel.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(155, 11, 125, 14);
		telacadGasto.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo de Gasto:");
		lblNewLabel_1.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(34, 46, 120, 14);
		telacadGasto.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo de Pagamento:");
		lblNewLabel_2.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(34, 85, 134, 14);
		telacadGasto.getContentPane().add(lblNewLabel_2);
		
		JButton btnCadastro = new JButton("Cadastrar");
		btnCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String tipodeGasto = txtGasto.getText();
				String tipodePag = txtPag.getText();
				String valor = txtValor.getText();
				String data = txtData.getText();
				
				if(tipodeGasto.isEmpty() || tipodePag.isEmpty() || data.isEmpty() || valor.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Campo(s) nao preenchido(s)","ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
					
					try {
						Connection con = ConectaBDProjeto.conecta();
						String sql = "insert into cadastrogasto(tipodeGasto, tipodePag, valor, dataPag) value (?, ?, ?, ?)";
						PreparedStatement stmt = con.prepareStatement(sql);
						stmt.setString(1, txtGasto.getText());
						stmt.setString(2, txtPag.getText());
						stmt.setFloat(3, Float.parseFloat(txtValor.getText()));
						stmt.setString(4, txtData.getText());
							
						stmt.execute();
						stmt.close();
						con.close();
						
						JOptionPane.showMessageDialog(null, "Gasto cadastrado");
						
						txtGasto.setText(null);
						txtPag.setText(null);
						txtValor.setText(null);
						txtData.setText(null);

					}catch (SQLException e1){
						e1.printStackTrace();
					}
					
					//var preco = Float.parseFloat(valor);
				}
			}
		});
		btnCadastro.setBounds(176, 199, 113, 23);
		telacadGasto.getContentPane().add(btnCadastro);
		
		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TeladeLogin voltar = new TeladeLogin();
				TeladeLogin.main(null);
				telacadGasto.setVisible(false);
			}
		});
		btnSair.setBounds(65, 199, 89, 23);
		telacadGasto.getContentPane().add(btnSair);
		
		txtValor = new JTextField();
		txtValor.setBounds(82, 117, 86, 20);
		telacadGasto.getContentPane().add(txtValor);
		txtValor.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Valor:");
		lblNewLabel_3.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(34, 120, 46, 14);
		telacadGasto.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Data:");
		lblNewLabel_4.setFont(new Font("Lucida Console", Font.PLAIN, 11));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(34, 155, 46, 14);
		telacadGasto.getContentPane().add(lblNewLabel_4);
		
		txtData = new JTextField();
		txtData.setBounds(82, 152, 86, 20);
		telacadGasto.getContentPane().add(txtData);
		txtData.setColumns(10);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtGasto.setText(null);
				txtPag.setText(null);
				txtValor.setText(null);
				txtData.setText(null);
			}
		});
		btnLimpar.setBounds(306, 199, 89, 23);
		telacadGasto.getContentPane().add(btnLimpar);
		
		txtGasto = new JTextField();
		txtGasto.setBounds(155, 42, 113, 20);
		telacadGasto.getContentPane().add(txtGasto);
		txtGasto.setColumns(10);
		
		txtPag = new JTextField();
		txtPag.setBounds(176, 81, 104, 20);
		telacadGasto.getContentPane().add(txtPag);
		txtPag.setColumns(10);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
