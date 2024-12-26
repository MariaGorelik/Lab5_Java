package filter;

import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.messageresolver.IMessageResolver;
import org.thymeleaf.messageresolver.StandardMessageResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.IServletWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;


import contr.*;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class MainFilter  implements Filter{
    private JakartaServletWebApplication application;
    private ITemplateEngine templateEngine;

    private String user;
    private String role;

    @Override
    public void init(FilterConfig filterConfig){
        this.application =
                JakartaServletWebApplication.buildApplication(filterConfig.getServletContext());

        this.templateEngine = buildTemplateEngine(this.application);

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!process((HttpServletRequest)request, (HttpServletResponse)response)) {
            chain.doFilter(request, response);
        }

    }

    private ITemplateEngine buildTemplateEngine(final IWebApplication application) {
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);
        IMessageResolver stringTemplateResolver = new StandardMessageResolver();
        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.setMessageResolver(stringTemplateResolver);
        return templateEngine;
    }

    private boolean process(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {
        try {
            request.getSession().setAttribute("calend",
                    Calendar.getInstance());

            final IServletWebExchange webExchange = this.application.buildExchange(request, response);
            final IWebRequest webRequest = webExchange.getRequest();
            final Writer writer = response.getWriter();

            if (webRequest.getPathWithinApplication().startsWith("/css") ||
                    webRequest.getPathWithinApplication().startsWith("/images") ||
                    webRequest.getPathWithinApplication().startsWith("/favicon")) {
                return false;
            }

            String requestURI = request.getRequestURI();

            if (requestURI.startsWith(request.getContextPath() + "/login") &&
                    "POST".equalsIgnoreCase(request.getMethod())) {
                handleLogin(request, response);
                response.sendRedirect(request.getContextPath());
                return true;
            }

            if (user == null &&
                    !requestURI.startsWith(request.getContextPath() + "/login")) {
                response.sendRedirect(request.getContextPath() + "/login");
                return true;
            }


            if (role != null) {
                if (role.equalsIgnoreCase("client") && (requestURI.startsWith(request.getContextPath() + "/readers") ||
                        requestURI.startsWith(request.getContextPath() + "/issues"))) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    return true;
                }
            }

            IController controller = MapController.resolveControllerForRequest(webRequest);
            if (controller == null) {
                //controller=new LoginController();
                return false;
            }
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            controller.process(webExchange, templateEngine, writer);
            return true;

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // Just ignore this
            }
            throw new ServletException(e);
        }
    }

    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        user = request.getParameter("user");
        role = request.getParameter("role");
    }

}