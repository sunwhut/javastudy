package javabean;

import java.util.Date;

/**
 * Created by sun on 2017/2/27.
 */
public class User {
    private String name;
    private int age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    @Override
    public String toString() {
        String str = "name: " + name + ", age: " + age;
        return str;
    }
}
