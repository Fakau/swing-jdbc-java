package com.monptitcoin.Dao;

import java.sql.*;

import javax.swing.JOptionPane;


public class DatabaseConnection {

	public static Connection connexion;
   
	static{
    	try{
    		//new com.mysql.cj.jdbc.Driver mysql v8
    		//old  com.mysql.jdbc.Driver mysql < v8
    		Class.forName(com.mysql.cj.jdbc.Driver.class.getName());
    		connexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/inu_app","root","");
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
