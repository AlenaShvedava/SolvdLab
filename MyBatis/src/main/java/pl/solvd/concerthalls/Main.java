package pl.solvd.concerthalls;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthalls.services.MyBatisImpl.AuthorTypesService;
import pl.solvd.concerthalls.services.MyBatisImpl.AuthorsService;
import pl.solvd.concerthalls.services.interfaces.IAuthorTypeService;
import pl.solvd.concerthalls.services.interfaces.IAuthorsService;
public class Main {
    Logger LOG = LogManager.getLogger(Main.class);
    public static void main(String[] args) throws Exception {
        IAuthorsService authorsService = new AuthorsService();
        authorsService.getEntityById(1L);
        //authorsService.addEntity(new Authors(26L, "Alexandr", "Pushkin"));
        //System.out.println(authorsService.getEntityById(1L));
        IAuthorTypeService authorTypeService = new AuthorTypesService();
       // LOG.info(authorTypeService.getEntityById(1L));
    }
}
