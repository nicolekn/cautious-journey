 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HealthTrackerControllers;
import healthtracker.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import org.jfree.*;
import org.jfree.data.category.DefaultCategoryDataset;
import java.text.SimpleDateFormat;

/**
 *
 * @author ksg16pru & drk16ztu & thinking emoji
 */
public class UserController {
  private User user;

    public UserController() {
    }

    public UserController(User user) {
        this.user = user;
    }
  
    public void registerUser(String userName,String password,String firstName, String lastName,String email, String sex, double height, double weight,int birth_day, int birth_month,int birth_year)
    {
        System.out.println( "Registering user..." );
        System.out.println( "Username: " + userName );
        System.out.println( "Password: " + password );
        System.out.println( "First name: " + firstName );
        System.out.println( "Last name: " + lastName );
        System.out.println( "Email: " + email );
        System.out.println( "Sex: " + sex );
        System.out.println( "Height: " + height );
        System.out.println( "Weight: " + weight );
        System.out.println( "Day of birth: " + birth_day );
        System.out.println( "Month of birth: " + birth_month );
        System.out.println( "Year of birth: " + birth_year );

        try{
            String birthday = birth_year + "-" + birth_month + "-" + birth_day;
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf1.parse( birthday );
            java.sql.Date sqlbirthday = new java.sql.Date(date.getTime()); 
        
            //connection not working
            Connection con=User.connectDB();  
            //Statement stmt=con.createStatement(); 
            
            String sql = "INSERT INTO users (username, firstname, lastname, email, pass, sex,  height, weight,birthdate) VALUES"+"(?,?,?,?,?,?,?,?,?)";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setString(1, userName);
            
            insertUser.setString(2, firstName);
            insertUser.setString(3, lastName);
            insertUser.setString(4, email);
            insertUser.setString(5, password);
            insertUser.setString(6,sex);
            
            insertUser.setDouble(7,height);
            insertUser.setDouble(8, weight);
            insertUser.setDate(9,sqlbirthday);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);}
    }
    public void captureWeight(double weight)
    {
        user.setWeight(weight);
        this.calculateBMI();
    }
    public void captureHeight(double height)
    {
        user.setHeight(height);
        this.calculateBMI();
    }
    public void calculateBMI()
    {
        //height in cm
        double height=user.getHeight();
        //weight in kg
        double weight=user.getWeight();
        
        double bmi =(weight/(height*height)); 
        bmi =Math.round(bmi * 100.0) / 100.0;
        user.setBmi(bmi);
        
    }
    public double calculateBMR()
    {
        double bmr=0.0;
        String sex= user.getSex();
        int age = user.getAge();
        //height in cm
        double height=user.getHeight();
        //weight in kg
        double weight=user.getWeight();     
        if(sex.equals("f"))
        {
            bmr=655 + (4.35*weight) + (4.7*height) - (4.7*age);
        }
        else
        {
            bmr=66 + (6.23 *weight) + (12.7 *height) - (6.8 *age);
        }
        return bmr;
    }
    public void updateCalBurnt(double calories)
    {
        double newTotal=0.0;
        newTotal=user.getCalBurnt()+calories;
        System.out.println(newTotal);
        user.setCalBurnt(newTotal);
    }
    
    public DefaultCategoryDataset createDataset( )
    {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        try{
            //connection not working
            Connection con=User.connectDB();  
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT * from exercise where user = ?";
            

            
            PreparedStatement exercise =con.prepareStatement(sql);
            exercise.setString(1,user.getUserName());
            
            ResultSet results = exercise.executeQuery();
            while (results.next())
            {
                dataset.setValue(results.getDouble("weight"),"weight",results.getDate("day"));
            }
            

            con.close();  
        }catch(Exception e){ System.out.println(e);}

        return dataset;
    }
}
