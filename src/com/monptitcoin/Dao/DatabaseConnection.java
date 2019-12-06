package com.monptitcoin.Dao;

import java.sql.*;

import javax.swing.JOptionPane;


public class DatabaseConnection {

	public static Connection connexion;
   
	static{
    	try{
    		//com.mysql.cj.jdbc.Driver
    		//old  com.mysql.jdbc.Driver
    		Class.forName(com.mysql.cj.jdbc.Driver.class.getName());
    		connexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/inscription_inutech","root","emanagement");
    	    System.out.println("connection succeess");
    	}catch(Exception ex){
    		javax.swing.JOptionPane.showMessageDialog(null, "Erreur de connection avec la base de donnee","Dialog",JOptionPane.ERROR_MESSAGE);
    		System.out.println(ex.getMessage());
    	}
    }
    
	public static Connection getConnexion() {
		return connexion;
	}
    
   
    
}
