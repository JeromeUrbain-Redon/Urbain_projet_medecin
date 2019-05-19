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
public class Medicament {
    protected int idmedoc;
    protected String nom;
    protected String code;
    protected String description;
    
    //protected Set<Medicament>Medoc = new HashSet<>();
    
    private Medicament(MedicamentBuilder mb){
        this.idmedoc=mb.idmedoc;
        this.nom=mb.nom;
        this.code=mb.code;
        this.description=mb.description;
        
    }

    public int getIdmedoc() {
        return idmedoc;
    }

    public String getNom() {
        return nom;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Medicament{" + "idmedoc=" + idmedoc + ", nom=" + nom + ", code=" + code + ", description=" + description + '}';
    }
    
    
    
    public static class MedicamentBuilder{
        protected int idmedoc;
        protected String nom;
        protected String code;
        protected String description;
        
        public MedicamentBuilder setIdmedoc(int idmedoc){
            this.idmedoc=idmedoc;
            return this;
        }
        
        public MedicamentBuilder setNom(String nom){
            this.nom=nom;
            return this;
        }
        
        public MedicamentBuilder setCode(String code){
            this.code=code;
            return this;
        }
        
        public MedicamentBuilder setDescription(String description){
            this.description=description;
            return this;
        }
        
        public Medicament build() throws Exception{
            if(idmedoc<=0||nom==null||code==null||description==null)
                throw new Exception("Informations de construction incomplètes");
            return new Medicament(this);
        }
    }
    
    
    
}
