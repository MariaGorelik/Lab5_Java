package filter;


import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.web.IWebRequest;

import contr.*;


public class MapController {

    private static Map<String, IController> controllersByURL;
    static {
        controllersByURL = new HashMap<String, IController>();
        controllersByURL.put("/books", new BookController());
        controllersByURL.put("/readers", new ReaderController());
        controllersByURL.put("/issues", new IssueController());
        controllersByURL.put("/login", new LoginController());
    }

    public static IController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        IController controller = controllersByURL.get(path);
        if (controller == null && path.endsWith("/")) {
            controller = controllersByURL.get(path.substring(0, path.length() - 1));
        }
        return controller;
    }

    private MapController() {
        super();
    }

}