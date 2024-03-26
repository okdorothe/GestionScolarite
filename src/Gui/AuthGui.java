package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Color;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Dao.AuthDAO;

import java.awt.CardLayout;
import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AuthGui {

	private JFrame frame;
	private JTextField iidd;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AuthGui window = new AuthGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AuthGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// Création de la fenêtre principale
		frame = new JFrame();
		frame.setFont(new Font("Calibri", Font.BOLD, 12));
		frame.setTitle("Authentification");
		frame.setType(Type.POPUP);
		frame.setBackground(new Color(64, 128, 128));
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 13));
		frame.getContentPane().setLayout(null);
		
		// Création d'un panneau pour le formulaire de connexion
		Panel panel = new Panel();
		panel.setBackground(new Color(0, 128, 128));
		panel.setBounds(82, 39, 280, 199);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		// Ajout du champ pour l'identifiant
		iidd = new JTextField();
		iidd.setBounds(136, 39, 122, 20);
		panel.add(iidd);
		iidd.setColumns(10);
		
		// Ajout d'un label pour le champ d'identifiant
		JLabel identifiant = new JLabel("Identifiant");
		identifiant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		identifiant.setBounds(10, 42, 86, 14);
		panel.add(identifiant);
		
		// Ajout d'un label pour le champ de mot de passe
		JLabel mdp = new JLabel("Mot de passe");
		mdp.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		mdp.setBounds(10, 71, 105, 20);
		panel.add(mdp);
		
		// Bouton pour la connexion
		JButton connexion = new JButton("Connexion");
		connexion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuthDAO th = new AuthDAO();
				boolean b=false;
				char[] mdpS = passwordField.getPassword();
				ArrayList<Object[]> list =new ArrayList<>();
				list = th.affEtu();
				// Recherche de l'utilisateur dans la base de données
				for(Object[] ob: list) {
					// verifie si c'est un etudiant
					if(iidd.getText().equals((String)ob[0])&&Arrays.equals(mdpS, ((String) ob[1]).toCharArray())&&((BigDecimal) ob[2]).intValue()==4) {
						AccEtuGui etu = new AccEtuGui(iidd.getText(),((BigDecimal) ob[3]).intValue());
						frame.setVisible(false);
						etu.show();
						b=true;
						break;
					}
					// verifie si Enseigant
					else if(iidd.getText().equals((String)ob[0])&&Arrays.equals(mdpS, ((String) ob[1]).toCharArray())&&((BigDecimal) ob[2]).intValue()==5) {
						AccEnseignGui ens = new AccEnseignGui(iidd.getText());
						frame.setVisible(false);
						ens.show();
						b=true;
						break;
					}
					// verifie si gestionnaire
					else if(iidd.getText().equals("admin") && Arrays.equals(mdpS, "admin".toCharArray())){
						AccGestionGui ges = new AccGestionGui();
						frame.setVisible(false);
						ges.show();
						b=true;
						break;
					}
				
				}
				if(b==false) {
					JOptionPane.showMessageDialog(null, "Identifiant ou mot de passe incorrecte");
				}
			}
		});
		connexion.setFont(new Font("Times New Roman", Font.BOLD, 15));
		connexion.setBackground(new Color(64, 128, 128));
		connexion.setBounds(71, 120, 105, 23);
		panel.add(connexion);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(136, 70, 122, 20);
		panel.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("R.jpg"));
		lblNewLabel.setBounds(10, 0, 135, 33);
		frame.getContentPane().add(lblNewLabel);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void show() {
		frame.setVisible(true);
	}
}
