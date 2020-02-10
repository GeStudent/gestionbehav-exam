/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.gestudent.services;

import edu.gestudent.entities.Behaviour;
import edu.gestudent.utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class behaviourCRUD {
    
    Connection con;
     Statement ste;
    
    public behaviourCRUD() {
          con = DataBase.getInstance().getConnection();
    }
    
     public void ajouterbeh(Behaviour b) {
          String requete2= "INSERT INTO behaviour (attendance,award)VALUES (?,?)";
         
        try {
             PreparedStatement pst = con.prepareStatement(requete2);
//            pre = con.prepareStatement("INSERT INTO behaviour (attendance,award)VALUES (?,?)");
            pst.setInt(1, b.getAttendance());
            pst.setInt(2,b.getAward());
        } catch (SQLException ex) {
          System.out.println(ex.getMessage());
        }
           

    }
         public boolean updateaward(String idcode, String image) throws SQLException {

        PreparedStatement pre = con.prepareStatement("update behaviour set image =? where idcode=? ;");
        pre.setString(1, image);
        pre.setString(2, idcode);
        if (pre.executeUpdate() != 0) {
            System.out.println("user's image is up to date");
            return true;
        }
        System.out.println("id user not found!!!");
        return false;

    }
    public List<Behaviour> afficherBehaviour(){
        ArrayList<Behaviour> per =new ArrayList();

        try {
                    String requete3 = "SELECT * FROM behaviour";
            PreparedStatement pst2 = con.prepareStatement(requete3);
            ResultSet rs = pst2.executeQuery();
            
            while(rs.next()){
              Behaviour b = new Behaviour();
              b.setIdbeh(rs.getInt("id"));
              b.setAttendance(rs.getInt("1"));
              b.setAward(rs.getInt("2"));
            
              per.add(b);
            }
        } catch (SQLException ex) {
                  System.out.println(ex.getMessage());
        }
        return per;
    }
}
