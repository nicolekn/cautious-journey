/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthtracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.Period;

public class User {
private String userName;
private String password;
private String firstName;
private String lastName;
private String email;
private int age;
private LocalDate birthdate;
private String sex;
private double bmi;
private double height;
private double weight;
private double goalWeight;
private double calBurnt;


public User()
{
    
}
public User(String userName,String password, String firstName, String lastName, String email, String sex, LocalDate birthdate)
{
    this.userName=userName;
    System.out.println(userName);
    this.password=password;
    this.firstName=firstName;
    this.lastName=lastName;
    this.birthdate=birthdate;
    this.sex=sex;
    
     

}
public User(String userName,String password, String firstName, String lastName, String email, String sex, int birthday, int birthmonth, int birthyear)
{
    this.userName=userName;
    System.out.println(userName);
    this.password=password;
    this.firstName=firstName;
    this.lastName=lastName;
    this.birthdate= LocalDate.of(birthyear, birthmonth, birthday);
    this.sex=sex;   

}
public static Connection connectDB() throws ClassNotFoundException, SQLException
{
    Class.forName("com.mysql.jdbc.Driver");  
         
    return DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/health_users?autoReconnect=true&useSSL=false","root",""); 
}
    /**
     * @return the userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.weight = weight;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET weight = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setDouble(1, weight);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET pass = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setString(1, password);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }

    /**
     * @return the first name
     */
    public String getFirstName() {
        String user_username = userName;

        //check database for username and passowrd matching
        //if (matching)
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT firstname from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,user_username);
            ResultSet set = verifyLogin.executeQuery();
            String firstName=set.getString(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        return firstName;
    }

    /**
     * @param firstName the name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
           String sql = "UPDATE users SET firstname = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setString(1, firstName);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }
    /**
     * @return the last name
     */
    public String getLastName() {
        String lastname="";
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT lastname from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            lastname=set.getString(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        return lastname;
    }

    /**
     * @param lastName the name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET lastname = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setString(1, lastName);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }
    /**
     * @return the age
     */
    public int getAge() {        
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT birthdate from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
            birthdate=( set.getDate(1).toLocalDate());
            
            age = Period.between(birthdate, LocalDate.now()).getYears();
            this.setAge( age );
            
            con.close();  
            
        }catch(Exception e){ System.out.println(e);}
        
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET age = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setInt(1, age);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }

    /**
     * @return the sex
     */
    public String getSex() {
        String sex="";
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT sex from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            sex=set.getString(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
   
        return sex;
    }

    /**
     * @param sex the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
        try{
            //connection not working
            Connection con=User.connectDB();   
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET sex = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setString(1, sex);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }

    /**
     * @return the bmi
     */
    public double getBmi() {
        bmi=this.getWeight()/(this.getHeight()*this.getHeight())*10000;
        bmi= Math.round(bmi*100.0)/100.0;
        return bmi;
    }

    /**
     * @param bmi the bmi to set
     */
    public void setBmi(double bmi) {
        this.bmi = bmi;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET bmi = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setDouble(1, bmi);
            insertUser.setString(2, this.userName);

            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }

    /**
     * @return the height
     */
    public double getHeight() {
        Double user_height=0.0;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT height from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            user_height=set.getDouble(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        return user_height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(double height) {
        this.height = height;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET height = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setDouble(1, height);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        Double user_weight=0.0;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT weight from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            user_weight=set.getDouble(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        return user_weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET weight = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setDouble(1, weight);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }

    /**
     * @return the idealWeight
     */
    public double getGoalWeight() {
        Double goal=0.0;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT weight_goal from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            goal=set.getDouble(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        return goal;
    }

    /**
     * @param idealWeight the idealWeight to set
     */
    public void setGoalWeight(double goalWeight) {
        this.goalWeight = goalWeight;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET weight_goal = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setDouble(1, goalWeight);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
    }
    public double getCalBurnt()
    {
        Double calBurnt=0.0;
        try{
            //connection not working
            Connection con=User.connectDB();    
            //Statement stmt=con.createStatement(); 
            
            String sql = "SELECT caloriesburnt from users WHERE username = ? ";
            //stmt.executeUpdate(sql);
            
            PreparedStatement verifyLogin =con.prepareStatement(sql);
            verifyLogin.setString(1,this.userName);
            ResultSet set = verifyLogin.executeQuery();
            set.next();
            calBurnt=set.getDouble(1);
            con.close();  
        }catch(Exception e){ System.out.println(e);} 
        return calBurnt;
    }
    public void setCalBurnt(double newAmount)
    {
        this.calBurnt = calBurnt;
        try{
            //connection not working
            Connection con=User.connectDB();     
            //Statement stmt=con.createStatement(); 
            
            String sql = "UPDATE users SET caloriesburnt = ? WHERE username=?";
            //stmt.executeUpdate(sql);
            
            PreparedStatement insertUser =con.prepareStatement(sql);
            insertUser.setDouble(1, newAmount);
            insertUser.setString(2, this.userName);
            
            insertUser.executeUpdate();
            con.close();  
        }catch(Exception e){ System.out.println(e);}
    }
}
