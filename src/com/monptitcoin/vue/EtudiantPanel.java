/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monptitcoin.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import com.monptitcoin.Dao.EtudiantDao;
import com.monptitcoin.controller.EtudiantControlleur;
import com.monptitcoin.modele.Etudiant;
import com.toedter.calendar.JDateChooser;

import javax.swing.DefaultComboBoxModel;







import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Fakau T.M
 */
public class EtudiantPanel extends JPanel {
	private JTable table;
	private JLabel lblInscriptionDesPostulant;
	private JLabel lblListeDesPostulant;
	private JButton btn_rechercher;
	private JTextField textField;
	private final JLabel lab_id = new JLabel("Id");
	private final JTextField field_code = new JTextField();
	private final JLabel lab_code = new JLabel("Code");
	private final JTextField field_nom = new JTextField();
	private final JLabel lab_nom = new JLabel("Nom");
	private final JTextField field_prenom = new JTextField();
	private final JLabel lab_prenom = new JLabel("Prenom");
	private final JLabel lab_sexe = new JLabel("Sexe");
	private final JTextField field_option = new JTextField();
	private final JLabel lab_option = new JLabel("Option");
	private final JLabel lab_niveau = new JLabel("Niveau");
	private final JLabel lab_date_naissance = new JLabel("Date naissance");
	private final JButton btnExporterSurExel = new JButton("Exporter sur excel");
	private final JButton btn_import_excelfile = new JButton(
			"Importer fichier excel");
	private final JButton btn_print = new JButton("Imprimer la liste");
	private final JLabel panel_photo = new JLabel();
	private final JLabel lab_date_inscription = new JLabel("Date inscription");
	private final JButton btn_update = new JButton("");
	private final JButton btn_delete = new JButton("");
	private final JButton btn_clear = new JButton("");
	private final JButton btn_initialize = new JButton("");
	private final JLabel field_id = new JLabel("-");
	private final JLabel field_date_inscription = new JLabel("yyyy-mm-dd");
	private JTextField field_rechercher;
	private JComboBox field_niveau;
	private JComboBox field_sexe;
	private final JButton btn_prendre_photo = new JButton("Prendre photo");
	private EtudiantTableModel model=new EtudiantTableModel();
	private JScrollPane sc;
	JDateChooser field_date_naissance;
	private JTextField field_no_recu;
	private JTextField field_montant;
	private EtudiantControlleur controller=new EtudiantControlleur();
	private EtudiantDao dao=new EtudiantDao();
	JButton btn_save;
	private File  fileInput;

	public EtudiantPanel() {
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.setBounds(37, 26, 1174, 535);
		this.setLayout(null);

		lblInscriptionDesPostulant = new JLabel("Inscription des postulants");
		lblInscriptionDesPostulant.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInscriptionDesPostulant.setBounds(477, 11, 271, 38);
		this.add(lblInscriptionDesPostulant);

		lblListeDesPostulant = new JLabel("Liste des postulants inscrient");
		lblListeDesPostulant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListeDesPostulant.setBounds(197, 47, 175, 24);
		this.add(lblListeDesPostulant);

		table = new JTable(model);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		sc=new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setBounds(23, 113, 530, 366);
		this.add(sc);

		btn_rechercher = new JButton("Rechercher");
		btn_rechercher.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/search.png")));
		btn_rechercher.setBounds(442, 79, 111, 23);
		this.add(btn_rechercher);

		JLabel lblRechercherPostulant = new JLabel("Rechercher postulant");
		lblRechercherPostulant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRechercherPostulant.setBounds(23, 82, 175, 24);
		this.add(lblRechercherPostulant);

		lab_id.setBounds(603, 245, 46, 14);

		this.add(lab_id);
		field_code.setColumns(10);
		field_code.setBounds(737, 273, 192, 20);

		this.add(field_code);
		lab_code.setBounds(603, 276, 46, 14);

		this.add(lab_code);
		field_nom.setColumns(10);
		field_nom.setBounds(737, 304, 192, 20);

		this.add(field_nom);
		lab_nom.setBounds(603, 307, 86, 14);

		this.add(lab_nom);
		field_prenom.setColumns(10);
		field_prenom.setBounds(737, 335, 192, 20);

		this.add(field_prenom);
		lab_prenom.setBounds(603, 338, 86, 14);

		this.add(lab_prenom);
		lab_sexe.setBounds(603, 369, 86, 14);

		this.add(lab_sexe);
		field_option.setColumns(10);
		field_option.setBounds(737, 459, 192, 20);

		this.add(field_option);
		lab_option.setBounds(603, 462, 86, 14);

		this.add(lab_option);
		lab_niveau.setBounds(603, 431, 86, 14);

		this.add(lab_niveau);
		lab_date_naissance.setBounds(603, 406, 86, 14);

		this.add(lab_date_naissance);
		btnExporterSurExel.setBounds(23, 490, 134, 23);

		this.add(btnExporterSurExel);
		btn_import_excelfile.setBounds(221, 490, 134, 23);

		this.add(btn_import_excelfile);
		btn_print.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/print.png")));
		btn_print.setBounds(419, 490, 134, 23);

		this.add(btn_print);
		panel_photo.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/default.jpg")));
		panel_photo.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_photo.setBounds(737, 82, 192, 145);

		this.add(panel_photo);

		btn_save = new JButton("");
		btn_save.setToolTipText("Enregistrer");
		btn_save.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/Save-50.png")));
		btn_save.setBounds(999, 82, 86, 61);
		this.add(btn_save);
		lab_date_inscription.setBounds(603, 496, 86, 14);

		this.add(lab_date_inscription);
		btn_update.setToolTipText("Editer");
		btn_update.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/Edit Row-50 - Copy.png")));
		btn_update.setBounds(999, 154, 86, 61);

		this.add(btn_update);
		btn_delete.setToolTipText("Supprimer");
		btn_delete.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/Delete-50 - Copy.png")));
		btn_delete.setBounds(999, 226, 86, 61);

		this.add(btn_delete);
		btn_clear.setToolTipText("Initialiser");
		btn_clear.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/Refresh-50.png")));
		btn_clear.setBounds(999, 304, 86, 61);

		this.add(btn_clear);
		btn_initialize.setToolTipText("Rechercher tout");
		btn_initialize.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/Search Property-50.png")));
		btn_initialize.setBounds(999, 387, 86, 61);

		this.add(btn_initialize);
		field_id.setBounds(737, 245, 46, 14);
		
		add(field_id);
		field_date_inscription.setBounds(737, 494, 192, 14);
		
		add(field_date_inscription);
		
		field_date_naissance = new JDateChooser();
		field_date_naissance.setDateFormatString("yyyy-MM-dd");
		field_date_naissance.setBounds(737, 397, 192, 20);
		add(field_date_naissance);
		
	    field_niveau = new JComboBox();
	    field_niveau.setModel(new DefaultComboBoxModel(new String[] {"1ere Annee", "2ere Annee", "3ere Annee", "4ere Annee"}));
		field_niveau.setBounds(737, 428, 192, 20);
		add(field_niveau);
		
		field_sexe = new JComboBox();
		field_sexe.setModel(new DefaultComboBoxModel(new String[] {"Masculin", "Feminin"}));
		field_sexe.setBounds(737, 366, 192, 20);
		add(field_sexe);
		
		field_rechercher = new JTextField();
		field_rechercher.setBounds(207, 82, 225, 20);
		add(field_rechercher);
		field_rechercher.setColumns(10);
		btn_prendre_photo.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/take_p.png")));
		btn_prendre_photo.setBounds(793, 226, 136, 23);
		
		add(btn_prendre_photo);
		
		field_no_recu = new JTextField();
		field_no_recu.setBounds(603, 201, 111, 20);
		add(field_no_recu);
		field_no_recu.setColumns(10);
		
		JLabel lblMontant = new JLabel("No recu");
		lblMontant.setBounds(603, 176, 111, 14);
		add(lblMontant);
		
		field_montant = new JTextField();
		field_montant.setColumns(10);
		field_montant.setBounds(603, 138, 111, 20);
		add(field_montant);
		
		JLabel lab_montant = new JLabel("Montant");
		lab_montant.setBounds(603, 113, 111, 14);
		add(lab_montant);
		
		onStart();
		event();
	}
	private void event(){
		btn_clear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		
		btn_initialize.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				List<Etudiant> myList=dao.getAll();
				if(myList.size()> 0){
					model.load(myList);	
					onSearch();
				}else{
					javax.swing.JOptionPane.showMessageDialog(null, "Liste vide");
				}
				
			}
		});
		
		btn_prendre_photo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file ;
			    BufferedImage img;
				JFileChooser chooser=new JFileChooser();
				int result = chooser.showOpenDialog(null);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					
					try{
						
						file = chooser.getSelectedFile();
						fileInput=file;
						img=ImageIO.read(file);
	                    ImageIcon icon=new ImageIcon(img); // ADDED
	                    panel_photo.setIcon(icon); // ADDED
	                   
	                    Dimension imageSize = new Dimension(icon.getIconWidth(),icon.getIconHeight()); // ADDED
	                    panel_photo.setPreferredSize(imageSize); // ADDED
	
	                    panel_photo.revalidate(); // ADDED
	                    panel_photo.repaint(); // ADDED
					}catch(Exception ex){
						
					}
				}
			}
		});
		
		btn_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(field_id.getText().equals("-")){
					Etudiant etu=new Etudiant();
					etu.setCode(field_code.getText());
			        etu.setOption(field_option.getText()); 
			        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
			      
						try {
							Date 	d = f.parse(f.format(new Date()));
							etu.setCreationDate(d);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				   
				    etu.setNiveau(field_niveau.getSelectedItem().toString());
		            etu.setSexe(field_sexe.getSelectedItem().toString());
					etu.setDateNaissance(field_date_naissance.getDate());
					etu.setNom(field_nom.getText());
					etu.setPrenom(field_prenom.getText());
					etu.setPhoto(getByte(fileInput)); 
					etu.setNumeroRecu(field_no_recu.getText());
					try {
						etu.setMontant(Double.parseDouble(field_montant.getText()) );
						if(field_montant.toString().length()>=0){
							controller.save(etu);
							model.load(dao.getAll());	
						}
					} catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null, "Montant invalide","Erreur",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					javax.swing.JOptionPane.showMessageDialog(null, "SVP reinitilaser les champs","Information",JOptionPane.INFORMATION_MESSAGE);
				}
		     }
			
		});
		
		btn_print.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header=new MessageFormat("Liste des etudiants");
				MessageFormat footer=new MessageFormat("Page{0,number,integer}");
				
				try {
					table.print(JTable.PrintMode.NORMAL, header, footer);
				} catch (Exception e) {
				   javax.swing.JOptionPane.showMessageDialog(null, "Erreur d'impression");
				   System.out.println(e.getMessage());
				}
				
			}
		});
		
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				getRowFromTable();
				
			}
		});
		
		btnExporterSurExel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JTextField filename = new JTextField(); 
			    JTextField dir = new JTextField();
				JFileChooser c = new JFileChooser();
				
			      
			      int rVal = c.showSaveDialog(EtudiantPanel.this);
			      if (rVal == JFileChooser.APPROVE_OPTION) {
			        filename.setText(c.getSelectedFile().getName());
			        dir.setText(c.getCurrentDirectory().toString());
			        exportExcel(filename.getText().trim(),dir.getText().trim());
			      }
			      if (rVal == JFileChooser.CANCEL_OPTION) {
			        filename.setText("You pressed cancel");
			        dir.setText("");
			      }
			}
		});
		
		btn_import_excelfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				File file ;
			   JFileChooser chooser=new JFileChooser();
			   int result = chooser.showOpenDialog(null);
				
				if (result == JFileChooser.APPROVE_OPTION) {
					
					try{
						file = chooser.getSelectedFile();
						controller.importExcelFile(file );
						model.load(dao.getAll());	
						
					}catch(Exception ex){
						
					}
				}
				
			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(!(field_id.getText().equals("-"))){
					Etudiant etu=new Etudiant();
					etu.setId(Integer.parseInt(field_id.getText()));
					etu.setCode(field_code.getText());
			        etu.setOption(field_option.getText()); 
			       
				   
				    etu.setNiveau(field_niveau.getSelectedItem().toString());
		            etu.setSexe(field_sexe.getSelectedItem().toString());
					etu.setDateNaissance(field_date_naissance.getDate());
					etu.setNom(field_nom.getText());
					etu.setPrenom(field_prenom.getText());
					etu.setPhoto(getByte(fileInput)); 
					etu.setNumeroRecu(field_no_recu.getText());
					try {
						etu.setMontant(Double.parseDouble(field_montant.getText()) );
						if(field_montant.toString().length()>=0){
							controller.update(etu);
							model.load(dao.getAll());	
						}
					} catch (Exception e) {
						javax.swing.JOptionPane.showMessageDialog(null, "Montant invalide","Erreur",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					javax.swing.JOptionPane.showMessageDialog(null, "SVP reinitilaser les champs","Information",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		field_rechercher.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				List<Etudiant> lolo=controller.getByName(field_rechercher.getText());
				model.load(lolo);
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_rechercher.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Etudiant et=controller.getByCode(field_code.getText().trim());
				List<Etudiant>ets=new ArrayList<Etudiant>();
				ets.add(et);
				model.load(ets);
			}
		});
	}
	
	private void clear(){
		//private JTable table;
		field_code.setText("");
        field_option.setText("");
	    btnExporterSurExel.setEnabled(false);
		btn_print.setEnabled(false);
		//private final JPanel panel_photo = new JPanel();
		btn_update.setEnabled(false);
		btn_delete.setEnabled(false);
		field_id.setText("-");
		field_date_inscription.setText("yyyy-mm-dd");
		field_niveau.setSelectedIndex(0);
		field_sexe.setSelectedIndex(0);
		field_date_naissance.setDate(null);
		field_nom.setText("");
		field_prenom.setText("");
		panel_photo.setIcon(new ImageIcon(EtudiantPanel.class.getResource("/com/monptitcoin/vue/images/default.jpg")));
		field_no_recu.setText("");
		field_montant.setText("");
		fileInput =new File("src/com/monptitcoin/vue/images/default.jpg");
		List<Etudiant> eet=new ArrayList<Etudiant>();
		model.load(eet);
	}
	private void onStart(){
		btn_update.setEnabled(false);
		btn_delete.setEnabled(false);
	    btnExporterSurExel.setEnabled(true);
		btn_print.setEnabled(true);
		fileInput =new File("src/com/monptitcoin/vue/images/default.jpg");
		model.load(dao.getAll());	
		
		
		field_montant.addKeyListener(new KeyListener() {
	    
			@Override
			public void keyTyped(KeyEvent arg0) {
				 char key=arg0.getKeyChar();
				 if(key == '.' || key == '0' || key == '1' || key == '2'  || key == '3'  || key == '4'  || key == '5'  || key == '6'  || key == '7'  || key == '8'  || key == '9'  ){
					if(field_montant.getText() == null || field_montant.getText().equals("") &&  key=='.'){
						arg0.consume();
					}
					if(field_montant.getText().contains(".") && key == '.'){
						arg0.consume();
					}
				 }else{
					 arg0.consume();
				 }
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		btn_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int a=javax.swing.JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette ligne", "Dialog", JOptionPane.YES_NO_OPTION);
				
				if(a==0){
					Integer id=Integer.parseInt(field_id.getText());
					controller.delete(id);
					model.load(dao.getAll());	
					clear();
				}
				
			}
		});
		
	 
		
    }
	
	
	
	
	private void onSearch(){
		 btnExporterSurExel.setEnabled(true);
		 btn_print.setEnabled(true);
	}
	

	public byte[] getByte(File file){
	
		byte[] imageInByte=null ;
		try {
			BufferedImage originalImage = ImageIO.read(file);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "jpg", baos );
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return imageInByte;
	}
	
	public void getRowFromTable(){
		int row=table.getSelectedRow();
		        field_id.setText(table.getValueAt(row,0).toString());
				field_code.setText(table.getValueAt(row,1).toString());
		        field_nom.setText(table.getValueAt(row,2).toString());
		        field_prenom.setText(table.getValueAt(row,3).toString());
				field_sexe.setSelectedItem(table.getValueAt(row,4).toString());
		        field_option.setText(table.getValueAt(row,5).toString());
		        field_niveau.setSelectedItem(table.getValueAt(row,6).toString());
				field_montant.setText(table.getValueAt(row,7).toString());
		        field_no_recu.setText(table.getValueAt(row,8).toString());
		        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
		        Date date=null;
				try {
					date = d.parse(table.getValueAt(row,9).toString());
					field_date_naissance.setDate(date); 
			        field_date_inscription.setText(table.getValueAt(row,10).toString());
				    byte[] imgByte=dao.getImageByte(Integer.parseInt(table.getValueAt(row,0).toString()));
				    ImageIcon m=new ImageIcon(imgByte);
				    panel_photo.setIcon(m);
				    //fileInput= Files.write(Paths.get("photo"),imgByte );
				    fileInput=new File("src/com/monptitcoin/vue/images/tmp.jpg");
				    FileOutputStream os=new FileOutputStream(fileInput);
	                os.write(imgByte);			
	               
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
			    //fileInput =new File("src/com/monptitcoin/vue/images/default.jpg");
		        btn_update.setEnabled(true);
				btn_delete.setEnabled(true);
	}
	
	public void exportExcel(String filename,String dirpath){
		FileOutputStream fileOut=null;
		XSSFWorkbook workbook=null;
		byte[] buffer = new byte[53];
		
		
		try {
			//workbook
			workbook=new XSSFWorkbook();
			
			XSSFSheet sheet=workbook.createSheet("feuille1");
			
			//stylecolor
			XSSFCellStyle xcs=workbook.createCellStyle();
			xcs.setFillForegroundColor(new XSSFColor(new Color(220,200,140)));
			xcs.setFillPattern(CellStyle.SOLID_FOREGROUND);
	
			//fit cell
			PrintSetup ps=sheet.getPrintSetup();
			sheet.setAutobreaks(true);
			ps.setFitHeight((short)1);
			ps.setFitWidth((short)1);
			
			XSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short)11);
			font.setFontName(HSSFFont.FONT_ARIAL);
			font.setBoldweight(HSSFFont.COLOR_NORMAL);
			font.setBold(true);
			font.setColor(HSSFColor.DARK_BLUE.index);
			
			xcs.setFont(font);
			
			XSSFCellStyle xc2=workbook.createCellStyle();
			xc2.setFont(font);
			xc2.setAlignment(CellStyle.ALIGN_CENTER);
			xc2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
			 Row text = sheet.createRow( 0);
					 Cell c1 = text.createCell(0);
					 c1.setCellValue("Liste des etudiants");
					 sheet.addMergedRegion(new CellRangeAddress(0, 0,0,10 ));
					 c1.setCellStyle(xc2);
		
			 Row rowTitle=sheet.createRow(2);
			 Cell cel0=rowTitle.createCell(0);
			 cel0.setCellValue("Id");
			 cel0.setCellStyle(xcs); 
	
			 Cell cel1=rowTitle.createCell(1);
			 cel1.setCellValue("Code");
			 cel1.setCellStyle(xcs); 
			 
			 Cell cel2=rowTitle.createCell(2);
			 cel2.setCellValue("Nom");
			 cel2.setCellStyle(xcs); 
			 
			 Cell cel3=rowTitle.createCell(3);
			 cel3.setCellValue("Prenom");
			 cel3.setCellStyle(xcs); 
			 
			 Cell cel4=rowTitle.createCell(4);
			 cel4.setCellValue("Sexe");
			 cel4.setCellStyle(xcs); 
			 
			 Cell cel5=rowTitle.createCell(5);
			 cel5.setCellValue("Option");
			 cel5.setCellStyle(xcs); 
			 
			 Cell cel6=rowTitle.createCell(6);
			 cel6.setCellValue("Niveau");
			 cel6.setCellStyle(xcs); 
			 
			 Cell cel7=rowTitle.createCell(7);
			 cel7.setCellValue("Montant");
			 cel7.setCellStyle(xcs); 
			 
			 Cell cel8=rowTitle.createCell(8);
			 cel8.setCellValue("No recu");
			 cel8.setCellStyle(xcs); 
			 
			 Cell cel9=rowTitle.createCell(9);
			 cel9.setCellValue("Date naissance");
			 cel9.setCellStyle(xcs); 
			 
			 Cell cel10=rowTitle.createCell(10);
			 cel10.setCellValue("Date inscription");
			 cel10.setCellStyle(xcs); 
 
			
			
			for (int i=1;i<table.getModel().getRowCount();i++){
			 Row row=sheet.createRow(i+2);
			 for (int j=0;j<11;j++){
			   Cell cel=row.createCell(j);
			   cel.setCellValue(table.getValueAt(i, j).toString());
			 }
			}
			fileOut=new FileOutputStream(dirpath+"/"+filename+".xlsx");
			workbook.write(fileOut);
			workbook.close();
			javax.swing.JOptionPane.showMessageDialog(null, "success");
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		}finally {
			if(fileOut != null){ 
				//fileOut.close();
			}
		}
	}
	
}
