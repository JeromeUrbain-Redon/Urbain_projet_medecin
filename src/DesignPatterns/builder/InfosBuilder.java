/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignPatterns.builder;

import DesignPatterns.Infos;

/**
 *
 * @author jerom
 */
public class InfosBuilder {
    public static void main(String[] args){
        try{
            Infos info1 = new Infos.InfosBuilder().
            setIdinfo(1).
            setIdpres(1).
            setIdmedoc(1).
            setUnite("2 gelules").
            setQuantite(1).
            build();
            System.out.println(info1);
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Infos info2 = new Infos.InfosBuilder().
            setIdinfo(1).
            setIdpres(1).
            setUnite("2 gelules").
            setQuantite(1).
            build();
            System.out.println(info2);
        }catch(Exception e){
            System.out.println(e);
        }
        
        try{
            Infos info3 = new Infos.InfosBuilder().
            setIdinfo(1).
            setIdpres(1).
            setIdmedoc(1).
            setUnite("2 gelules").
            setQuantite(-1).
            build();
            System.out.println(info3);
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
