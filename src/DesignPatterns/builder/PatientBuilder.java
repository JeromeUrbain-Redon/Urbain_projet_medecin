/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.builder;

import DesignPatterns.Patient;

/**
 *
 * @author jerom
 */
public class PatientBuilder {
    public static void main(String[] args){
        try{
            Patient pat1 = new Patient.PatientBuilder().
            setIdpat(1).
            setNom("Momo").
            setPrenom("coco").
            setTel("0478/123456").
            build();
            System.out.println(pat1);
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Patient pat2 = new Patient.PatientBuilder().
            setIdpat(1).
            setPrenom("coco").
            setTel("0478/123456").
            build();
            System.out.println(pat2);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
