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
public class Prescription {

    protected int idpres;
    protected int idmed;
    protected int idpat;
    protected String datepres;
    
    //protected Set<Prescription>mesPres = new HashSet<>();
    
    private Prescription(PrescriptionBuilder presb){
        this.idpres=presb.idpres;
        this.idmed=presb.idmed;
        this.idpat=presb.idpat;
        this.datepres=presb.datepres;
    }

    public int getIdpres() {
        return idpres;
    }

    public int getIdmed() {
        return idmed;
    }

    public int getIdpat() {
        return idpat;
    }

    public String getDatepres() {
        return datepres;
    }

    @Override
    public String toString() {
        return "Prescription{" + "idpres=" + idpres + ", idmed=" + idmed + ", idpat=" + idpat + ", datepres=" + datepres + '}';
    }
    
    

    
    public static class PrescriptionBuilder{
        protected int idpres;
        protected int idmed;
        protected int idpat;
        protected String datepres;
        
        public PrescriptionBuilder setIdpres(int idpres){
            this.idpres=idpres;
            return this;
        }
        
        public PrescriptionBuilder setIdmed(int idmed){
            this.idmed=idmed;
            return this;
        }
        
        public PrescriptionBuilder setIdpat(int idpat){
            this.idpat=idpat;
            return this;
        }
        
        public PrescriptionBuilder setDatepres(String datepres){
            this.datepres=datepres;
            return this;
        }
        
        public Prescription build() throws Exception{
            if(idpres<=0||idmed<=0||idpat<=0)
                throw new Exception("Informations de construction incomplètes \nUne prescription doit obligatoirement avoir un medecin prescripteur et un patient faisant l'objet de cette prescription");
            return new Prescription(this);
        }
    }
    
}
