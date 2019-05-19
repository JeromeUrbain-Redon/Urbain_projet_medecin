/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.builder;

import DesignPatterns.Medecin;

/**
 *
 * @author jerom
 */
public class MedecinBuilder {
    public static void main(String[] args) {
        try{
            Medecin med1 = new Medecin.MedecinBuilder().
            setIdmed(1).
            setNom("Toto").
            setPrenom("Bobo").
            setMatricule("44f8e").
            setTel("0487/654321").
            build();
            System.out.println(med1);
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Medecin med2 = new Medecin.MedecinBuilder().
            setIdmed(1).
            setNom("Toto").
            setPrenom("Bobo").
            setTel("0487/654321").
            build();
            System.out.println(med2);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
