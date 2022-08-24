package com.pavlenko.kyrylo.controller;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.util.UriPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    private final Logger logger = LogManager.getLogger(DispatcherServlet.class);

    private Map<String, Command> getCommands = new HashMap<>();
    private Map<String, Command> postCommands = new HashMap<>();

    /**
     * Gets commands from the context
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        getCommands = (Map<String, Command>) config.getServletContext().getAttribute("getCommands");
        postCommands = (Map<String, Command>) config.getServletContext().getAttribute("postCommands");


    }

    /**
     * Checks request URI according to command container,
     * Sends an error or executes command
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, Map<String, Command> commands) throws IOException, ServletException {
        String uri = request.getRequestURI().substring(request.getContextPath().length());
        Command command = commands.get(uri);
        if (command == null) {
            logger.warn("page: {} not found", uri);
            response.sendError(404);
            return;
        }

        String result = command.execute(request);
        renderPage(request, response, result);
    }

    private void renderPage(HttpServletRequest req, HttpServletResponse resp, String pagePath) throws ServletException, IOException {
        if (pagePath.startsWith(UriPath.REDIRECT)) {
            resp.sendRedirect(pagePath.replace(UriPath.REDIRECT, UriPath.PROJECT_NAME));
        } else {
            req.getRequestDispatcher(pagePath).forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, getCommands);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, postCommands);
    }


}
