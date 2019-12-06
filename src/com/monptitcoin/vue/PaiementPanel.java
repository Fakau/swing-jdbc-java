/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monptitcoin.vue;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.monptitcoin.Dao.EtudiantDao;
import com.monptitcoin.Dao.PaiementDao;
import com.monptitcoin.controller.PaiementControlleur;
import com.monptitcoin.modele.Etudiant;
import com.monptitcoin.modele.Paiement;
import com.toedter.calendar.JDateChooser;

/**
 *
 * @author Fakau T.M
 */
public class PaiementPanel extends JPanel {
	private JTable table;
	private JLabel lblInscriptionDesPostulant;
	private JLabel lblListeDesPostulant;
	private JButton btnRechercher;
	private JTextField textField;
	private final JLabel field_id = new JLabel("Id");
	private final JTextField field_code = new JTextField();
	private final JLabel lab_code = new JLabel("Code");
	private final JComboBox field_annee_academique = new JComboBox();
	private final JLabel field_nom = new JLabel("Nom");
	private final JTextField field_montant = new JTextField();
	private final JLabel field_prenom = new JLabel("Prenom");
	private final JLabel lab_annee_academique = new JLabel("Montant");
	private final JTextField field_no_recu = new JTextField();
	private final JLabel lab_option = new JLabel("Description");
	private final JLabel lab_niveau = new JLabel("Annee academique");
	private final JLabel lab_date_naissance = new JLabel("Numero recu");
	private final JButton btn_print = new JButton("Imprimer la liste");
	private final JLabel panel_photo = new JLabel();
	private final JLabel lab_date_inscription = new JLabel("Date paiement");
	private final JButton btn_update = new JButton("");
	private final JButton btn_delete = new JButton("");
	private final JButton btn_clear = new JButton("");
	private final JButton btn_initialize = new JButton("");
	private final JLabel field_date_paiement = new JLabel("yyyy-mm-dd");
	private JTextField field_rechercher;
	private PaiementTableModel model = new PaiementTableModel();
	private JScrollPane sc;
	private JTextField field_desc;
	JButton btn_trouver_etu;
	PaiementControlleur controlleur = new PaiementControlleur();
	PaiementDao dao = new PaiementDao();
	EtudiantDao dao1 = new EtudiantDao();
	JButton btn_save;
	private final JLabel lblIdPaiement = new JLabel("Id paiement");
	private final JLabel field_id_paiement = new JLabel("Id");

	public PaiementPanel() {
		this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.setBounds(37, 26, 1174, 535);
		this.setLayout(null);

		lblInscriptionDesPostulant = new JLabel("Paiement des etudiants");
		lblInscriptionDesPostulant.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInscriptionDesPostulant.setBounds(477, 11, 271, 38);
		this.add(lblInscriptionDesPostulant);

		lblListeDesPostulant = new JLabel("Liste des paiements");
		lblListeDesPostulant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblListeDesPostulant.setBounds(197, 47, 175, 24);
		this.add(lblListeDesPostulant);

		table = new JTable(model);
		table.setAutoResizeMode(table.AUTO_RESIZE_OFF);
		sc = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sc.setBounds(23, 113, 530, 366);
		this.add(sc);

		btnRechercher = new JButton("Rechercher");
		btnRechercher.setIcon(new ImageIcon(EtudiantPanel.class
				.getResource("/com/monptitcoin/vue/images/search.png")));
		btnRechercher.setBounds(442, 79, 111, 23);
		this.add(btnRechercher);

		JLabel lblRechercherPostulant = new JLabel("Rechercher postulant");
		lblRechercherPostulant.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRechercherPostulant.setBounds(23, 82, 175, 24);
		this.add(lblRechercherPostulant);

		field_id.setBounds(748, 267, 46, 14);

		this.add(field_id);
		field_code.setColumns(10);
		field_code.setBounds(785, 80, 99, 20);

		this.add(field_code);
		lab_code.setBounds(748, 83, 36, 14);

		this.add(lab_code);
		field_annee_academique.setModel(new DefaultComboBoxModel(
				new String[] { "2006-2007", "2007-2008", "2008-2009",
						"2009-2010", "2010-2011", "2011-2012", "2012-2013",
						"2013-2014", "2014-2015", "2015-2016", "2016-2017",
						"2017-2018", "2018-2019" }));
		field_annee_academique.setBounds(748, 373, 192, 20);

		this.add(field_annee_academique);
		field_nom.setBounds(748, 289, 86, 14);

		this.add(field_nom);
		field_montant.setColumns(10);
		field_montant.setBounds(748, 404, 192, 20);

		this.add(field_montant);
		field_prenom.setBounds(748, 308, 86, 14);

		this.add(field_prenom);
		lab_annee_academique.setBounds(614, 407, 86, 14);

		this.add(lab_annee_academique);
		field_no_recu.setColumns(10);
		field_no_recu.setBounds(748, 435, 192, 20);

		this.add(field_no_recu);
		lab_option.setBounds(614, 470, 86, 14);

		this.add(lab_option);
		lab_niveau.setBounds(614, 376, 124, 14);

		this.add(lab_niveau);
		lab_date_naissance.setBounds(614, 438, 86, 14);

		this.add(lab_date_naissance);
		btn_print.setIcon(new ImageIcon(EtudiantPanel.class
				.getResource("/com/monptitcoin/vue/images/print.png")));
		btn_print.setBounds(419, 490, 134, 23);

		this.add(btn_print);
		panel_photo
				.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_photo.setBounds(748, 111, 192, 145);

		this.add(panel_photo);

		btn_save = new JButton("");
		btn_save.setToolTipText("Enregistrer");
		btn_save.setIcon(new ImageIcon(EtudiantPanel.class
				.getResource("/com/monptitcoin/vue/images/Save-50.png")));
		btn_save.setBounds(999, 82, 86, 61);
		this.add(btn_save);
		lab_date_inscription.setBounds(614, 499, 86, 14);

		this.add(lab_date_inscription);
		btn_update.setToolTipText("Editer");
		btn_update
				.setIcon(new ImageIcon(
						EtudiantPanel.class
								.getResource("/com/monptitcoin/vue/images/Edit Row-50 - Copy.png")));
		btn_update.setBounds(999, 154, 86, 61);

		this.add(btn_update);
		btn_delete.setToolTipText("Supprimer");
		btn_delete
				.setIcon(new ImageIcon(
						EtudiantPanel.class
								.getResource("/com/monptitcoin/vue/images/Delete-50 - Copy.png")));
		btn_delete.setBounds(999, 226, 86, 61);

		this.add(btn_delete);
		btn_clear.setToolTipText("Initialiser");
		btn_clear.setIcon(new ImageIcon(EtudiantPanel.class
				.getResource("/com/monptitcoin/vue/images/Refresh-50.png")));
		btn_clear.setBounds(999, 304, 86, 61);

		this.add(btn_clear);
		btn_initialize.setToolTipText("Rechercher tout");
		btn_initialize
				.setIcon(new ImageIcon(
						EtudiantPanel.class
								.getResource("/com/monptitcoin/vue/images/Search Property-50.png")));
		btn_initialize.setBounds(999, 387, 86, 61);

		this.add(btn_initialize);
		field_date_paiement.setBounds(748, 497, 192, 14);

		add(field_date_paiement);

		field_rechercher = new JTextField();
		field_rechercher.setBounds(207, 82, 225, 20);
		add(field_rechercher);
		field_rechercher.setColumns(10);

		btn_trouver_etu = new JButton("");
		btn_trouver_etu.setIcon(new ImageIcon(PaiementPanel.class
				.getResource("/com/monptitcoin/vue/images/search.png")));
		btn_trouver_etu.setBounds(892, 79, 46, 23);
		add(btn_trouver_etu);

		field_desc = new JTextField();
		field_desc.setColumns(10);
		field_desc.setBounds(748, 466, 192, 20);
		add(field_desc);
		lblIdPaiement.setBounds(614, 342, 124, 14);
		
		add(lblIdPaiement);
		field_id_paiement.setBounds(748, 342, 124, 14);
		
		add(field_id_paiement);
		event();
		onStart();
	}

	private void event() {
		btn_clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});

		btn_initialize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				onSearch();
			}
		});

		btn_trouver_etu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String code = field_code.getText().trim();
				if (code.length() > 0) {
					Etudiant find = controlleur.trouverEtudiant(code);
					field_id.setText(String.valueOf(find.getId()));
					field_nom.setText(find.getNom());
					field_prenom.setText(find.getPrenom());
					panel_photo.setIcon(new ImageIcon(find.getPhoto()));

					field_no_recu.setEnabled(true);
					field_desc.setEnabled(true);
					field_montant.setEnabled(true);
				} else {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Code invalide", "Dialog",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		field_montant.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
				char key = arg0.getKeyChar();
				if (key == '.' || key == '0' || key == '1' || key == '2'
						|| key == '3' || key == '4' || key == '5' || key == '6'
						|| key == '7' || key == '8' || key == '9') {
					if (field_montant.getText() == null
							|| field_montant.getText().equals("") && key == '.') {
						arg0.consume();
					}
					if (field_montant.getText().contains(".") && key == '.') {
						arg0.consume();
					}
				} else {
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
				int a = javax.swing.JOptionPane.showConfirmDialog(null,
						"Voulez-vous supprimer cette ligne", "Dialog",
						JOptionPane.YES_NO_OPTION);

				if (a == 0) {
					 Integer id=Integer.parseInt(field_id_paiement.getText());
					 controlleur.delete(id); 
					 model.load(dao.getAll());
					 clear();
				}

			}
		});
		btn_save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (field_id.getText().equals("Id")) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Saisir le code de l'etudiant", "Dialog",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				Integer id = Integer.parseInt(field_id.getText());
				Paiement p = new Paiement();
				if (field_montant.getText().trim().length() == 0
						|| field_desc.getText().trim().length() == 0
						|| field_no_recu.getText().trim().length() == 0) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Champs vide", "Dialog", JOptionPane.ERROR_MESSAGE);

				} else {
					p.setMontant(Double.parseDouble(field_montant.getText()));
					p.setDescription(field_desc.getText());
					p.setAnneeAcademique(field_annee_academique
							.getSelectedItem().toString());
					p.setNumeroRecu(field_no_recu.getText());
					controlleur.save(p, id);
					clear();
					model.load(dao.getAll());

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
		btn_print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MessageFormat header = new MessageFormat("Liste des paiement");
				MessageFormat footer = new MessageFormat(
						"Page{0,number,integer}");

				try {
					table.print(JTable.PrintMode.NORMAL, header, footer);
				} catch (Exception e) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Erreur d'impression");
					System.out.println(e.getMessage());
				}

			}
		});
		
		btn_update.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int id=Integer.parseInt(field_id_paiement.getText());
				Paiement p = new Paiement();
				if (field_montant.getText().trim().length() == 0
						|| field_desc.getText().trim().length() == 0
						|| field_no_recu.getText().trim().length() == 0) {
					javax.swing.JOptionPane.showMessageDialog(null,
							"Champs vide", "Dialog", JOptionPane.ERROR_MESSAGE);

				} else {
					p.setId(id);
					p.setMontant(Double.parseDouble(field_montant.getText()));
					p.setDescription(field_desc.getText());
					p.setAnneeAcademique(field_annee_academique
							.getSelectedItem().toString());
					p.setNumeroRecu(field_no_recu.getText());
					controlleur.update(p);
					clear();
					model.load(dao.getAll());

				}
				
				
			}
		});

	}

	private void clear() {
		field_prenom.setText("Prenom");
		field_nom.setText("Nom");
		field_id.setText("Id");

		field_no_recu.setText("");
		field_no_recu.setEnabled(false);
		field_desc.setText("");
		field_desc.setEnabled(false);
		field_montant.setText("");
		field_montant.setEnabled(false);
		field_annee_academique.setEditable(false);
		panel_photo.setIcon(new ImageIcon(EtudiantPanel.class
				.getResource("/com/monptitcoin/vue/images/default.jpg")));
		btn_update.setEnabled(false);
		btn_delete.setEnabled(false);
		field_date_paiement.setText("yyyy-mm-dd");

	} 

	private void onStart() {
		btn_update.setEnabled(false);
		btn_delete.setEnabled(false);
		panel_photo.setIcon(new ImageIcon(EtudiantPanel.class
				.getResource("/com/monptitcoin/vue/images/default.jpg")));
		clear();
		btn_print.setEnabled(true);
		model.load(dao.getAll());
	}

	private void onSearch() {
		btn_print.setEnabled(true);
	}

	public void getRowFromTable() {
		int row = table.getSelectedRow();
		field_id_paiement.setText(table.getValueAt(row, 0).toString());
		field_desc.setText(table.getValueAt(row, 1).toString());
		field_annee_academique.setSelectedItem(table.getValueAt(row, 2)
				.toString());
		field_montant.setText(table.getValueAt(row, 3).toString());
		field_no_recu.setText(table.getValueAt(row, 4).toString());
		field_date_paiement.setText(table.getValueAt(row, 5).toString());
		field_code.setText(table.getValueAt(row, 6).toString());
		field_nom.setText(table.getValueAt(row, 7).toString());
		field_prenom.setText(table.getValueAt(row, 8).toString());

		field_no_recu.setEnabled(true);
		field_desc.setEnabled(true);
		field_montant.setEnabled(true);

		try {
			byte[] imgByte = dao.getImageByte(table.getValueAt(row, 6)
					.toString());
			ImageIcon m = new ImageIcon(imgByte);
			panel_photo.setIcon(m);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		btn_update.setEnabled(true);
		btn_delete.setEnabled(true);
	}
}
