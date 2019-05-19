/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.observer;

/**
 *
 * @author jerom
 */
public class Medecin extends Subject{

   protected int idmed;
    
    /**
     * matricule alphanumérique du medecin
     */
    protected String matricule;
    
    /**
     * nom du medecin
     */
    protected String nom;
    
    /**
     * prenom du medecin
     */
    protected String prenom;
    
    /**
     * numero de telephone du medecin
     */
    protected String tel;
    
    /**
     * Constructeur par défaut
     */
        
    public Medecin(){
        
    }
    
    /**
     * Constructeur paramétré
        * @param idmed identifiant unique du medecin
        * @param matricule matricule alphanumérique du medecin
        * @param nom nom du medecin
        * @param prenom prenom du medecin
        * @param tel numéro de telephone du medecin
    
    */
    
    public Medecin(int idmed,String matricule,String nom,String prenom,String tel){
        this.idmed=idmed;
        this.matricule=matricule;
        this.nom=nom;
        this.prenom=prenom;
        this.tel=tel;
    }

    /**
     *
     * @return
     */
    public int getIdmed() {
        return idmed;
    }

    /**
     *
     * @param idmed
     */
    public void setIdmed(int idmed) {
        this.idmed = idmed;
    }

    /**
     *
     * @return
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     *
     * @param matricule
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     *
     * @return
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     *
     * @param prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     *
     * @return
     */
    public String getTel() {
        return tel;
    }

    /**
     *
     * @param tel
     */
    public void setTel(String tel) {
        this.tel = tel;
        notifyObservers();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idmed;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Medecin other = (Medecin) obj;
        if (this.idmed != other.idmed) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return "Medecin{" + "idmed=" + idmed + ", matricule=" + matricule + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + '}';
    }


    @Override
    public String getNotification() {
        return "nouveau numero de Dr. "+nom+" "+prenom+" : "+tel;
    }
}
