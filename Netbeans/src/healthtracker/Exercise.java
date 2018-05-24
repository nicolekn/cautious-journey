/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthtracker;

import java.text.*;
import java.util.Date;

public class Exercise {

    private double duration;
    private int met;
    private int calBurnt;
    private Date date;

    public Exercise() {
    }

    public Exercise( double duration, int met) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date=new Date();
        this.duration = duration;
        this.met = met;
        this.calBurnt=0;
    }



    /**
     * @return the duration
     */
    public double getDuration() {
        return duration;
    }
    public Date getDate()
    {
        return date;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(double duration) {
        this.duration = duration;
    }

    /**
     * @return the intensity
     */
    public int getIntensity() {
        return met;
    }

    /**
     * @param intensity the intensity to set
     */
    public void setIntensity(int intensity) {
        this.met = met;
    }
    
    public static double calculateCaloriesBurnt(int met, double duration, User user)
    {
        System.out.println(met*user.getWeight()*(duration/60));
        return met*user.getWeight()*(duration/60);
        
    }
    
}
