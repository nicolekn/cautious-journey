/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package healthtracker;

public class Intake {
private String name;
private int calories;

    public Intake() {
    }

    public Intake(String name, int calories) {
        this.name = name;
        this.calories = calories;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return calories
     */
    public int getCalories() {
        return calories;
    }

    /**
     * @param calories calories to set
     */
    public void setCalories(int calories) {
        this.calories = calories;
    }

}
