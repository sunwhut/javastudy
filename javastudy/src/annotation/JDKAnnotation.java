package annotation;

/**
 * Created by sun on 2017/2/27.
 */
interface People {
    public void name();
    @Deprecated
    public void work();
}

class Child implements People {
    private String name;
    private String work;

    public Child(String name, String work){
        this.name = name;
        this.work = work;
    }

    @Override
    public void name() {
        System.out.println("name: " + name);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void work() {
        System.out.println("work: " + work);
    }
}

public class JDKAnnotation {
    public static void main(String[] args) {
        Child child = new Child("孙", "程序猿");
        child.work();
    }
}
