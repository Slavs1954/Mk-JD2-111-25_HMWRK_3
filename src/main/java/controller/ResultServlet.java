package controller;

import dto.AllowedData;
import dto.Stats;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ServiceFactory;
import service.VoteService;
import service.api.IVoteService;

import java.io.IOException;

@WebServlet(urlPatterns = "/result")
public class ResultServlet extends HttpServlet {
    private final IVoteService service = ServiceFactory.getVoteService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Stats stats = service.getStats();

        req.setAttribute("authors", stats.getAuthors());
        req.setAttribute("genres", stats.getGenres());
        req.setAttribute("abouts", stats.getAbouts().toArray());


        req.getRequestDispatcher("WEB-INF/JSP/result.jsp").forward(req, resp);

    }
}
