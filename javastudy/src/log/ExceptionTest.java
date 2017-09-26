package log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2016/12/26.
 */
public class ExceptionTest {
    private static Log log = LogFactory.getLog(ExceptionTest.class);

    //try和finally中都有return语句，测试它们的执行顺序
    public boolean divide(int a, int b){
        boolean result = true;
        try{
            double c = a / b;
            return true;
        }catch (Exception e){
            e.printStackTrace();
            result = false;
        }finally {
            System.out.println("This is finally clause.");
            return result;
        }
    }

    //测试用户自定义异常
    public void testMyException(String message) throws MyException {
        throw new MyException(message);
    }

    public static void main(String[] args){
        ExceptionTest exceptionTest = new ExceptionTest();
        log.info("info message in " + ExceptionTest.class.getName());
        boolean result = exceptionTest.divide(6, 2);
        System.out.println("result is : " + result);
        try {
            exceptionTest.testMyException("测试自定义异常");
        } catch (MyException e) {
            e.printStackTrace();
            System.out.println("异常信息：" + e.getMessage());
        }
    }
}

//用户自定义异常类
class MyException extends Exception{
    public MyException(String message){
        super(message);
    }
}