/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jerom
 */
public class Patient {
    protected int idpat;
    protected String nom;
    protected String prenom;
    protected String tel;
    
    //protected Set<Patient>Pat = new HashSet<>();
    
    private Patient(PatientBuilder pb){
        this.idpat=pb.idpat;
        this.nom=pb.nom;
        this.prenom=pb.prenom;
        this.tel=pb.tel;
    }

    public int getIdpat() {
        return idpat;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return "Patient{" + "idpat=" + idpat + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + '}';
    }
    
    
    
    public static class PatientBuilder{
        protected int idpat;
        protected String nom;
        protected String prenom;
        protected String tel;
        
        public PatientBuilder setIdpat(int idpat){
            this.idpat=idpat;
            return this;
        }
        
        public PatientBuilder setNom(String nom){
            this.nom=nom;
            return this;
        }
        
        public PatientBuilder setPrenom(String prenom){
            this.prenom=prenom;
            return this;
        }
        
        public PatientBuilder setTel(String tel){
            this.tel=tel;
            return this;
        }
        
        public Patient build() throws Exception{
            if(idpat<=0||nom==null||prenom==null||tel==null)
                throw new Exception("Informations de construction incomplètes");
            return new Patient(this);
        }

    }
    
}
