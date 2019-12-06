package com.monptitcoin.modele;

import java.util.Date;

public class Paiement {
	private Integer id;
	private Etudiant etudiant;
	private String anneeAcademique;
	private String description;
	private double montant;
	private String numeroRecu;
	private Date datePaiement;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	} 
	public Etudiant getEtudiant() {
		return etudiant;
	}
	public void setEtudiant(Etudiant etudiant) {
		this.etudiant = etudiant;
	}
	public String getAnneeAcademique() {
		return anneeAcademique;
	}
	public void setAnneeAcademique(String anneeAcademique) {
		this.anneeAcademique = anneeAcademique;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getNumeroRecu() {
		return numeroRecu;
	}
	public void setNumeroRecu(String numeroRecu) {
		this.numeroRecu = numeroRecu;
	}
	public Date getDatePaiement() {
		return datePaiement;
	}
	public void setDatePaiement(Date datePaiement) {
		this.datePaiement = datePaiement;
	}

	
	

}
