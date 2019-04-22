package rpc;

/**
 * Created by Administrator on 2017/10/7.
 */

/**
 *服务提供者实现类
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
