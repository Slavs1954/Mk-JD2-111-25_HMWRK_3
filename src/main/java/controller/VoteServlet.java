package controller;

import dto.AllowedData;
import dto.Vote;
import edu.emory.mathcs.backport.java.util.Arrays;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.VoteService;
import service.api.IVoteService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final IVoteService service = VoteService.getInstance();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setAttribute("authors", AllowedData.AUTHORS);
        req.setAttribute("genres", AllowedData.GENRES);

        req.getRequestDispatcher("WEB-INF/JSP/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] author = req.getParameterValues("author");
        String[] genres = req.getParameterValues("genres");
        String about = req.getParameter("about");

        Vote result = new Vote();
        result.setDateTimeCreate(LocalDateTime.now());
        result.setArtist(author[0]);
        result.setGenres(Arrays.asList(genres));
        result.setAbout(about);
        service.add(result);

        resp.sendRedirect(req.getContextPath() + "/result");
    }
}
