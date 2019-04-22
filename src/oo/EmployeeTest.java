package oo;

/**
 * Created by sun on 2016/12/7.
 */
public class EmployeeTest {
    public static void main(String[] args){
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Jack", 1000, 2000, 1, 1);
        staff[1] = new Employee("Neo", 5000, 2001,2 , 1);
        staff[2] = new Employee("Rose", 3000, 2010, 3, 2);

        for (Employee e : staff
             ) {
            e.raiseSalary(10);
        }

        for (Employee e : staff
             ) {
            System.out.println(e.getId() + "-" + e.getName() + "-" + e.getSalary() + "-" + e.getHireDay());
        }
    }
}
