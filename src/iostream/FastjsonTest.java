package iostream;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2017/2/13.
 */
public class FastjsonTest {
    public static void main(String[] args){
        FastjsonTest fastjsonTest = new FastjsonTest();
//        fastjsonTest.testJsonObject();
//        fastjsonTest.testJsonArray();
        Teacher teacher = new Teacher();
        teacher.setName("周");
        teacher.setStatus("success");
        Student student1 = new Student(201572641, "孙", 24);
        Student student2 = new Student(201572640, "陈", 24);
        Student student3 = new Student(201572642, "孙", 24);
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        teacher.setStudentList(studentList);
        String str = JSON.toJSONString(teacher);
        System.out.println("生成json字符串： " + str);
        Teacher teach = JSON.parseObject(str, Teacher.class);
        System.out.println("name: " + teach.getName());
        System.out.println("status: " + teach.getStatus());
        for (Student student : teach.getStudentList()) {
            fastjsonTest.printStudent(student);
        }
    }

    /**
     * 测试JsonObject对象类型
     */
    public void testJsonObject(){
        Student student = new Student(201572641, "孙", 24);
        String str = JSON.toJSONString(student);
        System.out.println("生成json字符串: " + str);
        //1.解析成普通的对象
        Student stu = JSON.parseObject(str, Student.class);
        System.out.println("解析json字符串: ");
        printStudent(stu);
        //2.解析成JSONObject
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject.getInteger("stuNum") + "--"
                + jsonObject.getString("name") + "--" + jsonObject.getInteger("age"));
    }

    /**
     * 测试JsonArray数组类型
     */
    public void testJsonArray(){
        Student student1 = new Student(201572641, "孙", 24);
        Student student2 = new Student(201572640, "陈", 24);
        Student student3 = new Student(201572642, "杨", 24);
        List<Student> studentList = new ArrayList<Student>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        //1.使用List
        String listStr = JSON.toJSONString(studentList);
        System.out.println("生成json字符串: " + listStr);
        List<Student> list = JSON.parseArray(listStr, Student.class);
        for (Student student : list) {
            printStudent(student);
        }
        //2.使用JsonArray
        System.out.println("使用JsonArray: ");
        JSONArray jsonArray = JSON.parseArray(listStr);
        for (Object jsonObject : jsonArray) {
            Student student = JSON.parseObject(jsonObject.toString(), Student.class);
            printStudent(student);
        }
    }


    /**
     * 打印学生的信息
     * @param student  Student对象
     */
    public void printStudent(Student student){
        System.out.println("学号: " + student.getStuNum() + ", 姓名: " + student.getName() + ", 年龄: "
                +student.getAge());
    }
}