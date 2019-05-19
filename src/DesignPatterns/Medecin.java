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
public class Medecin {
    protected int idmed;
    protected String matricule;
    protected String nom;
    protected String prenom;
    protected String tel;
    
    //protected Set<Medecin>Med = new HashSet<>();
    
    private Medecin(MedecinBuilder medb){
        this.idmed=medb.idmed;
        this.nom=medb.nom;
        this.prenom=medb.prenom;
        this.matricule=medb.matricule;
        this.tel=medb.tel;
    }

    public int getIdmed() {
        return idmed;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    
    public String getMatricule() {
        return matricule;
    }


    public String getTel() {
        return tel;
    }

    @Override
    public String toString() {
        return "Medecin{" + "idmed=" + idmed + ", matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + '}';
    }
    
    
    
    public static class MedecinBuilder{
        protected int idmed;
        protected String matricule;
        protected String nom;
        protected String prenom;
        protected String tel;
        
        public MedecinBuilder setIdmed(int idmed){
            this.idmed=idmed;
            return this;
        }
        
        public MedecinBuilder setNom(String nom){
            this.nom=nom;
            return this;
        }
        
        public MedecinBuilder setPrenom(String prenom){
            this.prenom=prenom;
            return this;
        }
        
        public MedecinBuilder setMatricule(String matricule){
            this.matricule=matricule;
            return this;
        }    
        
        public MedecinBuilder setTel(String tel){
            this.tel=tel;
            return this;
        }
        
        public Medecin build() throws Exception{
            if(idmed<=0||matricule==null||nom==null||prenom==null||tel==null)
                throw new Exception("Informations de construction incomplètes");
            return new Medecin(this);
        }
    }
    
    
    
    
}
