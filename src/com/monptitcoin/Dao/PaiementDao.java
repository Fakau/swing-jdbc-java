package com.monptitcoin.Dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.monptitcoin.modele.Etudiant;
import com.monptitcoin.modele.Paiement;

public class PaiementDao implements IMetier<Paiement>{
	
	private Connection con=DatabaseConnection.getConnexion();
	private EtudiantDao dao=new EtudiantDao();
	
	@Override
	public Paiement save(Paiement a) {
		PreparedStatement pstm=null;

        try {
			pstm=con.prepareStatement("insert into paiement(description,annee_academique,montant,numero_recu,date_paiement,id_etudiant) values (?,?,?,?,?,?)");
			
			pstm.setString(1, a.getDescription());
			pstm.setString(2, a.getAnneeAcademique());
			pstm.setDouble(3, a.getMontant());
			pstm.setString(4, a.getNumeroRecu());
			pstm.setInt(6, a.getEtudiant().getId());
			pstm.setDate(5, new java.sql.Date(new Date().getTime()) );

			pstm.execute();
			
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "duplication des informations", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println("message: "+e.getMessage());
			pstm=null;
			a=null;
		}
		return a;
	}

	@Override
	public List<Paiement> getAll() {
		PreparedStatement pstm=null;
		ResultSet rs;
		List<Paiement> pms=new ArrayList<Paiement>();

        try {
			pstm=con.prepareStatement("select * from paiement");
			rs=pstm.executeQuery();
			while(rs.next()){
				Paiement pm=new Paiement();
				pm.setId(rs.getInt(1));
				pm.setDescription(rs.getString(2));
				pm.setAnneeAcademique(rs.getString(3));
				pm.setMontant(rs.getDouble(4));
				pm.setNumeroRecu(rs.getString(5));
				pm.setDatePaiement(rs.getDate(6));
			    int idEtudiant=rs.getInt(7);
			    pm.setEtudiant(dao.getById(idEtudiant));
				pms.add(pm);
			}
			

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de connection", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
		return pms;
	}

	@Override
	public Paiement update(Paiement a) {
		PreparedStatement pstm=null;
		System.out.println("shit");
        try {
			pstm=con.prepareStatement("update paiement set description=?, annee_academique=?, montant=?, numero_recu=? where id=?");
			pstm.setString(1, a.getDescription());
			pstm.setString(2, a.getAnneeAcademique());
			pstm.setDouble(3, a.getMontant());
			pstm.setString(4, a.getNumeroRecu());
			pstm.setInt(5, a.getId());
			System.out.println("shit"+ a.getId());
			pstm.execute();
			System.out.println("shit");
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de connection", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
			a=null;
		}
		return a;
	}

	@Override
	public void delete(Integer id) {
		PreparedStatement pstm=null;

        try {
			pstm=con.prepareStatement("delete from paiement where id= '"+id+"'  ");
			pstm.execute();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de suppression", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
		
	}

	@Override
	public Paiement getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Paiement getByEtudiant(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	public Paiement getByAnneeAcademique(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Etudiant getByCode(String code) {
		PreparedStatement pstm=null;
		ResultSet rs;
		Etudiant etu=new Etudiant();
		Blob imgBlob;
		byte[] imgByte=null;

        try {
			pstm=con.prepareStatement("select * from etudiant where code=? ");
			pstm.setString(1,code);
			rs=pstm.executeQuery();
			if(rs.next()){
				etu=new Etudiant();
				etu.setId(rs.getInt(1));
				etu.setCode(rs.getString(2));
				etu.setNom(rs.getString(3));
				etu.setPrenom(rs.getString(4));
			    etu.setSexe(rs.getString(5));
			    etu.setOption(rs.getString(6));
		        etu.setNiveau(rs.getString(7));
			        imgBlob=rs.getBlob(8);
					imgByte=imgBlob.getBytes(1, (int) imgBlob.length());
				etu.setPhoto(imgByte);
				etu.setMontant(rs.getDouble(9));
				etu.setNumeroRecu(rs.getString(10));
				etu.setDateNaissance(rs.getDate(11));
                etu.setCreationDate(rs.getDate(12));
			}
			

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de connection", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
		return etu;
	}
	
	public byte[] getImageByte(String code){
		Blob imgBlob;
		byte[] imgByte=null;
		PreparedStatement pstm=null;
		ResultSet rs;
		 try {
			pstm=con.prepareStatement("select photo from etudiant where code='"+code+"' ");
			rs=pstm.executeQuery();
			if(rs.next()){
				imgBlob=rs.getBlob(1);
				imgByte=imgBlob.getBytes(1, (int) imgBlob.length());
			}
			

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de telechargment de l'image", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
		
		return imgByte;
	}

}
