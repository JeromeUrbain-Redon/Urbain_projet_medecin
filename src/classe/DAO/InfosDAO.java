/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe.DAO;

/**
 *
 * @author jerom
 */

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import medecin.metier.Infos;
import myconnections.DBConnection;

public class InfosDAO extends DAO<Infos>{
    
    Statement stmt;
    ResultSet rs = null;
    int id,idmedoc,idpres,quantite;
    String unite;

    @Override
    public Infos read(int idinfo) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "select * from infos where idinfo = ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(1, idinfo);
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {                    
                    int idmedoc = rs.getInt("IDMEDOC");
                    int idpat = rs.getInt("IDPRES");
                    int quantite = rs.getInt("QUANTITE");
                    String unite = rs.getString("UNITE");
                    System.out.println();
                    return new Infos(idinfo,idpres,idmedoc,quantite,unite);
                } else {
                    throw new SQLException("Code inconnu");
                }
            }
        }
    }

    @Override
    public Infos create(Infos obj) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req1 = "insert into infos(idinfo,idpres,idmedoc,quantite,unite) values(?,?,?,?,?)";
        String req2 = "select idinfo from infos where idpres=? and idmedoc= ?, and quantite=?, and unite=? ";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req1);
                PreparedStatement pstm2 = dbConnect.prepareStatement(req2)){
            
            pstm.setInt(1, obj.getIdpres());
            pstm.setInt(2, obj.getIdmedoc());
            pstm.setInt(3, obj.getQuantite());
            pstm.setString(4, obj.getUnite());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("erreur de creation prescription, aucune ligne créée");
            }
            pstm2.setInt(1, obj.getIdpres());
            pstm2.setInt(2, obj.getIdmedoc());
            pstm2.setInt(3, obj.getQuantite());
            pstm2.setString(4, obj.getUnite());
            try (ResultSet rs = pstm2.executeQuery()) {
                if (rs.next()) {
                    int idinfo = rs.getInt(1);
                    obj.setIdpres(idinfo);
                    return read(idinfo);
                } else {
                    throw new SQLException("erreur de création médicament, record introuvable");
                }
            }
        }
    }

    @Override
    public Infos update(Infos obj) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "update infos set idpres=? ,idmedoc=? ,quantite=? ,unite=? where idinfo= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {
            pstm.setInt(5, obj.getIdinfo());
            pstm.setInt(1, obj.getIdpres());
            pstm.setInt(2, obj.getIdmedoc());
            pstm.setInt(3, obj.getQuantite());
            pstm.setString(4,obj.getUnite());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne d'info mise à jour");
            }
            return read(obj.getIdinfo());

        }
    }

    @Override
    public void delete(Infos obj) throws SQLException {
        if (dbConnect == null) {
            System.exit(1);
        }
        String req = "delete from infos where idinfos= ?";
        try (PreparedStatement pstm = dbConnect.prepareStatement(req)) {

            pstm.setInt(1, obj.getIdpres());
            int n = pstm.executeUpdate();
            if (n == 0) {
                throw new SQLException("aucune ligne de d'info effacée");
            }

        }
    }
    
}
