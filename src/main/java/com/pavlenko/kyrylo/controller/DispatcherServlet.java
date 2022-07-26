package com.pavlenko.kyrylo.controller;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.common.GetLogOutCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.GetLoginCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.GetRegistrationCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.PostLoginCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.PostRegistrationCommand;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.model.service.ServiceFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class DispatcherServlet extends HttpServlet {

    private final Map<String, Command> getCommands = new HashMap<>();
    private final Map<String, Command> postCommands = new HashMap<>();
    private static final String COMMAND_NOT_FOUND = "Command not found";

    /**
     * Creates an instance of serviceFactory,
     * Puts all commands in map containers by using putGetCommands() and putPostCommands()
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServiceFactory service = ServiceFactory.getInstance();
        putGetCommands(service);
        putPostCommands(service);
    }

    private void putGetCommands(ServiceFactory service) {
        getCommands.put(UriPath.LOGIN, new GetLoginCommand());
        getCommands.put(UriPath.LOGOUT, new GetLogOutCommand());
        getCommands.put(UriPath.REGISTRATION, new GetRegistrationCommand());
    }

    private void putPostCommands(ServiceFactory service) {
        postCommands.put(UriPath.LOGIN, new PostLoginCommand(service.createUserService()));
        postCommands.put(UriPath.REGISTRATION, new PostRegistrationCommand(service.createUserService()));
    }

    /**
     * Checks request URI according to command container,
     * Sends an error or executes command
     *
     * @param request
     * @param response
     * @param commands
     * @throws IOException
     * @throws ServletException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, Map<String, Command> commands) throws IOException, ServletException {
        Command command = commands.get(request.getRequestURI());
        if (command == null) {
            response.sendError(403);
            return;
        }
        String result = command.execute(request);
        renderPage(request, response, result);
    }

    private void renderPage(HttpServletRequest req, HttpServletResponse resp, String pagePath) throws ServletException, IOException {
        if (pagePath.startsWith(UriPath.REDIRECT)) {
            resp.sendRedirect(pagePath.replace(UriPath.REDIRECT, ""));
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
