package client;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import session.CarRentalSessionRemote;
import session.ManagerSessionRemote;

public class Main extends AbstractTestAgency{
    
    @EJB
    static CarRentalSessionRemote session;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        //System.out.println("found rental companies: "+session.getAllRentalCompanies());
        Main main = new Main("simpleTrips");
        main.run();
    }

    public Main(String scriptFile) {
        super(scriptFile);
    }

    @Override
    protected Object getNewReservationSession(String name) throws Exception {
        InitialContext context = new InitialContext();
        Object reservationSession = (CarRentalSessionRemote) 
                    context.lookup(CarRentalSessionRemote.class.getName());
        //sessions.put(name, reservationSession);
        return reservationSession;
    }

    @Override
    protected Object getNewManagerSession(String name, String carRentalName) throws Exception {
        InitialContext context = new InitialContext();
        Object managerSession = (ManagerSessionRemote) 
                    context.lookup(ManagerSessionRemote.class.getName());
        return managerSession;
    }

    @Override
    protected void checkForAvailableCarTypes(Object session, Date start, Date end) throws Exception {
        ((CarRentalSessionRemote)session).checkForAvailableCarTypes(start, end);
    }

    @Override
    protected void addQuoteToSession(Object session, String name, Date start, Date end, String carType, String region) throws Exception {
        ((CarRentalSessionRemote)session).createQuote(name, start, end, carType, region);
    }

    @Override
    protected List confirmQuotes(Object session, String name) throws Exception {
        return ((CarRentalSessionRemote)session).confirmQuotes();
    }

    @Override
    protected int getNumberOfReservationsForCarType(Object ms, String carRentalName, String carType) throws Exception {
        return ((ManagerSessionRemote)ms).getNbReservations(carType, carRentalName);
    }

    @Override
    protected int getNumberOfReservationsBy(Object ms, String clientName) throws Exception {
        return ((ManagerSessionRemote)ms).getNumberOfReservationsBy(clientName);
    }
}
