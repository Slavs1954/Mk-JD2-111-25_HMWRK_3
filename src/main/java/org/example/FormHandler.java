package org.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@WebServlet(name = "Servlet4", urlPatterns = {"/form"})
public class FormHandler extends HttpServlet {

    List<FormData> data = new ArrayList<>();

    //final private Set<String> KNOWN_AUTHORS = new HashSet<>(Arrays.asList("MickGordon","America","Lyn", "Skillet"));
    //final private Set<String> KNOWN_GENRES = new HashSet<>(Arrays.asList("Rock","Pop","Classical","Jazz","Folk","Other"));

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
            resp.getWriter().write("<h1><div style=\"background-color:red;color:white;padding:2%;\">ERROR: INVALID DATA</div></h1>");
            return;
        }
        if (author.isEmpty()) {
            resp.getWriter().write("<h1><div style=\"background-color:red;color:white;padding:2%;\">ERROR: INVALID DATA</div></h1>");
            return;
        }
        data.add(new FormData(author, genres, customMessage));

        Iterator<FormData> dataIterator = data.iterator();

        HashMap<String, Integer> outGenres = new HashMap<>();
        HashMap<String, Integer> outAuthors = new HashMap<>();
        HashMap<LocalDateTime, String> outAbouts = new HashMap<>();

        while (dataIterator.hasNext()) {
            FormData extractedData = dataIterator.next();
            for (String genre : extractedData.getGenres()) {
                outGenres.merge(genre, 1, Integer::sum);
            }
            outAuthors.merge(extractedData.getAuthor(), 1, Integer::sum);
            outAbouts.put(extractedData.getTimeStamp(), extractedData.getAbout());

        }

        outGenres = outGenres.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        outAuthors = outAuthors.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
        outAbouts = outAbouts.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        writer.write("<html><head><title>HashMap Display</title></head><body>");
        writer.write("<h1>Результаты Голосования</h1>");
        writer.write("<h2>Жанры</h2><ul>");
        for (Map.Entry<String, Integer> entry : outGenres.entrySet()) {
            writer.write("<li>" + entry.getKey() + ": " + entry.getValue() + "</li>");
        }
        writer.write("</ul>");

        writer.write("<h2>Исполнители</h2><ul>");
        for (Map.Entry<String, Integer> entry : outAuthors.entrySet()) {
            writer.write("<li>" + entry.getKey() + ": " + entry.getValue() + "</li>");
        }
        writer.write("</ul>");

        writer.write("<h2>Комментарии</h2><ul>");
        for (Map.Entry<LocalDateTime, String> entry : outAbouts.entrySet()) {
            writer.write("<li>" + entry.getKey() + ": " + entry.getValue() + "</li>");
        }
        writer.write("</ul>");
        writer.write("</body></html>");
    }


}
