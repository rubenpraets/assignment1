package session;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;
import rental.*;

@Remote
public interface CarRentalSessionRemote {

    Set<String> getAllRentalCompanies();
    void createQuote(String clientName, Date start, Date end,
			String carType, String region);
    List<Quote> getCurrentQuotes();
    List<Reservation> confirmQuotes() throws ReservationException;
    void checkForAvailableCarTypes(Date start, Date end) throws Exception;
    
}
