package session;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateful;
import rental.*;

@Stateful
public class CarRentalSession implements CarRentalSessionRemote {

    @Override
    public Set<String> getAllRentalCompanies() {
        return new HashSet<String>(RentalStore.getRentals().keySet());
    }
    
    @Override
    public void confirmQuotes(){
        
    }
    
    @Override
    public Quote createQuote(String clientName, Date start, Date end,
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
        return quote;
    }

    @Override
    public List<Quote> getCurrentQuotes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
