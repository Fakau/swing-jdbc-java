package com.monptitcoin.controller;

import javax.swing.JOptionPane;

import com.monptitcoin.Dao.EtudiantDao;
import com.monptitcoin.Dao.PaiementDao;
import com.monptitcoin.modele.Etudiant;
import com.monptitcoin.modele.Paiement;

public class PaiementControlleur {
	PaiementDao dao=new PaiementDao();
	EtudiantDao etDao=new EtudiantDao();
	
	public Etudiant trouverEtudiant(String code){
		Etudiant et=new Etudiant();
		et=dao.getByCode(code);
		if(et.getCode()==null || et.getCode()==""){
			javax.swing.JOptionPane.showMessageDialog(null, "Etudiant non trouver");
		}
		return et;
	}
	
	public Paiement save(Paiement pm, int id){
		Etudiant e=new Etudiant();
		e=etDao.getById(id);
		if(pm.getDescription().trim().equals("") || pm.getNumeroRecu().trim().equals("") || pm.getMontant() <= 0 ){
			javax.swing.JOptionPane.showMessageDialog(null, "Champs invalide", "Erreur remplissage", JOptionPane.ERROR_MESSAGE);
		}else{
			pm.setEtudiant(e);
		    dao.save(pm);
			javax.swing.JOptionPane.showMessageDialog(null, "Success");

		}
		
		return pm;
	}
	public void delete(int id){
		dao.delete(id);
		javax.swing.JOptionPane.showMessageDialog(null, "Success");

	}
	
	public void update(Paiement pm){
		if(pm.getDescription().trim().equals("") || pm.getNumeroRecu().trim().equals("") || pm.getMontant() <= 0  ){
			javax.swing.JOptionPane.showMessageDialog(null, "Champs invalide", "Erreur remplissage", JOptionPane.ERROR_MESSAGE);
		}else{
			dao.update(pm);
			javax.swing.JOptionPane.showMessageDialog(null, "Success");

		}
	}

}
