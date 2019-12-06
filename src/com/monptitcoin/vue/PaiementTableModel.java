/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monptitcoin.vue;

import java.util.List;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import com.monptitcoin.modele.Etudiant;
import com.monptitcoin.modele.Paiement;

/**
 *
 * @author Fakau T.M
 */
public class PaiementTableModel   extends  AbstractTableModel{

   private String titreTable[]=new String[]{"Id","Description","Annee academique","Montant","Numero recu","Date paiement","Code","Nom","Prenom"};
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

	public void load(List<Paiement> paiement){
		size=new Vector<String[]>();
		for(Paiement c:paiement){
		 size.add(new String[]{String.valueOf(c.getId()), c.getDescription(), c.getAnneeAcademique(),String.valueOf(c.getMontant()),c.getNumeroRecu(),String.valueOf(c.getDatePaiement()),c.getEtudiant().getCode(),c.getEtudiant().getNom(),c.getEtudiant().getPrenom()});
		}
		fireTableChanged(null);
	}
    
}
