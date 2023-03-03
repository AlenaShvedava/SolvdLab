package pl.solvd.concerthall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import pl.solvd.concerthall.services.impl.*;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DAOReader {
    public static final Logger LOG = LogManager.getLogger(DAOReader.class.getName());

    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        AuthorsService authorsService = new AuthorsService();
        authorsService.getEntityById(1L);
        //LOG.info(AuthorsService.getEntityById(1L));
        AuthorTypesService authorTypesService = new AuthorTypesService();
       // LOG.info(AuthorTypesService.getEntityById(1L));
        BillService billService = new BillService();
       // LOG.info(BillService.getEntityById(1L));
        CompositionService compositionService = new CompositionService();
       // LOG.info(CompositionService.getEntityById(1L));
        ConcertHallsService concertHallsService = new ConcertHallsService();
       // LOG.info(ConcertHallsService.getEntityById(1L));
        CustomerService customerService = new CustomerService();
       // LOG.info(CustomerService.getEntityById(1L));
        GenreService genreService = new GenreService();
      //  LOG.info(GenreService.getEntityById(1L));
        ImagesService imagesService = new ImagesService();
        //LOG.info(ImagesService.getEntityById(1L));
        MySeatService mySeatService = new MySeatService();
      //  LOG.info(MySeatService.getEntityById(1L));
        NumOfSeatsService numOfSeatsService = new NumOfSeatsService();
      //  LOG.info(NumOfSeatsService.getEntityById(1L));
        OrderService orderService = new OrderService();
       // LOG.info(OrderService.getEntityById(1L));
        OrganizationService organizationService = new OrganizationService();
       // LOG.info(OrganizationService.getEntityById(1L));
        PosterService posterService = new PosterService();
      //  LOG.info(PosterService.getEntityById(1L));
        PriceLevelService priceLevelService = new PriceLevelService();
    //    LOG.info(PriceLevelService.getEntityById(1L));
        ProgramService programService = new ProgramService();
     //   LOG.info(ProgramService.getEntityById(1L));
        TicketService ticketService = new TicketService();
     //   LOG.info(TicketService.getEntityById(1L));

    }
}