package pl.solvd.concerthall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.services.MyBatisImpl.AuthorTypesService;
import pl.solvd.concerthall.services.MyBatisImpl.AuthorsService;
import pl.solvd.concerthall.services.interfaces.IAuthorTypeService;
import pl.solvd.concerthall.services.interfaces.IAuthorsService;

public class App {
    public static final Logger LOG = LogManager.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        IAuthorsService authorsService = new AuthorsService();
        LOG.info(authorsService.getEntityById(1L));
        IAuthorTypeService authorTypeService = new AuthorTypesService();
        LOG.info(authorTypeService.getEntityById(1L));
    }
}
