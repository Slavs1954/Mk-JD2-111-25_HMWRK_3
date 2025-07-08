package controller;

import dto.AllowedData;
import dto.Vote;
import edu.emory.mathcs.backport.java.util.Arrays;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.VoteService;
import service.api.IVoteService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    private final IVoteService service = ServiceFactory.getVoteService();


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

        service.add(Vote.builder()
                .dtCreate(LocalDateTime.now())
                .author(author[0])
                .addGenreList(Arrays.asList(genres))
                .about(about)
                .build());

        resp.sendRedirect(req.getContextPath() + "/result");
    }
}
