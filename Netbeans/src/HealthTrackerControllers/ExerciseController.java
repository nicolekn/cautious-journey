/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthTrackerControllers;

import healthtracker.Exercise;
import healthtracker.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ksg16pru
 */
public class ExerciseController {
    private Exercise exercise;
    public ExerciseController()
    {
        
    }
    public ExerciseController(Exercise e)
    {
        this.exercise=e;
    }
    public void insertExercise(User u) throws SQLException, ClassNotFoundException
    {
        java.sql.Date date= new java.sql.Date(exercise.getDate().getTime());
        try{
            //connection not working
            Connection con=User.connectDB();  
            //Statement stmt=con.createStatement(); 
            
            String sql = "INSERT INTO exercise (user, day, weight, caloriesburnt) VALUES"+"(?,?,?,?)";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setString(1,u.getUserName());
            insertUser.setDate(2, date);
            insertUser.setDouble(3, u.getWeight());
            insertUser.setDouble(4, Exercise.calculateCaloriesBurnt(exercise.getIntensity(),exercise.getDuration(),u));


            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);}
        
        
    }
}
