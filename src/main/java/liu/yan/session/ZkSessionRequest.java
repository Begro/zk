package liu.yan.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

/**
 * Created by liuyan9 on 2017/6/7.
 */
public class ZkSessionRequest extends HttpServletRequestWrapper {

    private ISessionManager zkSessionManager;
    private HttpSession session;

    public ZkSessionRequest(HttpServletRequest request, ISessionManager zkSessionManager) {
        super(request);
        this.zkSessionManager = zkSessionManager;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }
}
