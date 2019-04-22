package oo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Created by sun on 2016/12/7.
 */
public class Employee {
    private static int nextId;

    private String name;
    private double salary;
    private Date hireDay;
    private int id;

    public Employee(){

    }

    public Employee(String n, double s, int year, int month, int day){
        name = n;
        salary = s;
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getSalary(){
        return salary;
    }

    public Date getHireDay(){
        return hireDay;
    }

    public int getId(){
        return id;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    static {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }

    {
        id = nextId;
        nextId++;
    }
}
