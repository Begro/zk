package liu.yan.session;

import javax.servlet.ServletContext;

/**
 * Created by liuyan9 on 2017/6/8.
 */
public class ZkSessionManager {

    private ServletContext servletContext;


    public ZkSessionManager(ServletContext servletContext) {
        this.servletContext = servletContext;
    }


    public ServletContext getServletContext() {
        return servletContext;
    }
}
