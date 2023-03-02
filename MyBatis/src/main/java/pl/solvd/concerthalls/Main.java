package pl.solvd.concerthalls;

import pl.solvd.concerthalls.services.MyBatisImpl.*;
import pl.solvd.concerthalls.services.interfaces.*;

public class Main {

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
        //authorsService.addEntity(new Authors(26L, "Alexandr", "Pushkin"));
        //System.out.println(authorsService.getEntityById(1L));
//       compositionService.findAuthorsByCompositionId(1L);
//        authorsService.findCompositionsByAuthorsId(1L);
        // LOG.info(authorTypeService.getEntityById(1L));
//        authorTypesService.findAuthorsByAuthorTypesId(1L);
//        authorsService.findAuthorTypesByAuthorsId(1L);
//      compositionService.findProgramsByCompositionId(1L);
    }
}
