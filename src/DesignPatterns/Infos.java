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
public class Infos {
    protected int idinfo;
    protected int idmedoc;
    protected int quantite;
    protected int idpres;
    protected String unite;
        //protected Set<Infos>info = new HashSet<>();
        
    private Infos(InfosBuilder ib){
        this.idinfo=ib.idinfo;
        this.idmedoc=ib.idmedoc;
        this.idpres=ib.idpres;
        this.quantite=ib.quantite;
        this.unite=ib.unite;
    }

    public int getIdinfo() {
        return idinfo;
    }

    public int getIdmedoc() {
        return idmedoc;
    }

    public int getQuantite() {
        return quantite;
    }

    public int getIdpres() {
        return idpres;
    }

    public String getUnite() {
        return unite;
    }

    @Override
    public String toString() {
        return "Infos{" + "idinfo=" + idinfo + ", idmedoc=" + idmedoc + ", quantite=" + quantite + ", idpres=" + idpres + ", unite=" + unite + '}';
    }
    
    
    
    public static class InfosBuilder{
        protected int idinfo;
        protected int idmedoc;
        protected int quantite;
        protected int idpres;
        protected String unite;
        
        public InfosBuilder setIdinfo(int idinfo){
            this.idinfo=idinfo;
            return this;
        }
        
        public InfosBuilder setIdmedoc(int idmedoc){
            this.idmedoc=idmedoc;
            return this;
        }
        
        public InfosBuilder setIdpres(int idpres){
            this.idpres=idpres;
            return this;
        }
        
        public InfosBuilder setQuantite(int quantite){
            this.quantite=quantite;
            return this;
        }
        
        public InfosBuilder setUnite(String unite){
            this.unite=unite;
            return this;
        }
        
        public Infos build() throws Exception{
            if(idinfo<=0||idpres<=0||idmedoc<=0)
                throw new Exception("Informations de construction incomplètes \nUne prescription doit contenir au moins un médicament");
            if(quantite<=0)
                throw new Exception("Information de quantité erronée");
            return new Infos(this);
        }
    }
}

