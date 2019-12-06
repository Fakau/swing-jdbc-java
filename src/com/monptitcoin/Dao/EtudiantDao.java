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

public class EtudiantDao implements IMetier<Etudiant> {

	private Connection con=DatabaseConnection.getConnexion();
	
	@Override
	public Etudiant save(Etudiant a) {
		PreparedStatement pstm=null;

        try {
			pstm=con.prepareStatement("insert into etudiant(code,nom,prenom,sexe,options,niveau,photo,montant,numero_recu,date_naissance,date_inscription) values (?,?,?,?,?,?,?,?,?,?,?)");
			
			pstm.setString(1, a.getCode());
			pstm.setString(2, a.getNom());
			pstm.setString(3, a.getPrenom());
			pstm.setString(4, a.getSexe());
			pstm.setString(5, a.getOption());
			pstm.setString(6, a.getNiveau());
			pstm.setBytes(7, a.getPhoto());
			pstm.setDouble(8, a.getMontant());
			pstm.setString(9, a.getNumeroRecu());
			pstm.setDate(10, new java.sql.Date(a.getDateNaissance().getTime()) );
			pstm.setDate(11, new java.sql.Date(new Date().getTime()) );

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
	public List<Etudiant> getAll() {
		PreparedStatement pstm=null;
		ResultSet rs;
		List<Etudiant> etus=new ArrayList<Etudiant>();

        try {
			pstm=con.prepareStatement("select * from etudiant");
			rs=pstm.executeQuery();
			while(rs.next()){
				Etudiant etu=new Etudiant();
				etu.setId(rs.getInt(1));
				etu.setCode(rs.getString(2));
				etu.setNom(rs.getString(3));
				etu.setPrenom(rs.getString(4));
			    etu.setSexe(rs.getString(5));
			    etu.setOption(rs.getString(6));
		        etu.setNiveau(rs.getString(7));
				etu.setMontant(rs.getDouble(9));
				etu.setNumeroRecu(rs.getString(10));
				etu.setDateNaissance(rs.getDate(11));
                etu.setCreationDate(rs.getDate(12));
				
				etus.add(etu);
			}
			

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de connection", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
		return etus;
	}

	@Override
	public Etudiant update(Etudiant a) {
		PreparedStatement pstm=null;

        try {
			pstm=con.prepareStatement("update etudiant set code=?, nom=?, prenom=?, sexe=?, options=?, niveau=?, photo=?, montant=?, numero_recu=?, date_naissance=? where id=?");
			
			pstm.setString(1, a.getCode());
			pstm.setString(2, a.getNom());
			pstm.setString(3, a.getPrenom());
			pstm.setString(4, a.getSexe());
			pstm.setString(5, a.getOption());
			pstm.setString(6, a.getNiveau());
			pstm.setBytes(7, a.getPhoto());
			pstm.setDouble(8, a.getMontant());
			pstm.setString(9, a.getNumeroRecu());
			pstm.setDate(10, new java.sql.Date(a.getDateNaissance().getTime()) );
			pstm.setInt(11, a.getId());
            pstm.execute();
			
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
			pstm=con.prepareStatement("delete from etudiant where id= '"+id+"'  ");
			pstm.execute();
		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de suppression", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
	}

	@Override
	public Etudiant getById(Integer id) {
		PreparedStatement pstm=null;
		ResultSet rs;
		Etudiant etu=new Etudiant();

        try {
			pstm=con.prepareStatement("select * from etudiant where id=? ");
			pstm.setInt(1,id);
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
	
	public Etudiant getByCode(String code) {
		PreparedStatement pstm=null;
		ResultSet rs;
		Etudiant etu=new Etudiant();

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
	
	public List<Etudiant> getByName(String name) {
		PreparedStatement pstm=null;
		ResultSet rs;
		List<Etudiant> etus=new ArrayList<Etudiant>();

        try {
			pstm=con.prepareStatement("select * from etudiant where nom like ? or prenom like ? ");
			pstm.setString(1, "%"+name+"%");
			pstm.setString(2, "%"+name+"%");
			rs=pstm.executeQuery();
			while(rs.next()){
				Etudiant etu=new Etudiant();
				etu.setId(rs.getInt(1));
				etu.setCode(rs.getString(2));
				etu.setNom(rs.getString(3));
				etu.setPrenom(rs.getString(4));
			    etu.setSexe(rs.getString(5));
			    etu.setOption(rs.getString(6));
		        etu.setNiveau(rs.getString(7));
				etu.setMontant(rs.getDouble(9));
				etu.setNumeroRecu(rs.getString(10));
				etu.setDateNaissance(rs.getDate(11));
                etu.setCreationDate(rs.getDate(12));
				
				etus.add(etu);
			}
			

		} catch (Exception e) {
			javax.swing.JOptionPane.showMessageDialog(null, "Erreur de connection", "Dialog", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			pstm=null;
		}
		return etus;
	}

	public byte[] getImageByte(int id){
		Blob imgBlob;
		byte[] imgByte=null;
		PreparedStatement pstm=null;
		ResultSet rs;
		 try {
			pstm=con.prepareStatement("select photo from etudiant where id='"+id+"' ");
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
