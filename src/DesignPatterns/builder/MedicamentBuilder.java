/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.builder;

import DesignPatterns.Medicament;
     
/**
 *
 * @author jerom
 */
public class MedicamentBuilder {
    public static void main(String[] args){
        try{
            Medicament medoc1 = new Medicament.MedicamentBuilder().
            setIdmedoc(1).
            setNom("Xanax").
            setCode("xa455").
            setDescription("Anti depresseur").
            build();
            System.out.println(medoc1);
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Medicament medoc2 = new Medicament.MedicamentBuilder().
            setIdmedoc(1).
            setNom("Xanax").
            setDescription("Anti depresseur").
            build();
            System.out.println(medoc2);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
