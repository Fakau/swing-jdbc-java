package com.monptitcoin.vue;

import com.monptitcoin.Dao.EtudiantDao;
import com.monptitcoin.modele.Etudiant;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        EtudiantDao dao=new EtudiantDao();
        Etudiant e=new Etudiant();
        dao.save(e);
	}

}
