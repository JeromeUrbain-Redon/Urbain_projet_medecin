/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package urbain_projet_medecin;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import medecin.metier.Prescription;
import classe.DAO.PrescriptionDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
 
public class test {
 
 
	//	* Choix de la langue francaise
	static Locale locale = Locale.getDefault();
	static Date actuelle = new Date();
 
	//	* Definition du format utilise pour les dates
	static DateFormat dateFormat =
                 new SimpleDateFormat("dd/MM/YY");
 
	//	* Donne la date au format "aaaa-mm-jj"
	public static String date()
	{
		String dat = dateFormat.format(actuelle);
		return dat;
	}
 
	public static void main(String args[]) {
		//TutorialConnect1 app = new TutorialConnect1();
                
                String d = date();
		System.out.println(d);
                String datepres;
                Statement stmt;
                ResultSet rs = null;
                int id,idmed,idpat;
                Prescription pres;
                PrescriptionDAO pdao = new PrescriptionDAO();
                id=0;
                idmed=2;
                idpat=2;
                datepres=date();
                pres=new Prescription(id,datepres,idmed,idpat);
                try{
                pres=pdao.create(pres);
                }catch(SQLException e){
                    System.out.println(e);
                }
	}
}