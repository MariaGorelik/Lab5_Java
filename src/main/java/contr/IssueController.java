package contr;

import java.io.Writer;
import java.util.List;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import dao.LibraryDAO;
import dto.IssueDetailsDTO;

public class IssueController implements IController {

    @Override
    public void process(IWebExchange webExchange, ITemplateEngine templateEngine, Writer writer) throws Exception {
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        LibraryDAO libraryDAO = LibraryDAO.getInstance();
        List<IssueDetailsDTO> issues = libraryDAO.loadIssuesWithDetails();
        ctx.setVariable("issues", issues);
        templateEngine.process("issue", ctx, writer);
    }
}
