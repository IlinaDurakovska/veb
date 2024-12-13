package mk.ukim.finki.wp.lab.web.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter(filterName = "auth-filter", urlPatterns = "/*",
//        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD},
//        initParams = {
//                @WebInitParam(name = "set-path", value = "/artist"),
//                @WebInitParam(name = "get-path", value = "/songDetails")
//        }
//)
public class SongIdFilterLAB1 implements Filter {
    private String setPath;
    private String getPath;
    private String songId;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        setPath = filterConfig.getInitParameter("set-path");
        getPath = filterConfig.getInitParameter("get-path");
        songId = null;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String path = req.getServletPath();

        if(path.startsWith(setPath)){
            Long trackId = Long.parseLong(req.getParameter("chosenTrack"));
            //req.setAttribute("chosenTrack", trackId);
            servletRequest.getServletContext().setAttribute("chosenTrack", trackId);
            //songId = trackId.toString();
        }
        else if(path.startsWith(getPath)){
            req.setAttribute("chosenTrack", req.getServletContext().getAttribute("chosenTrack"));
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
