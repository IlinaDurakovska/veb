package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.ArtistService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "artist", urlPatterns = "/servlet/artist")
public class ArtistServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final ArtistService artistService;

    public ArtistServlet(SpringTemplateEngine springTemplateEngine, ArtistService artistService) {
        this.springTemplateEngine = springTemplateEngine;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        this.springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long trackId = Long.parseLong(req.getParameter("chosenTrack"));

        //
        req.getSession().setAttribute("chosenTrackId", trackId);
        // lab
        //


        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext()).buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("trackId", trackId);
        context.setVariable("artists", artistService.listArtists());
        this.springTemplateEngine.process("artistsList.html", context, resp.getWriter());

        //resp.sendRedirect("/artist");
    }
}
