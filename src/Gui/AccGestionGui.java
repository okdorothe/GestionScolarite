package Gui;
import Dao.*;
import Model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.toedter.calendar.JCalendar;
/**
 * Interface de Gestionnaire 
 * @author Do Ro The
 *@version 1.0
 */
public class AccGestionGui {

	private JTextField hd;
	private JTextField hf;
	private JTextField textFieldjr;
	private JTextField idens;
	private JTextField mdpens;
	private JTextField nomens;
	private JTextField prenens;
	private JTextField telens;
	private JFrame frame;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField;
	private JTextField textField_1;
	JButton btnDelete = new JButton("DELETE");
JButton btnUpdate = new JButton("UPDATE");
JButton btnDelete1 = new JButton("DELETE");
JButton btnUpdate1= new JButton("UPDATE");
String selected,sl,sl1,sl2,sl3,sl4;
String selected1;
String temp1, temp2;
Panel panel_3 = new Panel();
Panel createEns = new Panel();
Panel afficherEns = new Panel();
int pq,temp3;

/**
 * Initialize the contents of the frame.
 */
public AccGestionGui() {
	frame = new JFrame();
	frame.setBounds(300, 20, 900, 700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().setLayout(null);
	CardLayout card = new CardLayout();
	JPanel panell = new JPanel(card);
	JPanel panelll = new JPanel();
	panell.setBounds(0, 102, 874, 517);
	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 900, 49);
	frame.getContentPane().add(menuBar);
	JTextArea textArea = new JTextArea();
	FonctionsDAO f = new FonctionsDAO();
	JTextField field1 = new JTextField();
	JTextField field2 = new JTextField();
	JTextField field3 = new JTextField();
	Panel afficherEtu = new Panel();
	Panel createEtu = new Panel();
	Panel createCours = new Panel();
	Panel createSeance = new Panel();
	Panel afficherJust = new Panel();
	Panel affJust = new Panel();
	createSeance.setBackground(new Color(192, 192, 192));
	panell.add(panelll,"empty");
	panell.add(createEtu,"createEtu");
	panell.add(afficherEtu,"afficherEtu");
	panell.add(createEns,"createEns");
	panell.add(afficherEns,"afficherEns");
	panell.add(createCours,"createCours");
	panell.add(createSeance,"createSeance");
	panell.add(afficherJust,"afficherJust");
	panell.add(affJust,"affJust");
	affJust.setLayout(null);
	
	JButton invalider = new JButton("Invalider");
	invalider.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Annulé 👎");
		}
	});
	invalider.setForeground(new Color(255, 255, 255));
	invalider.setBackground(new Color(255, 0, 0));
	invalider.setFont(new Font("Times New Roman", Font.BOLD, 14));
	invalider.setBounds(167, 469, 149, 37);
	affJust.add(invalider);
	
	JButton valider = new JButton("Valider");
	valider.setBackground(new Color(128, 255, 0));
	valider.setFont(new Font("Times New Roman", Font.BOLD, 14));
	valider.setBounds(542, 469, 149, 37);
	affJust.add(valider);
	afficherJust.setLayout(null);
	
	JLabel lblNewLabel_4 = new JLabel("Identifiant de l'étudiant");
	lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
	lblNewLabel_4.setBounds(62, 11, 197, 32);
	afficherJust.add(lblNewLabel_4);
	
	JLabel lblNewLabel_5 = new JLabel("Action");
	lblNewLabel_5.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
	lblNewLabel_5.setBounds(497, 11, 122, 32);
	afficherJust.add(lblNewLabel_5);
	
	
	card.show(panell,"empty");
	createSeance.setLayout(null);
	
	
	//Pour Gerer les sous menu d'etudiant
      JMenu menu1 = new JMenu("Gérer Etudiant");
      menu1.setHorizontalAlignment(SwingConstants.CENTER);
      menu1.setPreferredSize(new Dimension(100,10));;
      JMenuItem bouton1 = new JMenuItem("Ajouter etudiant");
      bouton1.setHorizontalAlignment(SwingConstants.LEFT);
      bouton1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 card.show(panell,"createEtu");
         }
      });
      JMenuItem bouton2 = new JMenuItem("Afficher etudiant");
      bouton2.setHorizontalAlignment(SwingConstants.LEFT);
      bouton2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 card.show(panell,"afficherEtu");
         }
      });
      menu1.add(bouton1);
      menu1.add(bouton2);//fin du menu etudiant
      
      //Menu Justificatifs
      JMenu menu4 = new JMenu("Justificatifs");
      JMenuItem bouton3 = new JMenuItem("Justificatifs d'absences");
      bouton3.setHorizontalAlignment(SwingConstants.LEFT);
      bouton3.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	        	 card.show(panell, "afficherJust");
	         }
	      });
      menu4.add(bouton3);
      JustifiDAO ju = new JustifiDAO();
      try {
    	  ArrayList<byte[]> l =new ArrayList<>();
    	  l=ju.afficher();
		  int j=72;
		  for (int i = 0; i < ju.getCompte(); i++) {
			  byte[] pdfBytes = l.get(i);
			  temp3 =i;
	    	  File pdfFile = new File("my_pdf_file.pdf");
	          Files.write(pdfFile.toPath(), pdfBytes);
			  PDDocument pdfDoc = Loader.loadPDF(pdfFile);
			  PDFRenderer renderer = new PDFRenderer(pdfDoc);
			  JLabel lblNewLabel_6 = new JLabel(ju.getList().get(i));
	  		lblNewLabel_6.setFont(new Font("Tahoma", Font.ITALIC, 18));
	  		lblNewLabel_6.setBounds(62, j, 172, 32);
	  		afficherJust.add(lblNewLabel_6);
	  		
	  		JButton btnNewButton_1 = new JButton("Afficher");
	  		btnNewButton_1.setFont(new Font("Times New Roman", Font.ITALIC, 17));
	  		btnNewButton_1.setBounds(495, j, 108, 32);
	  		afficherJust.add(btnNewButton_1);
	  		j=j+61;
	  		btnNewButton_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				JLabel label = null;
					try {
						label = new JLabel(new ImageIcon(renderer.renderImage(0)));
						JScrollPane scroll = new JScrollPane(label);
						scroll.setBounds(0, 0, 874, 387);
						//label.setBounds(0, 0, 800, 1000);
						affJust.add(scroll);
        				  pdfDoc.close();
        		          pdfFile.delete();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ArrayList<Integer> li = new ArrayList<>();
			    	li=ju.getListAbs();
					pq=li.get(temp3);
					card.show(panell, "affJust");
    			}
	  		 });
		  		
		  		valider.addActionListener(new ActionListener() {
		  			public void actionPerformed(ActionEvent e) {
		  				ju.update(pq);
		  				JOptionPane.showMessageDialog(null, "Validé ");
		  				afficherJust.remove(lblNewLabel_6);
		  				afficherJust.remove(btnNewButton_1);
		  			}
		  		});
	        }
		  
	} catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
      //fin du menu justificatifs
      
      // Menu enseignant
      JMenu menu2 = new JMenu("Gérer Enseignants");
      JMenuItem eMenu1 = new JMenuItem("Ajouter Enseignant");
      eMenu1.setHorizontalAlignment(SwingConstants.LEFT);
      eMenu1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 
        	 createEns.setBackground(new Color(64, 128, 128));
     		//createEns.setBounds(0, 102, 874, 517);
     		createEns.setLayout(null);
     		
     		JLabel lblNe = new JLabel("Identifiant");
     		lblNe.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNe.setBounds(214, 59, 103, 35);
     		createEns.add(lblNe);
     		
     		JLabel lblNew_1 = new JLabel("Mot de passe");
     		lblNew_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNew_1.setBounds(214, 105, 119, 35);
     		createEns.add(lblNew_1);
     		
     		JLabel lblNew_2 = new JLabel("Nom");
     		lblNew_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNew_2.setBounds(214, 151, 119, 29);
     		createEns.add(lblNew_2);
     		
     		JLabel lblNew_3 = new JLabel("Prenom");
     		lblNew_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNew_3.setBounds(214, 201, 119, 34);
     		createEns.add(lblNew_3);
     		
     		JLabel lblNew_4 = new JLabel("Téléphone");
     		lblNew_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNew_4.setBounds(214, 271, 119, 23);
     		createEns.add(lblNew_4);
     		
     		idens = new JTextField();
     		idens.setBounds(471, 66, 171, 20);
     		createEns.add(idens);
     		idens.setColumns(10);
     		
     		mdpens = new JTextField();
     		mdpens.setBounds(471, 112, 171, 20);
     		createEns.add(mdpens);
     		mdpens.setColumns(10);
     		
     		nomens = new JTextField();
     		nomens.setBounds(471, 155, 171, 20);
     		createEns.add(nomens);
     		nomens.setColumns(10);
     		
     		prenens = new JTextField();
     		prenens.setBounds(471, 208, 171, 20);
     		createEns.add(prenens);
     		prenens.setColumns(10);
     		
     		telens = new JTextField();
     		telens.setBounds(471, 272, 171, 20);
     		createEns.add(telens);
     		telens.setColumns(10);
     		
     		JButton ajoutEns = new JButton("Ajouter");
     		ajoutEns.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				EnseignantDAO enseign = new EnseignantDAO();
     				Enseignant ensi = new Enseignant(idens.getText(),mdpens.getText(),nomens.getText(),prenens.getText(),telens.getText(),5);
     				enseign.add(ensi);
     				JOptionPane.showMessageDialog(null, "Ajouter avec succes !!");
     			}
     		});
     		ajoutEns.setBackground(new Color(192, 192, 192));
     		ajoutEns.setFont(new Font("Tahoma", Font.BOLD, 14));
     		ajoutEns.setBounds(385, 358, 119, 44);
     		createEns.add(ajoutEns);
     		card.show(panell,"createEns");
         }
      });
      // Afficher les  enseignant
      JMenuItem eMenu2= new JMenuItem("Afficher Enseignant");
      eMenu2.setHorizontalAlignment(SwingConstants.LEFT);
      eMenu2.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 afficherEns.setLayout(null);
         	 ModeleStatique model1 = new ModeleStatique();
        	DefaultTableModel table1 = new DefaultTableModel(model1.getDataEns().stream().toArray(Object[][]:: new),model1.getColumnEns());
        	JTable source1 = new JTable(table1){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
        	JScrollPane Spane1= new JScrollPane(source1);
        	Spane1.setBounds(40,50,800,150);
        	afficherEns.add(Spane1);
        	
              btnDelete1.setForeground(new Color(255, 255, 255));
              btnDelete1.setBackground(new Color(255, 0, 0));
              btnDelete1.setFont(new Font("Times New Roman", Font.BOLD, 12));
              btnDelete1.setBounds(161, 461, 147, 45);
              afficherEns.add(btnDelete1);

              btnUpdate1.setBackground(new Color(0, 255, 0));
              btnUpdate1.setFont(new Font("Times New Roman", Font.BOLD, 11));
              btnUpdate1.setBounds(584, 461, 141, 45);
              afficherEns.add(btnUpdate1);
              
         //jtable selection mode
              source1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
        			@Override
        			public void valueChanged(ListSelectionEvent e) {
        				try {
        				int a = source1.getSelectedRow();
        				textArea.setText(source1.getValueAt(a, 0).toString());
        				field1.setText(source1.getValueAt(a, 1).toString());
        				field2.setText(source1.getValueAt(a, 2).toString());
        				field3.setText(source1.getValueAt(a, 3).toString());
        				}catch(IndexOutOfBoundsException z){
        				source1.setRowSelectionAllowed(false);
        				}
        			}
        		});
              
              btnDelete1.addActionListener(new ActionListener() {
        	      	public void actionPerformed(ActionEvent e) {
        	      		EnseignantDAO op = new EnseignantDAO();
        				op.supprimer(textArea.getText());
        					//source.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        					int n = source1.getSelectedRow();
        					if(n != -1) {
        						table1.removeRow(n); 
        						source1.revalidate();	
        					} 
        	      	}
        	      });
              
              btnUpdate1.addActionListener(new ActionListener() {
        	      	public void actionPerformed(ActionEvent e) {
        	      	
        	      		Object[] inputs = {"Nom:", field1, "Prenom:", field2 , "Téléphone:", field3};
        	      		int result1 = JOptionPane.showConfirmDialog(null, inputs, "Modifier l'enseignant", JOptionPane.OK_CANCEL_OPTION);
        	      		if (result1 == JOptionPane.OK_OPTION) {
        	      		    // Récupérer les valeurs entrées dans les champs de texte
        	      		//update student in database according to the id
        					Enseignant ense = new Enseignant(textArea.getText(),"",field1.getText(),field2.getText(),field3.getText(),5);
        					ense.setIdentifiant(textArea.getText());
        					EnseignantDAO enseign = new EnseignantDAO();
        					enseign.update(ense);
        					//update value in table too
        							int i = source1.getSelectedRow();
        							source1.setValueAt(field1.getText(), i, 1);
        							source1.setValueAt(field2.getText(), i, 2);
        							source1.setValueAt(field3.getText(), i, 3);
        							//table.fireTableDataChanged();
        							source1.revalidate();
        	      		}
        	      	}
        	      });
        	 card.show(panell,"afficherEns");
         }
      });

      // la fin de la methode qui permet d'afficher, de supprimer ou de modifier les enseignants 
    
      menu2.add(eMenu1);
      menu2.add(eMenu2);
      
      // Menu cours et séance
      createCours.setLayout(null);
      //createCours.setBounds(0, 102, 874, 517);
      JMenu menu3 = new JMenu("Cours & Séances");
      JMenuItem sM = new JMenuItem("Ajouter cours");
      sM.setHorizontalAlignment(SwingConstants.LEFT);
      sM.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 card.show(panell,"createCours");
     		
     		JLabel lblN_1 = new JLabel("NOM");
     		lblN_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblN_1.setBounds(214, 105, 119, 35);
     		createCours.add(lblN_1);
     		
     		JLabel lblN_2 = new JLabel("Masse Horaire");
     		lblN_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblN_2.setBounds(214, 151, 119, 29);
     		createCours.add(lblN_2);
     		
     		JTextField nomC = new JTextField();
     		nomC.setBounds(471, 112, 171, 20);
     		createCours.add(nomC);
     		nomC.setColumns(10);
     		
     		JTextField mssH = new JTextField();
     		mssH.setBounds(471, 155, 171, 20);
     		createCours.add(mssH);
     		mssH.setColumns(10);
     		/**
     		 * button pour ajouter un cours
     		 */
     		JButton ajoutCrs = new JButton("Ajouter");
     		ajoutCrs.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				CoursDAO cc = new CoursDAO();
     				Cours crs = new Cours(nomC.getText(),Integer.parseInt(mssH.getText()));
     				cc.add(crs);
     				nomC.setText("");
     				mssH.setText("");
     				JOptionPane.showMessageDialog(null, "Ajouter avec succes!!");
     			}
     		});
     		ajoutCrs.setBackground(new Color(192, 192, 192));
     		ajoutCrs.setFont(new Font("Tahoma", Font.BOLD, 14));
     		ajoutCrs.setBounds(385, 358, 119, 44);
     		createCours.add(ajoutCrs);
         }
      });
      
      JMenuItem sM1 = new JMenuItem("Ajouter une séance");
      sM1.setHorizontalAlignment(SwingConstants.LEFT);
      sM1.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 card.show(panell,"createSeance");
        	 JLabel lblNS_1 = new JLabel("Salle");
     		lblNS_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNS_1.setBounds(214, 73, 119, 35);
     		createSeance.add(lblNS_1);
     		
     		JLabel lblNJ_2 = new JLabel("Jour");
     		lblNJ_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNJ_2.setBounds(214, 119, 119, 29);
     		createSeance.add(lblNJ_2);
     		
     		hd = new JTextField();
     		hd.setBounds(451, 163, 191, 20);
     		createSeance.add(hd);
     		hd.setColumns(10);
     		
     		hf = new JTextField();
     		hf.setBounds(451, 199, 191, 20);
     		createSeance.add(hf);
     		hf.setColumns(10);
     		/**
     		 * Ajouter une seance
     		 */
     		JButton ajoutSean = new JButton("Ajouter");
     		ajoutSean.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     			SeanceDAO sc = new SeanceDAO();
     			GroupeDAO gr =new GroupeDAO();
     			FonctionsDAO fn = new FonctionsDAO();
     			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     			
				try {
					java.util.Date d = formatter.parse(textFieldjr.getText());
					java.sql.Date sqlDate = new java.sql.Date(d.getTime());
					Seance sc1 = new Seance(fn.recppr(sl3), gr.recp(sl4), fn.recpCrs(sl),fn.recpSalle(sl1),sqlDate,Integer.parseInt(hd.getText()),Integer.parseInt(hf.getText()),fn.recpTpS(sl2));
					sc.add(sc1);
					hd.setText("");
					hf.setText("");
					textFieldjr.setText("");
					JOptionPane.showMessageDialog(null, "Ajouter avec succes!!");
					
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	     			
     			}
     		});
     		ajoutSean.setBackground(new Color(192, 192, 192));
     		ajoutSean.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		ajoutSean.setBounds(339, 369, 119, 44);
     		createSeance.add(ajoutSean);
     		
     		JLabel cousL = new JLabel("Cours");
     		cousL.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		cousL.setBounds(214, 42, 70, 20);
     		createSeance.add(cousL);
     		
     		JLabel lblNewLabelHD_1 = new JLabel("Heure Début");
     		lblNewLabelHD_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNewLabelHD_1.setBounds(214, 159, 87, 26);
     		createSeance.add(lblNewLabelHD_1);
     		
     		JLabel lblNewLabelHF_2 = new JLabel("Heure Fin");
     		lblNewLabelHF_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNewLabelHF_2.setBounds(214, 196, 87, 24);
     		createSeance.add(lblNewLabelHF_2);
     		
     		JLabel lblNewLabelTS_3 = new JLabel("Type séance");
     		lblNewLabelTS_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNewLabelTS_3.setBounds(214, 231, 87, 24);
     		createSeance.add(lblNewLabelTS_3);
     		
     		FonctionsDAO fn = new FonctionsDAO();
     		JComboBox comboBoxCrs = new JComboBox();
     		comboBoxCrs.setBounds(451, 42, 191, 22);
     		createSeance.add(comboBoxCrs);
     		for(String s : fn.listCrs()) {
     			comboBoxCrs.addItem(s);
     		}
     		comboBoxCrs.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	sl = (String) comboBoxCrs.getSelectedItem();	
	            }
	        });
     		
     		JComboBox comboBoxSal = new JComboBox();
     		comboBoxSal.setBounds(451, 80, 191, 22);
     		createSeance.add(comboBoxSal);
     		for(String s : fn.listSalle()) {
     			comboBoxSal.addItem(s);
     		}
     		comboBoxSal.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	sl1 = (String) comboBoxSal.getSelectedItem();	
	            }
	        });
     		
     		JComboBox comboBoxts_2 = new JComboBox();
     		comboBoxts_2.setBounds(451, 230, 188, 22);
     		createSeance.add(comboBoxts_2);
     		for(String s : fn.listTseance()) {
     			comboBoxts_2.addItem(s);
     		}
     		comboBoxts_2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	sl2 = (String) comboBoxts_2.getSelectedItem();	
	            }
	        });
     		
     		textFieldjr = new JTextField();
     		textFieldjr.setBounds(451, 124, 191, 20);
     		createSeance.add(textFieldjr);
     		textFieldjr.setColumns(10);
     		
     		JLabel lblNewLabelPr_4 = new JLabel("Professeur");
     		lblNewLabelPr_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
     		lblNewLabelPr_4.setBounds(214, 266, 101, 28);
     		createSeance.add(lblNewLabelPr_4);
     		
     		JComboBox comboBoxpr_3 = new JComboBox();
     		comboBoxpr_3.setBounds(451, 270, 191, 22);
     		createSeance.add(comboBoxpr_3);
     		for(String s : fn.listTPro()) {
     			comboBoxpr_3.addItem(s);
     		}
     		comboBoxpr_3.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	sl3 = (String) comboBoxpr_3.getSelectedItem();	
	            }
	        });
     		
     		JLabel lblG = new JLabel("Groupe");
    		lblG.setFont(new Font("Times New Roman", Font.BOLD, 14));
    		lblG.setBounds(214, 305, 119, 31);
    		createSeance.add(lblG);
    		
    		GroupeDAO gr = new GroupeDAO();
    		JComboBox comboBoxG = new JComboBox();
    		comboBoxG.setBounds(451, 310, 191, 22);
    		createSeance.add(comboBoxG);
    		for(String s : gr.listGr()) {
    			comboBoxG.addItem(s);
    		}
    		comboBoxG.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	sl4 = (String) comboBoxG.getSelectedItem();	
	            }
	        });
     		// selectionner une date
     		JButton Date = new JButton("Date");
     		Date.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				if (e.getSource() == Date) {
                         JCalendar calendar = new JCalendar();
                         calendar.setLocale(Locale.ENGLISH);
                         calendar.setTodayButtonVisible(true);
                         calendar.setNullDateButtonVisible(true);
                         JOptionPane.showMessageDialog(null, calendar, "Select a date", JOptionPane.PLAIN_MESSAGE);
                         SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                         if (calendar.getDate() != null) {
                             textFieldjr.setText(sdf.format(calendar.getDate()));
                         }
                     }
     			}
     		});
     		Date.setBounds(645, 123, 65, 23);
     		createSeance.add(Date);
         }
      });
     menu3.add(sM);
     menu3.add(sM1);
      //fin de gestion de cours et seance
      
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
     //fin de la partie deconnexion
      
      menuBar.add(menu1);
      menuBar.add(menu2);
      menuBar.add(menu3);
      menuBar.add(menu4);
      menuBar.add(menu5);
      
      Panel panel = new Panel();
      panel.setBackground(new Color(255, 255, 255));
      panel.setBounds(0, 55, 874, 41);
      panel.setLayout(null);
      
      //title panel
      JLabel lblNewLabel = new JLabel("BIENVENUE !!!!!");
      lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
      lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel.setBounds(287, 11, 294, 14);
      panel.add(lblNewLabel);
      //
      
      //afficher les etudiants selon le groupe

      afficherEtu.setLayout(null);
      GroupeDAO g1 = new GroupeDAO();
      JComboBox comboShowGr = new JComboBox();
      comboShowGr.setBounds(447, 11, 113, 22);
      afficherEtu.add(comboShowGr);
      for(String s : g1.listGr())
      {
    	  comboShowGr.addItem(s);
      }
      
      JLabel lblNewLabel_1 = new JLabel("Choisissez le groupe");
      lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 13));
      lblNewLabel_1.setBounds(279, 15, 141, 14);
      afficherEtu.add(lblNewLabel_1);
     
      btnDelete.setForeground(new Color(255, 255, 255));
      btnDelete.setBackground(new Color(255, 0, 0));
      btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 12));
      btnDelete.setBounds(161, 461, 147, 45);
      afficherEtu.add(btnDelete);

      btnUpdate.setBackground(new Color(0, 255, 0));
      btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 11));
      btnUpdate.setBounds(584, 461, 141, 45);
      afficherEtu.add(btnUpdate);
      panel_3.setBounds(9, 51, 855, 390);

      comboShowGr.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String selectedValue = (String) comboShowGr.getSelectedItem();
            	panel_3.removeAll();
            	Panel panel_2 = new Panel();
            	int r=0;

            	 panel_2.setBounds(10, 52, 854, 391);
            	 panel_2.setLayout(null);
	        	    	 EtudiantDAO e1 = new EtudiantDAO();
	        		     ResultSet lire = e1.affichage(selectedValue);
	        		     ModeleStatique model = new ModeleStatique();
	        	      	 DefaultTableModel  table = new DefaultTableModel(model.getData(lire).stream().toArray(Object[][]:: new),model.getColumn());
	        	      	JTable source = new JTable(table){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
	        	   
	        	      	JScrollPane spane= new JScrollPane(source);
	        	      	
	        	  		//Spane.setPreferredSize(new Dimension(600, 430));
	        	  		spane.setBounds(40,50,800,150);
	        	  		 panel_2.add(spane);
	        	            
	        	  		//Table selection mode
	        		      source.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	        					@Override
	        					public void valueChanged(ListSelectionEvent e) {
	        						// TODO Auto-generated method stub
	        						//use try to avoid -1 out of bound exception 
	        						try {
	        						int a = source.getSelectedRow();
	        						textArea.setText(source.getValueAt(a, 0).toString());
	        						field1.setText(source.getValueAt(a, 1).toString());
	        						field2.setText(source.getValueAt(a, 2).toString());
	        						field3.setText(source.getValueAt(a, 5).toString());
	        						temp1=source.getValueAt(a, 3).toString();
	        						temp2=source.getValueAt(a, 4).toString();
	        						}catch(IndexOutOfBoundsException z){
	        						source.setRowSelectionAllowed(false);
	        						}
	        						
	        					}
	        				});

	        		      //delete option
	        		      btnDelete.addActionListener(new ActionListener() {
	        			      	public void actionPerformed(ActionEvent e) {
	        			      		EtudiantDAO op = new EtudiantDAO();
	        						op.supprimer(textArea.getText());
	        							//source.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	        							int n = source.getSelectedRow();
	        							if(n != -1) {
	        								table.removeRow(n); 
	        								source.revalidate();	
	        							} 
	        			      	}
	        			      });
	        		      
	        		      // update option
	        		      btnUpdate.addActionListener(new ActionListener() {
	        			      	public void actionPerformed(ActionEvent e) {
	        			      		JComboBox ShowGr = new JComboBox();
	        			  	      for(String s : g1.listGr()){
	        				    	  ShowGr.addItem(s);
	        				      }
	        			            	
	        			            ShowGr.addActionListener(new ActionListener() {
	        			     	      public void actionPerformed(ActionEvent e) {
	        			     	    	  temp2=(String) ShowGr.getSelectedItem();
	        			     	            }
	        			     	        });
	        			  	    JComboBox ShowFil = new JComboBox();
	        			  	      for(String s : f.listFil()){
	        				    	  ShowFil.addItem(s);
	        				      }
	        			  	    ShowFil.addActionListener(new ActionListener() {
	        			     	      public void actionPerformed(ActionEvent e) {
	        			     	    	  temp1=(String) ShowFil.getSelectedItem();
	        			     	            }
	        			     	        });
	        			      		Object[] inputs = {"Nom:", field1, "Prenom:", field2 , "Mail:", field3,"Groupe:",ShowGr,"Filiere",ShowFil};
	        			      		int result = JOptionPane.showConfirmDialog(null, inputs, "Formulaire", JOptionPane.OK_CANCEL_OPTION);
	        			      		if (result == JOptionPane.OK_OPTION) {
	        			      		    // Récupérer les valeurs entrées dans les champs de texte
	        			      		//update student in database according to the id
	        							Etudiant etuu = new Etudiant(textArea.getText(),"",field1.getText(),field2.getText(),field3.getText(),g1.recp(temp2),4,f.recpFiliere(temp1));
	        							etuu.setIdentifiant(textArea.getText());
	        							e1.update(etuu);
	        							//update value in table too
	        									int i = source.getSelectedRow();
	        									source.setValueAt(field1.getText(), i, 1);
	        									source.setValueAt(field2.getText(), i, 2);
	        									source.setValueAt(temp1, i, 3);
	        									source.setValueAt(temp2, i, 4);
	        									source.setValueAt(field3.getText(), i, 5);
	        									//table.fireTableDataChanged();
	        									source.revalidate();
	        			      		}
	        			      	}
	        			      });
	        		    panel_3.add(panel_2);
	        		    afficherEtu.add(panel_3);
	        		     afficherEtu.revalidate();
	      	              afficherEtu.repaint();
            }
        });
      
      //create student panel
      createEtu.setBackground(new Color(64, 128, 128));
     // createEtu.setBounds(0, 102, 874, 517);
     
      createEtu.setLayout(null);	
      textField_2 = new JTextField();
      textField_2.setBounds(397, 72, 140, 20);
      createEtu.add(textField_2);
      textField_2.setColumns(10);
      
      textField_3 = new JTextField();
      textField_3.setBounds(397, 122, 140, 20);
      createEtu.add(textField_3);
      textField_3.setColumns(10);
      
      FonctionsDAO f1 = new FonctionsDAO();
      JComboBox filCombo = new JComboBox();
      filCombo.setToolTipText("");
      filCombo.setBounds(397, 292, 140, 22);
      for(String s : f1.listFil())
      {
    	  filCombo.addItem(s);
      }
      createEtu.add(filCombo);
      filCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selected1 = (String) filCombo.getSelectedItem();	
            }
        });
      
      textField_4 = new JTextField();
      textField_4.setBounds(397, 172, 140, 20);
      createEtu.add(textField_4);
      textField_4.setColumns(10);
      
      textField = new JTextField();
      textField.setBounds(397, 212, 140, 20);
      createEtu.add(textField);
      textField.setColumns(10);
      
      textField_1 = new JTextField();
      textField_1.setBounds(397, 253, 140, 20);
      createEtu.add(textField_1);
      textField_1.setColumns(10);
      
      GroupeDAO gpr = new GroupeDAO();
      JComboBox grCombo = new JComboBox();
      grCombo.setBounds(397, 349, 140, 22);
      createEtu.add(grCombo);
      for(String s : gpr.listGr())
      {
    	  grCombo.addItem(s);
      }
      
      grCombo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	selected = (String) grCombo.getSelectedItem();	
            }
        });
      
      JLabel lblNewLabel_11 = new JLabel("NOM");
      lblNewLabel_11.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_11.setBounds(158, 74, 46, 14);
      createEtu.add(lblNewLabel_11);
      
      JLabel lblNewLabel_1_1 = new JLabel("PRENOM");
      lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_1_1.setBounds(158, 124, 89, 14);
      createEtu.add(lblNewLabel_1_1);
      
      JLabel lblNewLabel_1_2 = new JLabel("MAIL");
      lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_1_2.setBounds(158, 174, 46, 14);
      createEtu.add(lblNewLabel_1_2);
      
      JLabel lblNewLabel_1_3 = new JLabel("IDENTIFIANT");
      lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_1_3.setBounds(158, 214, 107, 14);
      createEtu.add(lblNewLabel_1_3);
      
      JLabel lblNewLabel_1_4 = new JLabel("MOT DE PASSE");
      lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_1_4.setBounds(158, 254, 107, 17);
      createEtu.add(lblNewLabel_1_4);
      
      JLabel lblNewLabel_1_5 = new JLabel("FILIERE");
      lblNewLabel_1_5.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_1_5.setBounds(158, 292, 89, 20);
      createEtu.add(lblNewLabel_1_5);
      
      JLabel lblNewLabel_1_6 = new JLabel("GROUPE");
      lblNewLabel_1_6.setFont(new Font("Times New Roman", Font.BOLD, 14));
      lblNewLabel_1_6.setBounds(158, 335, 89, 20);
      createEtu.add(lblNewLabel_1_6);
      /**
       * button pour ajouter un etudiant
       */
      JButton btnNewButton = new JButton("Ajouter");
      btnNewButton.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		int x =gpr.recp(selected);
      		int y =f1.recpFiliere(selected1);
      		if(gpr.nbrEtu(x)<gpr.cap(x)) {
      		Etudiant etu = new Etudiant(textField.getText(),textField_1.getText(),textField_2.getText(),textField_3.getText(),textField_4.getText(),x,4,y);
      		EtudiantDAO etuD = new EtudiantDAO();
      		etuD.add(etu);
      		textField.setText("");
      		textField_1.setText("");
      		textField_2.setText("");
      		textField_3.setText("");
      		textField_4.setText("");
      		JOptionPane.showMessageDialog(null, "Opération réussie 😎");
      		}
      		else {
      			JOptionPane.showMessageDialog(null, "Capacité atteinte 🙃");
      		}
      	}
      });
      btnNewButton.setBackground(new Color(192, 192, 192));
      btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
      btnNewButton.setBounds(316, 399, 146, 43);
      createEtu.add(btnNewButton);
      
      Panel panel_1 = new Panel();
      panel_1.setBounds(10, 628, 874, 23);
      panel_1.setLayout(null);
      
      JLabel lblNewLabel_2 = new JLabel("2023 Copyright");
      lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 11));
      lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
      lblNewLabel_2.setBounds(347, 0, 192, 25);
      panel_1.add(lblNewLabel_2);
      
      frame.getContentPane().add(panel_1);
      frame.getContentPane().add(panel);
       
       JLabel lblNewLabel_3 = new JLabel("");
       lblNewLabel_3.setIcon(new ImageIcon("R.jpg"));
       lblNewLabel_3.setBounds(10, 5, 136, 30);
       panel.add(lblNewLabel_3);
       frame.getContentPane().add(panell);
       
}
/**
 * afficher le frame
 */
	public void show() {
		frame.setVisible(true);
	}
}
	
