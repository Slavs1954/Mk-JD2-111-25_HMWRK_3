package controller;

import dto.FeatureToggle;
import jakarta.servlet.annotation.WebServlet;
import service.ServiceFactory;
import service.api.EFeature;
import service.api.IFeatureService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/feature")
public class FeatureFlagServlet extends HttpServlet {

    private static final IFeatureService featureService =
            ServiceFactory.getFeatureService();

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String flag = req.getParameter("flag");
        String value = req.getParameter("value");
        featureService.toggle(new FeatureToggle(
                EFeature.valueOf(flag), value)
        );
    }
}
