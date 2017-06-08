package liu.yan.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by liuyan9 on 2017/6/8.
 */
public interface ISessionManager {

    ServletContext getServletContext();

    Object getAttribute(String id, String key);

    Enumeration<String> getAttributeNames(String id);

    String[] getValueNames(String id);

    boolean setAttribute(String id, String s, Object o);

    void removeAttribute(String id, String s);

    void invalidate(String id);

    HttpSession getSession(String sessionid);
}
