/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.observer;
/**
 *
 * @author jerom
 */
public class Pharmacie {
    public static void main(String[] args) {
        Medecin med1 = new Medecin(1,"JU996","Urbain","Jérôme","0473/888999");
        Medecin med2 = new Medecin(2,"MP333","Poriaux","Michel","0498/123456");
        
        Patient pat1 = new Patient(1,"Erradi","Hicham","0485/556677");
        Patient pat2 = new Patient(2,"Andry","Xavier","0471/775533");
        
        med1.addObserver(pat1);
        med1.addObserver(pat2);
        med2.addObserver(pat1);
        
        med1.setTel("0474/444666");
        med2.setTel("0482/564278");
    }

}
