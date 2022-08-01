package com.pavlenko.kyrylo.controller.listener;

import com.pavlenko.kyrylo.controller.command.Command;
import com.pavlenko.kyrylo.controller.command.impl.admin.GetAllUsersCommand;
import com.pavlenko.kyrylo.controller.command.impl.common.GetCatalogCommand;
import com.pavlenko.kyrylo.controller.command.impl.common.GetHomeCommand;
import com.pavlenko.kyrylo.controller.command.impl.common.GetLogOutCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.GetLoginCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.GetRegistrationCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.PostLoginCommand;
import com.pavlenko.kyrylo.controller.command.impl.guest.PostRegistrationCommand;
import com.pavlenko.kyrylo.controller.util.UriPath;
import com.pavlenko.kyrylo.model.dao.UserDao;
import com.pavlenko.kyrylo.model.dao.impl.UserDaoImpl;
import com.pavlenko.kyrylo.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionListener;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@WebListener
public class ContextListener implements ServletContextListener, HttpSessionListener {

    private static final Logger LOG = LogManager.getLogger(ContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOG.info("Start context initialization");
        ServletContext context = sce.getServletContext();
        initDatasource(context);
        LOG.info("DataSource initialized");
        initServices(context);
        LOG.info("Services initialized");
    }

    private void initDatasource(ServletContext context) {
        String dataSourceName = context.getInitParameter("dataSource");
        Context jndiContext = null;
        try {
            jndiContext = (Context) new InitialContext().lookup("java:/comp/env");
            DataSource dataSource = (DataSource) jndiContext.lookup(dataSourceName);
            context.setAttribute("dataSource", dataSource);
            LOG.info("context.setAttribute 'dataSource': {}", dataSource.getClass().getName());
        } catch (NamingException e) {
            throw new IllegalStateException("Cannot initialize dataSource", e);
        }
    }

    private void initServices(ServletContext context){
        Map<String, Command> getCommands = new HashMap<>();
        Map<String, Command> postCommands = new HashMap<>();

        putGetCommands(getCommands, context);
        putPostCommands(postCommands, context);

        context.setAttribute("getCommands", getCommands);
        LOG.info("getCommands initialized");
        context.setAttribute("postCommands", postCommands);
        LOG.info("postCommands initialized");
    }

    private void putGetCommands(Map<String, Command> commands, ServletContext context) {
        UserDao userDao = new UserDaoImpl((DataSource) context.getAttribute("dataSource"));
        UserService userService = new UserService(userDao);

        commands.put(UriPath.LOGIN, new GetLoginCommand());
        commands.put(UriPath.LOGOUT, new GetLogOutCommand());
        commands.put(UriPath.REGISTRATION, new GetRegistrationCommand());
        commands.put(UriPath.ADMIN_USER_LIST, new GetAllUsersCommand(userService));
        commands.put(UriPath.CATALOG, new GetCatalogCommand());
        commands.put(UriPath.INDEX, new GetHomeCommand());
    }

    private void putPostCommands(Map<String, Command> commands, ServletContext context) {
        UserDao userDao = new UserDaoImpl((DataSource) context.getAttribute("dataSource"));
        UserService userService = new UserService(userDao);

        commands.put(UriPath.LOGIN, new PostLoginCommand(userService));
        commands.put(UriPath.REGISTRATION, new PostRegistrationCommand(userService));
    }
}
