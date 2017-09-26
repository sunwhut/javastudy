package codingproblem;

/**
 * Created by Administrator on 2017/8/28.
 */
public class ReplaceSpace {
    /**
     * 替换字符串中的空格
     * @param str
     * @return
     */
    public static String replaceSpace(String str){
        char[] chars = str.toCharArray();
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ')
                result.append("%20");
            else
                result.append(chars[i]);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String str = "We are happy.";
        System.out.println(replaceSpace(str));
    }
}
