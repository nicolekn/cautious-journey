/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthTrackerControllers;
import healthtracker.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 *
 * @author ksg16pru
 */
public class IntakeController {
    private Intake intake;
    public IntakeController()
    {
        
    }
    public IntakeController(Intake i)
    {
        this.intake=i;
    }
    public void insertIntake(User u) throws SQLException, ClassNotFoundException
    {
        try{
            //connection not working
            Connection con=User.connectDB();  
            //Statement stmt=con.createStatement(); 
            
            String sql = "INSERT INTO intake (name,calories) VALUES"+"(?,?)";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertIntake =con.prepareStatement(sql);
            insertIntake.setString(1,intake.getName());
            insertIntake.setDouble(2, intake.getCalories());



            
            insertIntake.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);}
        
        
    }
    public static String[] getIntakeArray()
    {
        ArrayList<String> intakeNames = new ArrayList<String>();
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT name from intake";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            ResultSet set = verifyLogin.executeQuery();

            
            while(set.next())
            {
                intakeNames.add(set.getString("name"));
            }
            
            con.close();  
            
        }catch(Exception e){ System.out.println(e);}
        return intakeNames.toArray(new String[intakeNames.size()]);
    }
}

