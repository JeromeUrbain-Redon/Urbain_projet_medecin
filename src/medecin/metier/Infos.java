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
public class Infos {
    
    /**
     *
     */
    protected int idinfo;
    
    /**
     *
     */
    protected int idpres;
    
    /**
     *
     */
    protected int idmedoc;
    
    /**
     *
     */
    protected int quantite;
    
    /**
     *
     */
    protected String unite;
    
    /**
     * Constricteur par défaut
     */
           
    
    public Infos(){
        
    }
    
    /**
     * Constructeur paramétré
     * @param idinfo
     * @param idpres
     * @param idmedoc
     * @param quantite
     * @param unite
     */
    
    public Infos(int idinfo,int idpres,int idmedoc,int quantite,String unite){
        this.idinfo=idinfo;
        this.idpres=idpres;
        this.idmedoc=idmedoc;
        this.quantite=quantite;
        this.unite=unite;
    }

    /**
     *
     * @return
     */
    public int getIdinfo() {
        return idinfo;
    }

    /**
     *
     * @param idinfo
     */
    public void setIdinfo(int idinfo) {
        this.idinfo = idinfo;
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
    public int getIdmedoc() {
        return idmedoc;
    }

    /**
     *
     * @param idmedoc
     */
    public void setIdmedoc(int idmedoc) {
        this.idmedoc = idmedoc;
    }

    /**
     *
     * @return
     */
    public int getQuantite() {
        return quantite;
    }

    /**
     *
     * @param quantite
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    /**
     *
     * @return
     */
    public String getUnite() {
        return unite;
    }

    /**
     *
     * @param unite
     */
    public void setUnite(String unite) {
        this.unite = unite;
    }

    @Override
    public String toString() {
        return "Infos{" + "idinfo=" + idinfo + ", idpres=" + idpres + ", idmedoc=" + idmedoc + ", quantite=" + quantite + ", unite=" + unite + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + this.idinfo;
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
        final Infos other = (Infos) obj;
        return true;
    }
    
    
    
}
