package iostream;

import com.google.gson.annotations.SerializedName;

import java.io.*;

/**
 * Created by sun on 2017/2/10.
 */
public class ObjectSerializableTest {
    public static void main(String[] args){
        ObjectSerializableTest objectSerializableTest = new ObjectSerializableTest();
        objectSerializableTest.testSerializable();
//        objectSerializableTest.testExternalizable();
    }

    /**
     * 测试Serializable接口
     */
    public void testSerializable(){
        File file = new File("./log/student.txt");
        Student student1 = new Student(201572641,"孙", 23);
        Student student2 = new Student(201572640,"陈", 23);
        Student stu1 = null;
        Student stu2 = null;
        //1.对象序列化
        /*try {
            //（1）创建对象输出流，连接在文件输出流上
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            //（2）使用流对象传输数据
            //如果Student类定义了writeObject()方法，那么ObjectOutputStream会调用
            // Student对象的writeObject方法进行序列化
            objectOutputStream.writeObject(student1);
            objectOutputStream.writeObject(student2);
            //（3）关闭流对象
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //2.对象反序列化
        try {
            //（1）创建对象输入流，连接到文件输入流上
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            //（2）使用流对象传输数据
            stu1 = (Student) objectInputStream.readObject();
            stu2 = (Student) objectInputStream.readObject();
            //（3）关闭流对象
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("学号: " + stu1.getStuNum() + ", 姓名: " + stu1.getName() + ", "
                + "年龄: " + stu1.getAge());
        System.out.println("学号: " + stu2.getStuNum() + ", 姓名: " + stu2.getName() + ", "
                + "年龄: " + stu2.getAge());
    }

    /**
     * 测试Externalizable接口
     */
    public void testExternalizable(){
        File file = new File("./log/student.txt");
        Master master1 = new Master(201572641, "孙", 23);
        Master master2 = new Master(201572640, "杨", 23);
        Master m1 = null;
        Master m2 = null;
        //1.对象序列化
        try {
            //（1）创建流对象
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            //（2）使用流对象传输数据
            objectOutputStream.writeObject(master1);
            objectOutputStream.writeObject(master2);
            //（3）关闭流对象
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //2.对象反序列化
        try {
            //（1）创建流对象
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            //（2）使用流对象传输数据
            m1 = (Master) objectInputStream.readObject();
            m2 = (Master) objectInputStream.readObject();
            //（3）关闭流对象
            objectInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("学号: " + m1.getStuNum() + ", 姓名: " + m1.getName() + ", "
                + "年龄: " + m1.getAge());
        System.out.println("学号: " + m2.getStuNum() + ", 姓名: " + m2.getName() + ", "
                + "年龄: " + m2.getAge());

    }
}

class Student implements Serializable{
    private static final long serialVersionUID = -1537784272187936493L;
    //使用transient关键字修饰的字段不能被序列化，被反序列化之后该字段的值被设为默认初始值
    @SerializedName("studentNumber")
    private int stuNum;  //学号
    private String name; //姓名
    private int age;  //年龄
//    private String sex;  //性别

    public Student(){

    }

    public Student(int stuNum, String name, int age){
        this.stuNum = stuNum;
        this.name = name;
        this.age = age;
    }

    public int getStuNum() {
        return stuNum;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setStuNum(int stuNum) {
        this.stuNum = stuNum;
    }

    /**
     * 自定义序列化方法
     * 为什么writeObject()要是private类型的，ObjectOutputStream使用反射来寻找是否声明了这个方法。
     * ObjectOutputStream使用getPrivateMethod，所以这些方法不得不被声明为private来供ObjectOutputStream使用。
     * @param out  对象输出流
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(stuNum);
        out.writeUTF(name);
        out.writeInt(age);
    }

    /**
     * 自定义反序列化方法
     * @param in  对象输入流
     * @throws IOException
     */
    private void readObject(ObjectInputStream in) throws IOException {
        //读对象的顺序必须与写对象的顺序一致
        stuNum = in.readInt();
        name = in.readUTF();
        age = in.readInt();
    }
}

class Master implements Externalizable{
    private int stuNum;    //学号
    private String name;    //姓名
    private int age;    //年龄

    /**
     * 实现Externalizable接口的类必须要提供一个public的无参的构造器。否则会得到一个异常java.io.InvalidClassException
     * 因为在使用Externalizable进行反序列化，在读取对象时，会调用被序列化类的无参构造器去创建一个新的对象，
     * 然后再将被保存对象的字段的值分别填充到新对象中。
     */
    public Master(){
        System.out.println("调用了无参构造函数");
    }

    public Master(int stuNum, String name, int age){
        this.stuNum = stuNum;
        this.name = name;
        this.age = age;
    }

    public int getStuNum() {
        return stuNum;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * 自定义序列化方法
     * @param out
     * @throws IOException
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(stuNum);
        out.writeUTF(name);
        out.writeInt(age);
    }

    /**
     * 自定义反序列化方法
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        stuNum = in.readInt();
        name = in.readUTF();
        age = in.readInt();
    }
}


