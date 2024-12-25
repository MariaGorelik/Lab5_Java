package contr;

import java.io.Writer;
import java.util.List;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import dao.LibraryDAO;
import entity.Reader;

public class ReaderController implements IController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        LibraryDAO libraryDAO = LibraryDAO.getInstance();
        List<Reader> readers = libraryDAO.loadAllReaders();
        ctx.setVariable("readers", readers);
        templateEngine.process("reader", ctx, writer);
    }
}
