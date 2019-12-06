/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monptitcoin.vue;

import java.awt.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.SystemColor;

import javax.swing.border.EtchedBorder;
import javax.swing.JSplitPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.table.DefaultTableModel;

import com.monptitcoin.Dao.EtudiantDao;
import com.monptitcoin.controller.EtudiantControlleur;

@SuppressWarnings("serial")
/**
 *
 * @author Fakau T.M
 */
public class VuePrincipale extends JFrame {
	JPanel panel = new JPanel();
	JPanel panelWest = new JPanel();
	JPanel panelFrameCenter = new JPanel(new BorderLayout());
	JPanel panelFrameCenterWest = new JPanel();
	public Panel mainPanelNorth = new Panel(new BorderLayout());

	public Panel mainPanelSouth = new Panel();
	public JPanel mainPanelWest = new JPanel();
	public Panel mainPanelCenter = new Panel(new BorderLayout());
	public JPanel paneCenter = new JPanel();

	private final JMenuBar menuBar = new JMenuBar();

	private JMenu menuFile = new JMenu("Fichier");
	private JMenu menuHelp = new JMenu("Aide");

	

	private final JToolBar toolBar = new JToolBar();
	private final JButton toolbarAddEtudiant = new JButton("Ajouter Postulant");
	private final JButton toolbarListEtudiant = new JButton("Liste etudiants");
	private final JButton toolbarAddPaiement = new JButton("Ajouter paienment");
	private final JButton toolbarListPaiement = new JButton("Liste paienment");
	private final JButton toolbarquiter = new JButton("Quitter");
	
	private final JMenuItem itemAddEtudiant = new JMenuItem("Ajouter etudiant");
	private final JMenuItem item_import = new JMenuItem("Import list etudiant");
	private final JMenuItem itemquit = new JMenuItem("Quitter");
	private final JMenuItem itemAddPaiement = new JMenuItem("Creer paiement");
	

	public VuePrincipale() {
		this.setVisible(true);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit
				.getDefaultToolkit().getScreenSize().height - 40);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Inu App");
		
		menuBar.add(menuFile);
		menuBar.add(menuHelp);
		
		itemAddEtudiant.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK | InputEvent.ALT_MASK));
		itemAddEtudiant.setIcon(new ImageIcon(VuePrincipale.class.getResource("/com/monptitcoin/vue/images/saveItem.png")));
		menuFile.add(itemAddEtudiant);
		
		itemAddPaiement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		itemAddPaiement.setIcon(new ImageIcon(VuePrincipale.class.getResource("/com/monptitcoin/vue/images/paiement.png")));
		menuFile.add(itemAddPaiement);
		
		menuFile.add(new JSeparator());
		
		item_import.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_MASK));
		item_import.setIcon(new ImageIcon(VuePrincipale.class.getResource("/com/monptitcoin/vue/images/import.png")));
		menuFile.add(item_import);
		
		menuFile.add(new JSeparator());
		
		itemquit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		itemquit.setIcon(new ImageIcon(VuePrincipale.class.getResource("/com/monptitcoin/vue/images/exit.png")));
		menuFile.add(itemquit);
		
		mainPanelNorth.add(menuBar, BorderLayout.NORTH);

		menuBar.setBackground(new java.awt.Color(41, 156, 210));
		menuBar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		menuBar.setForeground(new java.awt.Color(41, 156, 210));
		
		

		// MainPanelSouth
		mainPanelSouth.add(new JLabel("Monp'titcoin sa | Copyright 2018"));
		mainPanelSouth.setBackground(SystemColor.menu);
		mainPanelWest.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mainPanelWest.setForeground(SystemColor.activeCaptionText);

		mainPanelWest.setBackground(Color.WHITE);
		
		
		
		panelFrameCenter.add(panelFrameCenterWest, BorderLayout.WEST);
		
		Color colorSouth = new Color(181, 218, 255);
		Color colorWest = new Color(188, 168, 199);
		panel.setBackground(colorSouth);
		panelWest.setBackground(colorWest);
		panelWest.add(new Label());
		

		buildMenu();
		onLoad();
		paneCenter.setBorder(javax.swing.BorderFactory.createEtchedBorder());
		paneCenter.setLayout(null);

		mainPanelCenter.add(paneCenter, BorderLayout.CENTER);
		


		this.getContentPane().add(mainPanelNorth, BorderLayout.NORTH);

		mainPanelNorth.add(toolBar, BorderLayout.SOUTH);

		// toolbar
		toolBar.setRollover(true);
		toolBar.setMargin(new java.awt.Insets(0, 0, 20, 0));
		
		//toolbar button
		toolbarAddEtudiant.setIcon(new ImageIcon(getClass().getResource("/com/monptitcoin/vue/images/Add User-50.png")));
		toolbarListEtudiant.setIcon(new ImageIcon(getClass().getResource("/com/monptitcoin/vue/images/Report Card-50.png")));
		toolbarAddPaiement.setIcon(new ImageIcon(getClass().getResource("/com/monptitcoin/vue/images/Currency50.png")));
        toolbarListPaiement.setIcon(new ImageIcon(getClass().getResource("/com/monptitcoin/vue/images/Day50.png")));
        toolbarquiter.setIcon(new ImageIcon(getClass().getResource("/com/monptitcoin/vue/images/exit1.png")));

        
		panelFrameCenterWest.add(panelWest);
		toolBar.add(toolbarAddEtudiant);
		toolBar.add(toolbarListEtudiant);
		toolBar.add(new JToolBar.Separator());
		//toolBar.add(new JSeparator(JSeparator.VERTICAL));
		toolBar.add(toolbarAddPaiement);
		toolBar.add(toolbarListPaiement);
		toolBar.add(new JToolBar.Separator());
		toolBar.add(toolbarquiter);
		

		this.getContentPane().add(mainPanelSouth, BorderLayout.SOUTH);
		this.getContentPane().add(mainPanelWest, BorderLayout.WEST);
		this.getContentPane().add(mainPanelCenter, BorderLayout.CENTER);
	}

	private void buildMenu() {
		// ModelJtree tr=new ModelJtree();
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Menu principale");
		DefaultMutableTreeNode configuration = new DefaultMutableTreeNode(
				"Module etudiant");
		DefaultMutableTreeNode inscription = new DefaultMutableTreeNode(
				"Inscription");
		DefaultMutableTreeNode paiement = new DefaultMutableTreeNode(
				"Paiement");
		configuration.add(inscription);
		configuration.add(paiement);

		root.add(configuration);
		JTree tree = new JTree(root);
		
		paneCenter.add(new JPanel(){
			
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				switch (String.valueOf(e.getPath().getLastPathComponent())) {
				
				case "Inscription":
					final EtudiantPanel etudiantPanel = new EtudiantPanel();
					paneCenter.removeAll();
					paneCenter.repaint();
					etudiantPanel.setBounds(5, 5,
							etudiantPanel.getWidth(),
						    etudiantPanel.getHeight());
					paneCenter.add(etudiantPanel);

					
					break;
				case "Paiement":
					final PaiementPanel paiementPanel = new PaiementPanel();
					paneCenter.removeAll();
					paneCenter.repaint();
					paiementPanel.setBounds(5, 5,
							paiementPanel.getWidth(),
							paiementPanel.getHeight());
					paneCenter.add(paiementPanel);
					break;
				}
				System.out.println("test : "
						+ e.getPath().getLastPathComponent());
			}
		});
		mainPanelWest.add(tree);
		
		itemAddEtudiant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoad();
				
			}
		});
		itemquit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int a=javax.swing.JOptionPane.showConfirmDialog(null, "Voulez-vous quitter l'application", "Dialog", JOptionPane.YES_NO_OPTION);
				//javax.swing.JOptionPane.showMessageDialog(null, a+"test");
				if(a==0){
					System.exit(0);
				}
				
			}
		});
		
		toolbarquiter.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int a=javax.swing.JOptionPane.showConfirmDialog(null, "Voulez-vous quitter l'application", "Dialog", JOptionPane.YES_NO_OPTION);
				//javax.swing.JOptionPane.showMessageDialog(null, a+"test");
				if(a==0){
					System.exit(0);
				}
				
			}
		});
		
		toolbarAddEtudiant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoad();
			}
		});
		
		toolbarListEtudiant.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoad();
			}
		});
		
		toolbarAddPaiement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoadPaiement();
			}
		});
		
		toolbarListPaiement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoadPaiement();
			}
		});
		
		itemAddPaiement.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				onLoadPaiement();
			}
		});
		
		item_import.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				File file ;
				   JFileChooser chooser=new JFileChooser();
					int result = chooser.showOpenDialog(null);
					
					if (result == JFileChooser.APPROVE_OPTION) {
						
						try{
							EtudiantControlleur c=new EtudiantControlleur();
							EtudiantTableModel m=new EtudiantTableModel();
							EtudiantDao d=new EtudiantDao();
							file = chooser.getSelectedFile();
							c.importExcelFile(file );
							m.load(d.getAll());	
							
						}catch(Exception ex){
							
						}
					}
				
			}
		});
	}
	private void onLoad(){
		final EtudiantPanel etudiantPanel = new EtudiantPanel();
		paneCenter.removeAll();
		paneCenter.repaint();
		etudiantPanel.setBounds(5, 5,
				etudiantPanel.getWidth(),
			    etudiantPanel.getHeight());
		paneCenter.add(etudiantPanel);
	}
	private void onLoadPaiement(){
		final PaiementPanel paiementPanel = new PaiementPanel();
		paneCenter.removeAll();
		paneCenter.repaint();
		paiementPanel.setBounds(5, 5,
				paiementPanel.getWidth(),
				paiementPanel.getHeight());
		paneCenter.add(paiementPanel);
	} 
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		new VuePrincipale().repaint();
		//DatabaseConnection.getConnexion();
	}
}
