package log;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by sun on 2016/12/26.
 */
public class LogTest {
    //使用log4j获取logger
//    private static Logger logger = Logger.getLogger(LogTest.class);
    //使用commons-logging获取logger
//    private static Log logger = LogFactory.getLog(LogTest.class);

    //使用SLF4J获取日志实例对象
    final static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args){
        logger.info("This is info message");
        logger.debug("This is debug message");
        String str = "#\u8F93\u51FAinfo\u5230\u65E5\u5FD7\u6587\u4EF6";
        try {
            System.out.println(new String(str.getBytes(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String[] pattern = {"yyyy-MM-dd HH:mm:ss"};
        try {
            Date date = DateUtils.parseDate("2017-8-15 11:30:30", pattern);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
