package Gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import Dao.AbsentDAO;
import Dao.FonctionsDAO;
import Dao.GroupeDAO;
import Dao.JustifiDAO;

import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.ImageIcon;
/**
 * Classe d'acceuil de l'etudiant
 * @author Do Ro The
 *
 */
public class AccEtuGui {

	private static final long serialVersionUID = 1L;
    private Timer timer;
    private int count = 0;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private byte[] pdfBytes;
/**
 * Constructeur AccEtuGui qui est la plateforme de l'etudiant
 * @param iden
 * @param groupe
 */
	public AccEtuGui(String iden,int groupe) {
		frame = new JFrame();
		frame.setBounds(300, 20, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout card = new CardLayout();
		JPanel panel = new JPanel(card);
		panel.setBounds(0, 101, 884, 515);
		frame.getContentPane().add(panel);
		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(255, 255, 255));
		JPanel panel2 = new JPanel();
		panel2.setBackground(new Color(255, 255, 255));
		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 255));
		Panel cal = new Panel();
		cal.setBounds(176, 72, 516, 163);
		card.show(panel, "empty");
		panel.add(panel3,"panel 3");
		panel3.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon("R.jpg"));
		lblNewLabel_6.setBounds(10, 11, 142, 34);
		panel3.add(lblNewLabel_6);
		AnimatedLabel an = new AnimatedLabel();
		//JLabel lblNewLabel_7 = new JLabel("POUR TROUVER SANS CHERCHER , ");
		an.setHorizontalAlignment(SwingConstants.CENTER);
		an.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
		an.setBounds(10, 229, 864, 49);
		panel3.add(an);
		
		
		panel.add(panel2,"panel 2");
		panel.add(panel1,"panel 1");
		panel1.setLayout(null);
		JCalendar calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(515, 162));
		cal.add(calendar);
		panel1.add(cal);
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(128, 128, 128));
		panel_1.setBounds(48, 248, 787, 341);
		panel1.add(panel_1);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\Do Ro The\\Pictures\\R.jpg"));
		lblNewLabel_4.setBounds(10, 11, 135, 28);
		panel1.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("PLANNING");
		lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(339, 11, 123, 28);
		panel1.add(lblNewLabel_5);
		
		/*JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(60, 174, 787, 341);
		//panel1.add(panel_2);*/
		
		panel2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 53, 894, 522);
		panel2.add(panel_3);
		panel_3.setLayout(null);
		
		// initialisation d'un Jmenu
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		
		//Faire appel au menu de planning en cliquant sur le sous-menu planning
		JMenu mnNewMenu = new JMenu("Planning");
		JMenuItem pl = new JMenuItem("Afficher Planning");
		pl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"panel 1");
			}
		});
		mnNewMenu.add(pl);
		menuBar.add(mnNewMenu);
		
		//Faire appel au menu de absence en cliquant sur le sous-menu absence
		JMenu mnNewMenu_1 = new JMenu("ABSENCES");
		JMenuItem p2 = new JMenuItem("Afficher Absences");
		p2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(panel,"panel 2");
			}
		});
		menuBar.add(mnNewMenu_1);
		mnNewMenu_1.add(p2);
		
		// Se deconnecter
		JMenu menu5 = new JMenu("Deconnexion");
	      JMenuItem sm5 = new JMenuItem("Deconnexion");
	      sm5.setHorizontalAlignment(SwingConstants.LEFT);
	      sm5.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	AuthGui au = new AuthGui();
	        	au.show();
	        	frame.setVisible(false);
	         }
	      });
	      menu5.add(sm5);
		menuBar.add(menu5);
		
		/**
		 * Permet d'afficher le planning selon la date choisie
		 */
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        if ("calendar".equals(evt.getPropertyName())) {
		        	panel_1.removeAll();
		        	panel_1.validate();
		            Date date = calendar.getDate();
		            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		            String a= sdf.format(calendar.getDate());//changer le format de la date du celendar
		            java.sql.Date sqlDate = null;
		            /**
		             * convertir  a de util.date en sql.date
		             */
		            try {
						java.util.Date d = sdf.parse(a);
						sqlDate = new java.sql.Date(d.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            
		            ModeleStatique md = new ModeleStatique();
		             
		        	ArrayList<Object[]> list = new ArrayList<>();//liste d'objet contenue les seances à afficher
		            list = md.getDataS(sqlDate,groupe);
		            
		            if(!(list.isEmpty()))
		            {
		            	//JOptionPane.showMessageDialog(null, (String) list.get(0)[5]);
		            	panel_1.setLayout(new GridLayout(list.size(),1));
		             // Ajout d'un JPanel pour chaque cours, contenant les informations dans des JLabel
		            for(Object[] o : list) {
		            	    JPanel coursePanel = new JPanel(new GridLayout(1, 7));
		            	    JButton b = new JButton("Lien");
		            	    panel_1.add(coursePanel);
		            		coursePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY)); // Ajout d'une bordure en bas de chaque panel
		            		coursePanel.setBackground(Color.WHITE);
		            		JLabel nameLabel = new JLabel((String) o[0]);
		            		nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 0)); // Ajout d'une marge gauche pour le JLabel
		            		//nameLabel.setPreferredSize(new Dimension(15,15));
		            		coursePanel.add(nameLabel);
		            		coursePanel.add(new JLabel((String) o[1]));
		                    coursePanel.add(new JLabel((String) o[2]));
		                    coursePanel.add(new JLabel((String) o[3]));
		                    coursePanel.add(new JLabel((String) o[4]));
		                    coursePanel.add(new JLabel((String) o[5]));
		                    coursePanel.add(b); // bouton permettant d'afficher le lien pour suivre le cours en ligne 
		                    b.addActionListener(new ActionListener() {
		            			public void actionPerformed(ActionEvent e) {
		            				JOptionPane.showMessageDialog(null, "");
		            			
		            			}
		            		});
		            }
		            
		            }else {
		            	panel_1.removeAll();
		            	panel_1.repaint();
		            }

		        }
		    }
		});
		
		/**
		 * ici, on affiche tous les absences d'un étudiants dans untableau
		 */
		    FonctionsDAO fn = new FonctionsDAO();
		    ModeleStatique model1 = new ModeleStatique();
	  		DefaultTableModel table1 = new DefaultTableModel(model1.getDataAbs(fn.recpEtu(iden)).stream().toArray(Object[][]:: new),model1.getColumnsAbs());
	  		JTable source1 = new JTable(table1){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
	  		JScrollPane Spane1= new JScrollPane(source1);
	  		Spane1.setBounds(40,50,800,150);
	  		panel_3.add(Spane1);
	  		
	  		textField = new JTextField();
	  		textField.setHorizontalAlignment(SwingConstants.CENTER);
	  		textField.setFont(new Font("Times New Roman", Font.BOLD, 16));
	  		textField.setBounds(540, 260, 140, 35);
	  		panel_3.add(textField);
	  		textField.setColumns(10);
	  		
	  		JLabel lblNewLabel = new JLabel("Selectionner identifiant");
	  		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
	  		lblNewLabel.setBounds(269, 260, 245, 35);
	  		panel_3.add(lblNewLabel);
	  		
	  		textField_1 = new JTextField();
	  		textField_1.setBounds(249, 336, 204, 35);
	  		panel_3.add(textField_1);
	  		textField_1.setColumns(10);
	  		/**
	  		 * ici on choisis le fichier à déposer comme justificatif
	  		 */
	  		JButton btnNewButton = new JButton("Choisir fichier");
	  		btnNewButton.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				 JFileChooser fileChooser = new JFileChooser();  // Création d'un objet JFileChooser pour sélectionner un fichier
	  			// Affichage de la boîte de dialogue pour sélectionner un fichier et récupération de la réponse de l'utilisateur
	                 int returnValue = fileChooser.showOpenDialog(null);
	              // Si l'utilisateur a sélectionné un fichier et a cliqué sur "Ouvrir"
	                 if (returnValue == JFileChooser.APPROVE_OPTION) {
	                	 // Récupération du fichier sélectionné
	                     File selectedFile = fileChooser.getSelectedFile();
	                     // Affichage du nom du fichier dans un JTextField nommé textField_1
	                     textField_1.setText(selectedFile.getName());
	                     try {
	                    	// Lecture de toutes les données du fichier et stockage dans un tableau de bytes nommé pdfBytes
							pdfBytes = Files.readAllBytes(selectedFile.toPath());
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                 }
	  			}
	  		});
	  		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
	  		btnNewButton.setBounds(447, 335, 233, 36);
	  		panel_3.add(btnNewButton);
	  		
	  		/**
	  		 * Sommettre les informations des absents
	  		 */
	  		JButton btnNewButton_1 = new JButton("Soumettre");
	  		btnNewButton_1.addActionListener(new ActionListener() {
	  			public void actionPerformed(ActionEvent e) {
	  				JustifiDAO j = new JustifiDAO();
	  				try {
  					if(textField_1.getText().contains("pdf")) {
	  					j.add(textField_1.getText(), pdfBytes, Integer.parseInt(textField.getText()));
	  					JOptionPane.showMessageDialog(null, "Ajouter avec succès");
	  				}
	  				else {
	  					JOptionPane.showMessageDialog(null, "Ajoutez que des pdfs svp");
	  				}
	  				}catch(Exception e3) {
	  					if(e3.getMessage().contains("For input string"))
	  					{
	  					JOptionPane.showMessageDialog(null, "Mettez l'id de l'absence que vous voulez justifiez");
	  					}
	  				}
	  				
	  			} 
	  		});
	  		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
	  		btnNewButton_1.setBounds(373, 411, 151, 35);
	  		panel_3.add(btnNewButton_1);
	  		
	  		JLabel lblNewLabel_1 = new JLabel("");
	  		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Do Ro The\\Pictures\\R.jpg"));
	  		lblNewLabel_1.setBounds(24, 11, 130, 31);
	  		panel2.add(lblNewLabel_1);
	  		
	  		JLabel lblNewLabel_2 = new JLabel("2023 Copyright");
	  		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
	  		lblNewLabel_2.setBounds(375, 597, 151, 31);
	  		panel2.add(lblNewLabel_2);
	  		
	  		JLabel lblNewLabel_3 = new JLabel("ABSENCES");
	  		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
	  		lblNewLabel_3.setBounds(389, 11, 89, 31);
	  		panel2.add(lblNewLabel_3);
	  		
	}
	public void show() {
		frame.setVisible(true);
	}
}
