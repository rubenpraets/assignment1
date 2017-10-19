/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import rental.*;

/**
 *
 * @author Joris
 */
@Stateless
public class ManagerSession implements ManagerSessionRemote {

    /**
     * 
     * @param crc
     * The CarRentalCompany for which you want to list all CarTypes.
     * @return An ArrayList containing all CarTypes 
     * in the given CarRentalCompany.
     */
    @Override
    public List<CarType> getCarTypesForCompany(CarRentalCompany crc) {
        return new ArrayList(crc.getAllCarTypes());
    }
    
    @Override
    public int getNbReservations(String typeString, String crcString) {
        int result = 0;
        CarRentalCompany crc = RentalStore.getRental(crcString);
        CarType type = crc.getType(typeString);
        
        for (Car car: crc.getCars()){
            if (car.getType().equals(type)) {
                result += car.getAllReservations().size();
            }        
        }
        return result;
    }
    
    public String bestRenter(CarRentalCompany crc){
        return crc.bestRenter();
    }
    
    @Override
    public int getNumberOfReservationsBy(String clientName){
        return RentalStore.getNumberOfReservationsBy(clientName);
    }
}
