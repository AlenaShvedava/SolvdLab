package pl.solvd.concerthall;

import pl.solvd.concerthall.services.MyBatisImpl.*;
import pl.solvd.concerthall.services.interfaces.*;

public class MyBatisReader {
    public static void main(String[] args) {
        IAuthorsService authorsService = new AuthorsService();
        IAuthorTypeService authorTypesService = new AuthorTypesService();
        ICompositionService compositionService = new CompositionService();
        IProgramService programService = new ProgramService();
        IGenreService genreService = new GenreService();
        IOrganizationService organizationService = new OrganizationService();
        IConcertHallsService concertHallService = new ConcertHallsService();
        IPosterService posterService = new PosterService();
        IImagesService imageService = new ImagesService();
        IEventsService eventService = new EventsService();
        ICustomerService customerService = new CustomerService();
        INumOfSeatsService numOfSeatsService = new NumOfSeatsService();
        IMySeatService mySeatService = new MySeatService();
        IPriceLevelService priceLevelService = new PriceLevelService();
        IOrderService orderService = new OrderService();
        ITicketService ticketService = new TicketService();
        IBillService billService = new BillService();
        authorsService.getAll();
    }
}
