package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


@WebServlet(name = "Servlet4", urlPatterns = {"/form"})
public class FormHandler extends HttpServlet {

    List<FormData> data = new ArrayList<>();

    final private Set<String> KNOWN_AUTHORS = new HashSet<>(Arrays.asList("MickGordon","America","Lyn",
            "Skillet","HeavenPierceHer"));
    final private Set<String> KNOWN_GENRES = new HashSet<>(Arrays.asList("Rock","Pop","Classical","Jazz","Folk","Other"));

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Enumeration<String> parameterNames = req.getParameterNames();

        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");


        String author = "";
        List<String> genres = new ArrayList<>();
        String customMessage = "";
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.equals("author")) {
                author = req.getParameter(paramName);
            }
            if (paramName.equals("genres")) {
                genres.addAll(Arrays.asList(req.getParameterValues(paramName)));
            }
            if (paramName.equals("about")) {
                customMessage = req.getParameter(paramName);
            }
        }
        if (genres.size() < 3) {
            resp.getWriter().write("<div style=\"background-color:red;color:white;padding:2%;\">ERROR: INVALID DATA</div>");
            return;
        }
        data.add(new FormData(author, genres, customMessage));

        Iterator<String> knownAuthorsIterator = KNOWN_AUTHORS.iterator();
        Iterator<String> knownGenresIterator = KNOWN_GENRES.iterator();
        Iterator<FormData> dataIterator = data.iterator();
        PrintWriter writer = resp.getWriter();

        List<String> outAuthors = new ArrayList<>();
        List<String> outGenres = new ArrayList<>();


        String currentAuthor;
        String currentGenre;
        FormData retrivedData;

        writer.write("<p><b>Результаты голосования:</b></p>");
        while (dataIterator.hasNext()) {
            retrivedData = dataIterator.next();
            outAuthors.add(retrivedData.getAuthor());
            outGenres.addAll(retrivedData.getGenres());
        }
        writer.write("<p><b>Авторы:</b></p>");
        writer.write("<p>");
        while (knownAuthorsIterator.hasNext()) {
            currentAuthor = knownAuthorsIterator.next();
            writer.write(currentAuthor + " " + Collections.frequency(outAuthors, currentAuthor) + "<br>");
        }
        writer.write("</p>");
        writer.write("<p><b>Жанры:</b></p>");
        writer.write("<p>");
        while (knownGenresIterator.hasNext()) {
            currentGenre = knownGenresIterator.next();
            writer.write(currentGenre + " " + Collections.frequency(outGenres, currentGenre) + "<br>");
        }
        writer.write("</p>");

    }
}
