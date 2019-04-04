/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medecin.metier;

/**
 *
 * @author jerom
 */
public class Prescription {
    
    /**
     *
     */
    protected int idpres;
    
    /**
     *
     */
    protected String datepres;
    
    /**
     *
     */
    protected int idmed;
    
    /**
     *
     */
    protected int idpat;
    
    /**
     * Constructeur par défaut
     */
    
    public Prescription(){
        
    }
    
    /**
     * Constructeur paramétré
     * @param idpres
     * @param datepres
     * @param idmed
     * @param idpat
     */
    
    public Prescription(int idpres,String datepres,int idmed,int idpat){
        this.idpres=idpres;
        this.datepres=datepres;
        this.idmed=idmed;
        this.idpat=idpat;
    }

    /**
     *
     * @return
     */
    public int getIdpres() {
        return idpres;
    }

    /**
     *
     * @param idpres
     */
    public void setIdpres(int idpres) {
        this.idpres = idpres;
    }

    /**
     *
     * @return
     */
    public String getDatepres() {
        return datepres;
    }

    /**
     *
     * @param datepres
     */
    public void setDatepres(String datepres) {
        this.datepres = datepres;
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

    @Override
    public String toString() {
        return "Prescription{" + "idpres=" + idpres + ", datepres=" + datepres + ", idmed=" + idmed + ", idpat=" + idpat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.idpres;
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
        final Prescription other = (Prescription) obj;
        return true;
    }
    
    
}
