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
public class Patient extends Observer{

    /**
     * identifiant unique d'un patient
     */
    protected int idpat;
    
    /**
     * nom du patient
     */
    protected String nom;
    
    /**
     * prenom du patient
     */
    protected String prenom;
    
    /**
     * numero de telephone du patient
     */
    protected String tel;
    
    /**
     * Constructeur par défaut
     */
    
    public Patient(){
        
    }
    
    /**
     * Constructeur paramétré
     * @param idpat
     * @param nom
     * @param prenom
     * @param tel
     */
    
    public Patient(int idpat,String nom,String prenom,String tel){
        this.idpat=idpat;
        this.nom=nom;
        this.prenom=prenom;
        this.tel=tel;
    }

    /**
     *
     * @return
     */
    public int getIdpat() {
        return idpat;
    }

    /**
     *
     * @param idpat
     */
    public void setIdpat(int idpat) {
        this.idpat = idpat;
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
    }

    @Override
    public String toString() {
        return "Patient{" + "idpat=" + idpat + ", nom=" + nom + ", prenom=" + prenom + ", tel=" + tel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idpat;
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
        final Patient other = (Patient) obj;
        return true;
    }


    
    @Override
    public void update(String msg) {
        System.out.println(prenom+" "+nom+" a reçu le msg :"+msg);
    }
}
