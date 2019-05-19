/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.builder;

import DesignPatterns.Prescription;


/**
 *
 * @author jerom
 */
public class PrescriptionBuilder {
    
    
    public static void main(String[] args) {
        try{
            Prescription pres1 = new Prescription.PrescriptionBuilder().
            setIdpres(1).
            setIdmed(1).
            setIdpat(1).
            setDatepres("01/01/2000").
            build();
            System.out.println(pres1);
        }catch (Exception e) {
            System.out.println("erreur "+e);
        }
        
        try{
            Prescription pres2 = new Prescription.PrescriptionBuilder().
            setIdpres(1).
            setIdmed(1).
            setDatepres("01/01/2000").
            build();
            System.out.println(pres2);
        }catch (Exception e) {
            System.out.println("erreur "+e);
        }

    }

}
