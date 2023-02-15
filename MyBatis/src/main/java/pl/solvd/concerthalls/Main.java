package pl.solvd.concerthalls;

import pl.solvd.concerthalls.services.MyBatisImpl.AuthorTypesService;
import pl.solvd.concerthalls.services.MyBatisImpl.AuthorsService;
import pl.solvd.concerthalls.services.interfaces.IAuthorTypeService;
import pl.solvd.concerthalls.services.interfaces.IAuthorsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {
    Logger LOG = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws Exception {
        IAuthorsService authorsService = new AuthorsService();
        System.out.println(authorsService.getEntityById(1L));
        IAuthorTypeService authorTypeService = new AuthorTypesService();
       // LOG.info(authorTypeService.getEntityById(1L));
    }
}
