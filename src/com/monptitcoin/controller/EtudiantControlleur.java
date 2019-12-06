package com.monptitcoin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.monptitcoin.Dao.EtudiantDao;
import com.monptitcoin.modele.Etudiant;

public class EtudiantControlleur {
	 EtudiantDao dao=new EtudiantDao();
	 
	public void save(Etudiant e){
		if(e.getCode().trim().equals("") || e.getNom().trim().equals("") || e.getPrenom().trim().equals("") || e.getMontant() <= 0 || e.getOption().trim().equals("") || e.getDateNaissance().toString().equals("")){
			javax.swing.JOptionPane.showMessageDialog(null, "Champs invalide", "Erreur remplissage", JOptionPane.ERROR_MESSAGE);
		}else{
			if(e.getNumeroRecu().isEmpty()){
				e.setNumeroRecu("montant cash");
			}
		    dao.save(e);
			javax.swing.JOptionPane.showMessageDialog(null, "Success");

		}
	}
	
	public void importExcelFile(File file ){
		FileInputStream filein;
		 byte[] buffer=new byte[64];
		try{
			if(file.exists() && file.canRead()){
				filein=new FileInputStream(file);
				XSSFWorkbook work=new XSSFWorkbook(filein);
				Sheet sheet=work.getSheet("feuille1");
				Row row=null;
				for (int i=0;i<=sheet.getLastRowNum();i++){
					 row=sheet.getRow(i+3);
					 Etudiant e=new Etudiant();
					 e.setCode(row.getCell(1).toString());
					 e.setNom(row.getCell(2).toString());
					 e.setPrenom(row.getCell(3).toString());
					 e.setSexe(row.getCell(4).toString());
					 e.setOption(row.getCell(5).toString());
					 e.setNiveau(row.getCell(6).toString());
					 e.setMontant(Double.parseDouble(row.getCell(7).toString()));
					 e.setNumeroRecu(row.getCell(8).toString());
					 
					 e.setDateNaissance(new Date());
					 e.setCreationDate(new Date());
				     dao.save(e);
			  }
				 
				work.close();
				
				
			}else{
				System.out.println("file not exist or can't read");
			}
		}catch(Exception e){
			System.out.print(e.getMessage());
		}

	}
	
	public void delete(Integer id ){
		try{
		 dao.delete(id);
		 javax.swing.JOptionPane.showMessageDialog(null, "Suppression success");
		}catch(Exception ex){
			
		}
	}
	
	public void update(Etudiant e){
		
		if(e.getCode().trim().equals("") || e.getNom().trim().equals("") || e.getPrenom().trim().equals("") || e.getMontant() <= 0 || e.getOption().trim().equals("") || e.getDateNaissance().toString().equals("")){
			javax.swing.JOptionPane.showMessageDialog(null, "Champs invalide", "Erreur remplissage", JOptionPane.ERROR_MESSAGE);
		}else{
			if(e.getNumeroRecu().isEmpty()){
				e.setNumeroRecu("montant cash");
			}
			dao.update(e);
			javax.swing.JOptionPane.showMessageDialog(null, " modification success");

		}
		
	}
	
	public List<Etudiant> getByName(String nom){
		return dao.getByName(nom);
	}
	
	public Etudiant getByCode(String code){
		Etudiant et=new Etudiant();
		if(code.length()>=0){
			et=dao.getByCode(code);
		}else{
			javax.swing.JOptionPane.showMessageDialog(null, "Etudiant non trouver","Dialog",JOptionPane.INFORMATION_MESSAGE);
		}
		return et;
	}

}
