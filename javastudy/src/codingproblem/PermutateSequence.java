package codingproblem;

/**
 * Created by Administrator on 2017/9/22.
 */
public class PermutateSequence {
    public static void main(String[] args) {
        char[] ch = {'1', '2', '3', '4'};
        permutate(ch, 0);
    }

    public static void permutate(char[] ch, int i){
        if (ch == null || i > ch.length || i < 0){
            return;
        }
        if (i == ch.length){
            System.out.println(ch);
            return;
        }
        for (int j = i; j < ch.length; j++) {
            swap(ch, i, j);
            permutate(ch, i + 1);
            swap(ch, i, j);
        }

    }

    public static void swap(char[] ch, int i, int j){
        char tmp = ch[i];
        ch[i] = ch[j];
        ch[j] = tmp;
    }
}
