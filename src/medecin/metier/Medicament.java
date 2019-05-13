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
public class Medicament {
    
    /**
     * Identifiant unique d'un medicament
     */
    
    protected int idmedoc;

    /**
     * Nom du medicament
     */
    
    protected String nom;
    
    /**
     * Description du medicament
     */
               
    protected String description;
    
    /**
     * Code unique du medicament
    */
    
    protected String code;
    
    /**
     * Constructeur par défaut
     */
    
    public Medicament(){
        
    }
    
    /**
     * Constructeur paramétré
     * @param idmedoc
     * @param nom
     * @param description
     * @param code
     */
    
    public Medicament(int idmedoc,String nom,String description,String code){
        this.idmedoc=idmedoc;
        this.nom=nom;
        this.description=description;
        this.code=code;
    }

    
    
    /**
     * Constructeur paramétré
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
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Medicament{" + "idmedoc=" + idmedoc + ", nom=" + nom + ", description=" + description + ", code=" + code + '}';
    }

    /**
     * 
     * @return 
     */
    
    public String getCode() {
        return code;
    }

    /**
     * 
     * @param code 
     */
    public void setCode(String code) {
        this.code = code;
    }

    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.idmedoc;
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
        final Medicament other = (Medicament) obj;
        return true;
    }
    
    
}
