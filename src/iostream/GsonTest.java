package iostream;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2017/2/11.
 */
public class GsonTest {
    public static void main(String[] args) {
        GsonTest gsonTest = new GsonTest();
//        gsonTest.testJsonPrimitive();
//        gsonTest.testJsonObject();
//        gsonTest.testJsonArray();
        Teacher teacher = new Teacher();
        teacher.setName("周");    //设置名字
        teacher.setStatus("success");    //设置状态
        Student student1 = new Student(201572641, "孙", 23);
        Student student2 = new Student(201572640, "陈", 23);
        Student student3 = new Student(201572642, "杨", 23);
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        teacher.setStudentList(studentList);    //设置Student列表
        String str = gsonTest.testToJson(teacher);
        System.out.println(str);
        Teacher teach = gsonTest.testFromJson(str);
        System.out.println("name: " + teach.getName());
        System.out.println("status: " + teach.getStatus());
        List<Student> list = teach.getStudentList();
        for (Student student : list) {
            System.out.println("学号: " + student.getStuNum() + ", 姓名: " + student.getName() + ", 年龄: "
                    + student.getAge());
        }
    }

    /**
     * 测试JsonPrimitive基本数据类型
     */
    public void testJsonPrimitive() {
        Gson gson = new Gson();
        //基本数据类型的生成
        System.out.println("---基本数据类型的生成---");
        String jsonInt = gson.toJson(100);
        String jsonString = gson.toJson("你好世界");
        System.out.println("生成int: " + jsonInt);
        System.out.println("生成String: " + jsonString);
        //基本数据类型的解析
        System.out.println("---基本数据类型的解析---");
        int i = gson.fromJson(jsonInt, int.class);
        String str = gson.fromJson(jsonString, String.class);
        System.out.println("解析int: " + i);
        System.out.println("解析String: " + str);
    }

    /**
     * 测试JsonObject简单对象类型
     */
    public void testJsonObject(){
        Gson gson = new Gson();
        Student student = new Student(201572641, "孙", 23);
        //JsonObject类型的生成
        System.out.println("---JsonObject类型的生成---");
        String str = gson.toJson(student);
        System.out.println("生成字符串: " + str);
        //JsonObject类型的解析
        System.out.println("---JsonObject类型的解析---");
        Student stu = gson.fromJson(str, Student.class);
        System.out.println("学号: " + stu.getStuNum() + ", 姓名: " + stu.getName() + ", 年龄: "
                + stu.getAge());
    }

    /**
     * 测试JsonArray数组类型
     */
    public void testJsonArray(){
        Gson gson = new Gson();
        Student student1 = new Student(201572641, "孙", 23);
        Student student2 = new Student(201572640, "陈", 23);
        Student student3 = new Student(201572642, "杨", 23);
        //1.使用数组
        System.out.println("---使用数组---");
        Student[] stu = {student1, student2 , student3};
        //(1)生成json字符串
        String arrayString = gson.toJson(stu);
        System.out.println("使用数组生成json: " + arrayString);
        //(2)解析json字符串
        System.out.println("解析json: ");
        Student[] stuArray = gson.fromJson(arrayString, Student[].class);
        System.out.println("学号: " + stu[0].getStuNum() + ", 姓名: " + stu[0].getName() + ", 年龄: "
                + stu[0].getAge());
        System.out.println("学号: " + stu[1].getStuNum() + ", 姓名: " + stu[1].getName() + ", 年龄: "
                + stu[1].getAge());
        System.out.println("学号: " + stu[2].getStuNum() + ", 姓名: " + stu[2].getName() + ", 年龄: "
                + stu[2].getAge());
        //2.使用List
        System.out.println("---使用列表---");
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        //(1)生成json字符串
        String listString = gson.toJson(studentList);
        System.out.println("使用列表生成json: " + listString);
        //(2)解析json字符串
        System.out.println("解析json: ");
        List<Student> studentLi = gson.fromJson(listString, new TypeToken<List<Student>>(){} .getType());
        for (Student student : studentLi) {
            System.out.println("学号: " + student.getStuNum() + ", 姓名: " + student.getName() + ", 年龄: "
                    + student.getAge());
        }
        //3.使用JsonArray
        System.out.println("---使用JsonArray---");
        //解析json字符串
        System.out.println("解析json: ");
        //使用JsonParser进行解析
        JsonArray jsonArray = new JsonParser().parse(listString).getAsJsonArray();
        List<Student> stuLi = new ArrayList<Student>();
        //从JsonArray中获取Student对象
        for (JsonElement jsonElement : jsonArray) {
            Student student = gson.fromJson(jsonElement, Student.class);
            stuLi.add(student);
        }
        for (Student student : studentLi) {
            System.out.println("学号: " + student.getStuNum() + ", 姓名: " + student.getName() + ", 年龄: "
                    + student.getAge());
        }
    }

    /**
     * 测试将任意的对象转换为json字符串
     * @param object
     * @return
     */
    public String testToJson(Object object){
        return new Gson().toJson(object);
    }

    /**
     * 测试将json字符串转换为对象类型，需要用指定的对象类型接收
     * @param str
     * @return  Teacher类型的对象
     */
    public Teacher testFromJson(String str){
        return new Gson().fromJson(str, Teacher.class);
    }
}

class Teacher{
    private String name;
    private String status;
    private List<Student> studentList;

    public Teacher(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public List<Student> getStudentList() {
        return studentList;
    }
}
