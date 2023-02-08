package pl.solvd.concerthall;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.solvd.concerthall.services.*;

public class App {
    public static final Logger LOG = LogManager.getLogger(App.class.getName());
    public static void main(String[] args) throws Exception {
        AuthorsService authorsService = new AuthorsService();
        AuthorTypesService authorTypesService = new AuthorTypesService();
        BillService billService = new BillService();
        CompositionService compositionService = new CompositionService();
        ConcertHallsService concertHallsService = new ConcertHallsService();
        CustomerService customerService = new CustomerService();
        GenreService genreService = new GenreService();
        ImagesService imagesService = new ImagesService();
        MySeatService mySeatService = new MySeatService();
        NumOfSeatsService numOfSeatsService = new NumOfSeatsService();
        OrderService orderService = new OrderService();
        OrganizationService organizationService = new OrganizationService();
        PosterService posterService = new PosterService();
        PriceLevelService priceLevelService = new PriceLevelService();
        ProgramService programService = new ProgramService();
        TicketService ticketService = new TicketService();
    }
}
