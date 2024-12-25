package filter;


import java.util.HashMap;
import java.util.Map;

import org.thymeleaf.web.IWebRequest;

import contr.*;


public class MapController {

    private static Map<String, IController> controllersByURL;
    static {
        controllersByURL = new HashMap<String, IController>();
        //controllersByURL.put("/", new HomeController());
        controllersByURL.put("/books", new BookController());
        controllersByURL.put("/readers", new ReaderController());
        controllersByURL.put("/issues", new IssueController());
        controllersByURL.put("/login", new LoginController());
    }

    public static IController resolveControllerForRequest(final IWebRequest request) {
        /*final String path = request.getPathWithinApplication();

        System.out.println("Resolved path: " + path);
        System.out.println("Available paths: " + controllersByURL.keySet());

        System.out.println(path);
        return controllersByURL.get(path);*/

        // Получаем путь из запроса
        final String path = request.getPathWithinApplication();

        // Выводим отладочную информацию
        System.out.println("Resolved path: " + path);
        System.out.println("Available paths: " + controllersByURL.keySet());

        // Пытаемся найти контроллер по пути
        IController controller = controllersByURL.get(path);

        // Если контроллер не найден, проверяем вариант с удалением завершающего "/"
        if (controller == null && path.endsWith("/")) {
            controller = controllersByURL.get(path.substring(0, path.length() - 1));
        }

        // Еще раз выводим информацию о найденном контроллере
        System.out.println("Controller for path " + path + ": " + (controller != null ? controller.getClass().getSimpleName() : "None"));

        return controller;
    }

    private MapController() {
        super();
    }

}