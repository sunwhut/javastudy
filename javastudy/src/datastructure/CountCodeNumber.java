package datastructure;

/**
 * Created by sun on 2017/3/4.
 */

import java.io.*;
import java.util.*;
import java.util.ArrayList;

/**
 * 统计本项目中所有java文件总代码行数
 */
public class CountCodeNumber {
    private int codeNumber;  //本项目中所有java文件总代码行数

    public int getCodeNumber() {
        return codeNumber;
    }

    /**
     * 统计所有java文件总代码行数
     * @param file  File对象
     */
    public void countCodeNumber(File file){
        java.util.ArrayList<File> arrayList = new ArrayList<File>();
        for (File tmp : file.listFiles()){
            //把所有的目录都加入arrayList中
            if (tmp.isDirectory()){
                arrayList.add(tmp);
            }
        }
        ArrayList<File> fileList = new ArrayList<File>();
        int number = 0;
        for (File directory : arrayList) {
            System.out.println(directory.getAbsolutePath());
            //将该目录下的所有java文件加入fileList中
            for (File javaFile : directory.listFiles()) {
                if (javaFile.getName().indexOf(".java") > 0){
                    fileList.add(javaFile);
                }
            }
            for (File javaFile : fileList) {
                number = countFileCodeNumber(javaFile);
                System.out.println(javaFile.getName() + " : " + number + " 行");
                codeNumber += number;

            }
            fileList.clear();
        }
    }

    /**
     * 统计一个java文件的代码行数
     * @param file  File对象
     * @return  文件中代码行数
     */
    public int countFileCodeNumber(File file){
        int number = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(file)));
            while (bufferedReader.readLine() != null){
                number++;
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return number;
    }

    public static void main(String[] args) {
        CountCodeNumber count = new CountCodeNumber();
        count.countCodeNumber(new File("./src"));
       System.out.println("总代码行数： " + count.getCodeNumber());
    }
 }
