package log;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by sun on 2016/12/26.
 */
public class AssertTest {
    private static Log log = LogFactory.getLog(AssertTest.class);

    public static void main(String[] args){
        log.info("info message in " + AssertTest.class.getName());
        String name = "ur name";
        assert name != null;
        System.out.println("length : " + name.length());
    }
}
