/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthtracker;

import HealthTrackerViews.*;
import java.sql.*;
/**
 *
 * @author Noo
 */
public class HealthTracker {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        
        try{
            Connection con=User.connectDB();     
            Statement stmt=con.createStatement();
            
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
            "id int NOT NULL auto_increment," +
            "username varchar(40) NOT NULL UNIQUE," +
            "firstname varchar(40) NOT NULL," +
            "lastname varchar(40) NOT NULL," +
            "email varchar(40) NOT NULL UNIQUE," +
            "pass varchar(40) NOT NULL," +
            "sex varchar(1) NOT NULL," +
            "birthdate date NOT NULL,"+
            "age int," +
            "height double," +
            "weight double," +
            "bmi double," +
            "weight_goal double," +
            "caloriesburnt double," +
            "  PRIMARY KEY (id)" +
            ")";
            stmt.execute(sql);
            
            
            String sql2 = "CREATE TABLE IF NOT EXISTS intake (" +
            "id int NOT NULL auto_increment," +
            "name varchar(40) NOT NULL," +
            "calories int," +
            "  PRIMARY KEY (id)" +
            ")";
            stmt.execute(sql2);
            
            String sql4 = "CREATE TABLE IF NOT EXISTS exercise (" +
            "id int NOT NULL auto_increment," +
            "user varchar(40) NOT NULL,"+
            "day date NOT NULL," +
            "weight double NOT NULL," +
            "caloriesburnt int," +
            "  PRIMARY KEY (id)" +
            ")";
            stmt.execute(sql4);
            
            
            String sql3 = "SELECT id from intake WHERE id = ? ";   
            PreparedStatement verifyEmpty = con.prepareStatement( sql3 );
            verifyEmpty.setInt( 1,1 );
            ResultSet set = verifyEmpty.executeQuery();
            set.next();
            try{ 
                Integer ahhhh = set.getInt(1);
                
            } catch(Exception e){ 
            
                String item1 = "INSERT INTO intake (name, calories) VALUES"+"(?,?)";
                PreparedStatement insertItem1 = con.prepareStatement( item1 );
                insertItem1.setString( 1, "Uncle Bens Boil in the Bag Rice" );
                insertItem1.setInt( 2, 215 );
                insertItem1.executeUpdate();
                
                String item2 = "INSERT INTO intake (name, calories) VALUES"+"(?,?)";
                PreparedStatement insertItem2 = con.prepareStatement( item2 );
                insertItem2.setString( 1, "Cheesy Wotsits" );
                insertItem2.setInt( 2, 95 );
                insertItem2.executeUpdate();
                
                String item3 = "INSERT INTO intake (name, calories) VALUES"+"(?,?)";
                PreparedStatement insertItem3 = con.prepareStatement( item3 );
                insertItem3.setString( 1, "Pringles" );
                insertItem3.setInt( 2, 279 );
                insertItem3.executeUpdate();
                
                String item4 = "INSERT INTO intake (name, calories) VALUES"+"(?,?)";
                PreparedStatement insertItem4 = con.prepareStatement( item4 );
                insertItem4.setString( 1, "White bread roll" );
                insertItem4.setInt( 2, 151 );
                insertItem4.executeUpdate();
                
                String item5 = "INSERT INTO intake (name, calories) VALUES"+"(?,?)";
                PreparedStatement insertItem5 = con.prepareStatement( item5 );
                insertItem5.setString( 1, "Uncle Bens Boil in the Bag Rice" );
                insertItem5.setInt( 2, 215 );
                insertItem5.executeUpdate();
                
                String item6 = "INSERT INTO intake (name, calories) VALUES"+"(?,?)";
                PreparedStatement insertItem6 = con.prepareStatement( item6 );
                insertItem6.setString( 1, "Hot Cross Buns" );
                insertItem6.setInt( 2, 155 );
                insertItem6.executeUpdate();
            }
            
             
 
            con.close();  
        }catch(Exception e){ System.out.println(e);}  
        
        
        
        
        // TODO code application logic here
        LoginView start= new LoginView();
        start.setVisible(true);
        
    }
    
}
