package liu.yan.session;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;

/**
 * Created by liuyan9 on 2017/6/7.
 */
public class ZkSession implements HttpSession {

    private long createTime;
    private String id;
    private long lastAccessedTime;
    private boolean isNew;
    private int maxInactiveInterval;

    private ISessionManager zkSessionManager;

    public ZkSession(ISessionManager zkSessionManager) {
        this.zkSessionManager = zkSessionManager;
        this.createTime = System.currentTimeMillis();
        this.lastAccessedTime = createTime;
        this.isNew = true;
    }

    @Override
    public long getCreationTime() {
        return createTime;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    @Override
    public ServletContext getServletContext() {
        return zkSessionManager.getServletContext();
    }

    @Override
    public void setMaxInactiveInterval(int i) {
        this.maxInactiveInterval = i;
    }

    @Override
    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return zkSessionManager.getAttribute(id, s);
    }

    @Override
    public Object getValue(String s) {
        return getAttribute(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return zkSessionManager.getAttributeNames(id);
    }

    @Override
    public String[] getValueNames() {
        return zkSessionManager.getValueNames(id);
    }

    @Override
    public void setAttribute(String s, Object o) {
        this.isNew = zkSessionManager.setAttribute(id, s, o);
    }

    @Override
    public void putValue(String s, Object o) {
        setAttribute(s, o);
    }

    @Override
    public void removeAttribute(String s) {
        zkSessionManager.removeAttribute(id, s);
    }

    @Override
    public void removeValue(String s) {
        removeAttribute(s);
    }

    /**
     * 失效session
     */
    @Override
    public void invalidate() {
        zkSessionManager.invalidate(id);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
