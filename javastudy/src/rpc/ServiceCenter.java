package rpc;

import java.io.IOException;

/**
 * Created by Administrator on 2017/10/7.
 */

/**
 * 服务中心
 */
public interface ServiceCenter {
    void start() throws IOException;

    void stop();

    void register(Class service, Class serviceImpl);

    boolean isRunning();

    int getPort();
}
