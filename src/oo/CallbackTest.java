package oo;

/**
 * Created by sun on 2016/12/23.
 */
interface Callback{
    public void exe();
}

class CallbackImp {
    public void callExe(Callback i){
        i.exe();
    }
}
public class CallbackTest {
    public static void main(String[] args){
        CallbackImp callbackImp = new CallbackImp();
        callbackImp.callExe(new Callback() {
            @Override
            public void exe() {
                System.out.println("这里回调了！");
            }
        });
    }
}
