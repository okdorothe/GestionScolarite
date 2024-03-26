package Gui;
import Dao.*;
import Model.*;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
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
import javax.swing.table.TableColumn;

import com.toedter.calendar.JCalendar;

import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
/**
 * interface de l'utilisateur Enseignant
 * @author Do Ro The
 *@version 1.0
 */
public class AccEnseignGui {
	

	private JFrame frame;
	
	/**
	 * Constructeur de la classe AccEnseigant
	 * @param ide
	 */
	public AccEnseignGui(String ide) {
		// Création de la fenêtre principale et ses composants
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBounds(300, 20, 900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		CardLayout card = new CardLayout();
		JPanel panel = new JPanel(card);
		panel.setBounds(0, 101, 884, 515);
		frame.getContentPane().add(panel);
		card.show(panel, "empty");
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel();
		Panel cal = new Panel();
		cal.setBounds(155, 5, 516, 163);
		panel.add(panel2,"panel 2");
		panel.add(panel1,"panel 1");
		panel1.setLayout(null);
		JCalendar calendar = new JCalendar();
		calendar.setPreferredSize(new Dimension(515, 162));
		cal.add(calendar);
		panel1.add(cal);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(60, 174, 787, 341);
		panel1.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(60, 174, 787, 341);
		//panel1.add(panel_2);
		
		panel2.setLayout(null);
		
		AnimatedLabel an =new AnimatedLabel();
		//JLabel lblNewLabel_3 = new JLabel("New label");
		an.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
		an.setHorizontalAlignment(SwingConstants.CENTER);
		an.setBounds(10, 214, 864, 28);
		panel2.add(an);
		
		JButton btnNewButton = new JButton("Soumettre");
		
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(331, 457, 190, 47);
		
	
		JLabel lblNewLabel_1 = new JLabel("PLANNING");
		lblNewLabel_1.setVisible(false);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(372, 67, 138, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 988, 56);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Planning");
		JMenuItem pl = new JMenuItem("Afficher Planning");
		pl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_1.setVisible(true);
				card.show(panel,"panel 1");
			}
		});
		mnNewMenu.add(pl);
		menuBar.add(mnNewMenu);
		

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
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Do Ro The\\Pictures\\R.jpg"));
		lblNewLabel.setBounds(10, 61, 138, 29);
		frame.getContentPane().add(lblNewLabel);
		
		
		
		JLabel lblNewLabel_2 = new JLabel("2023 Copyright");
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel_2.setBounds(372, 627, 121, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		//lorque on clique sur une date sur le calen
		calendar.addPropertyChangeListener("calendar", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent evt) {
		        if ("calendar".equals(evt.getPropertyName())) {
		        	panel_1.removeAll();
		        	panel_1.validate();
		            Date date = calendar.getDate();
		            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		            String a= sdf.format(calendar.getDate());
		            java.sql.Date sqlDate = null;
		            try {
						java.util.Date d = sdf.parse(a);
						sqlDate = new java.sql.Date(d.getTime());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            ModeleStatique md = new ModeleStatique();
		             FonctionsDAO fn = new FonctionsDAO();
		        	ArrayList<Object[]> list = new ArrayList<>();
		            list = md.getDataS1(sqlDate ,fn.recppr1(ide));
		            
		            if(!(list.isEmpty()))
		            {
		            	panel_1.setLayout(new GridLayout(list.size(),1));
		             // Ajout d'un JPanel pour chaque cours, contenant les informations dans des JLabel
		            for(Object[] o : list) {
		            	    JPanel coursePanel = new JPanel(new GridLayout(1, 7));
		            	    JButton b = new JButton("Appel");
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
		                    coursePanel.add(b);
		                    b.addActionListener(new ActionListener() {
		            			public void actionPerformed(ActionEvent e) {
		            				panel2.removeAll();
		            				panel2.add(btnNewButton);
		            				GroupeDAO g = new GroupeDAO();
		            				    ModeleStatique model1 = new ModeleStatique();
		            			  		DefaultTableModel table1 = new DefaultTableModel(model1.getDataEtu2(g.recp((String) o[5])).stream().toArray(Object[][]:: new),model1.getColumns());
		            			  		//JTable source1 = new JTable(table1){public boolean isCellEditable(int rowIndex, int colIndex){return false;}};
									
									JTable source1 = new JTable(table1) {
									    public boolean isCellEditable(int rowIndex, int colIndex) {
									        // Make all columns editable except the first one
									        return colIndex != 0;
									    }
									};
									
									// Create a new column in the table's DefaultTableModel
									table1.addColumn("Select");
									
									// Set the cell editor for the new column to a JCheckBox
									source1.getColumnModel().getColumn(table1.getColumnCount() - 1).setCellEditor(new DefaultCellEditor(new JCheckBox()));
									
									// Set the cell renderer for the new column to a JCheckBox
									source1.getColumnModel().getColumn(table1.getColumnCount() - 1).setCellRenderer(new DefaultTableCellRenderer() {
									    private final JCheckBox checkbox = new JCheckBox();
									
									    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
									        checkbox.setSelected(value != null && (boolean) value);
									        checkbox.setHorizontalAlignment(JCheckBox.CENTER);
									        return checkbox;
									    }
									});
									
									// Créer un ArrayList pour stocker les index des lignes sélectionnées
									ArrayList<Integer> selectedRows = new ArrayList<Integer>();

									// Ajouter un éditeur de cellules pour la colonne de cases à cocher
									source1.getColumnModel().getColumn(table1.getColumnCount() - 1).setCellEditor(new DefaultCellEditor(new JCheckBox()));

									// Ajouter un écouteur de cellules d'édition pour la colonne de cases à cocher
									source1.getColumnModel().getColumn(table1.getColumnCount() - 1).getCellEditor().addCellEditorListener(new CellEditorListener() {
									    public void editingStopped(ChangeEvent e) {
									        // Obtenir l'index de la ligne sélectionnée
									        int row = source1.getSelectedRow();

									        // Vérifier si la case à cocher est cochée
									        boolean checked = (Boolean) source1.getValueAt(row, table1.getColumnCount() - 1);

									        if (checked) {
									            // Ajouter l'index de la ligne sélectionnée à l'ArrayList des lignes sélectionnées
									            selectedRows.add(row);
									        } else {
									            // Retirer l'index de la ligne sélectionnée de l'ArrayList des lignes sélectionnées
									            selectedRows.remove(Integer.valueOf(row));
									        }
									    }

									    public void editingCanceled(ChangeEvent e) {
									    }
									});
		            			  		JScrollPane Spane1= new JScrollPane(source1);
		            			  		Spane1.setBounds(40,50,800,150);
		            			  		panel2.add(Spane1);
		            			  		panel2.validate();
		            			  		card.show(panel,"panel 2");		            			  		
                                        FonctionsDAO fn= new FonctionsDAO(); 
                                        
                                        
                                        btnNewButton.addActionListener(new ActionListener() {
                                			public void actionPerformed(ActionEvent e) {
                                				
                                				for (int row : selectedRows) {
        		            			  		    // avoir la valeur de la ligne selectionée
        		            			  		    String value =(String) table1.getValueAt(row, 0);
        		            			  		  // ajouter une absence
        		            			  		    AbsentDAO a = new AbsentDAO();
        		            			  		    a.add(fn.recppr((String) o[4]), fn.recpCrs((String) o[1]), fn.recpSalle((String) o[2]), g.recp((String) o[5]),fn.recpEtu(value) , 1);
        		            			  		  JOptionPane.showMessageDialog(null, "Effectué!!");
        		            			  		}
                                			}
                                		});
		            			 
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
	}
	public void show() {
		frame.setVisible(true);
	}
}
