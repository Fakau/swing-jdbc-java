/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monptitcoin.vue;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.monptitcoin.modele.Etudiant;

/**
 *
 * @author Fakau T.M
 */
public class EtudiantTableModel   extends  AbstractTableModel{

   private String titreTable[]=new String[]{"Id","Code","Nom","Prenom","Sexe","Option","Niveau","Montant","No recu","Date Naissance","Date inscription"};
   private Vector<String[]> size=new Vector<String[]>();
   
	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return titreTable[arg0];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return titreTable.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return size.size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return size.get(arg0)[arg1];
	}

	public void load(List<Etudiant> etudiant){
		size=new Vector<String[]>();
		for(Etudiant c:etudiant){
		 size.add(new String[]{String.valueOf(c.getId()), c.getCode(), c.getNom(),c.getPrenom(),c.getSexe(),c.getOption(),c.getNiveau(), String.valueOf(c.getMontant()), c.getNumeroRecu(), String.valueOf(c.getDateNaissance()), String.valueOf(c.getCreationDate())});
		}
		fireTableChanged(null);
	}
    
}
