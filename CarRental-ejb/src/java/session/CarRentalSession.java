package session;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import rental.*;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {
    
    List<Quote> quotes = new ArrayList<Quote>();

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    @Override
    public void confirmQuotes() throws ReservationException{
        List<Reservation> reservations = new ArrayList<Reservation>();
        List<Quote> quotes = getCurrentQuotes();
        try{
            for (Quote quote: quotes){
                CarRentalCompany crc = RentalStore.getRental(quote);
                Reservation reservation = crc.confirmQuote(quote);
                reservations.add(reservation);
            }
        } catch(ReservationException e){
            for(Reservation reservation: reservations){
                CarRentalCompany crc = RentalStore.getRental(reservation);
                crc.cancelReservation(reservation);
                throw e;
            }
        }
    }
    
    @Override
    public void createQuote(String clientName, Date start, Date end,
			String carType, String region){
        ReservationConstraints constraints = new ReservationConstraints(start, end, carType, region);
        Quote quote = null;
        for(CarRentalCompany crc: RentalStore.getRentals().values()){
            try{
                quote = crc.createQuote(constraints, clientName);
                break;
            } catch(ReservationException e){
                //do nothing
            }
        }
        if(quote == null){
            System.out.println("Impossible demands!");
        } else {
            addQuote(quote);
        }
    }

    @Override
    public List<Quote> getCurrentQuotes() {
        return quotes;
    }

    private void addQuote(Quote quote){
        getCurrentQuotes().add(quote);
    }
    
}
