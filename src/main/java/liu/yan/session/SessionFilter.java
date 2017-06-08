package liu.yan.session;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by liuyan9 on 2017/6/7.
 */
@WebFilter(filterName = "1", urlPatterns = "/*")
public class SessionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String sessionid = httpServletRequest.getSession().getId();

    }

    @Override
    public void destroy() {

    }
}
