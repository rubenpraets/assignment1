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
public class SessionManager implements SessionManagerRemote {

    /**
     * 
     * @param crc
     * The CarRentalCompany for which you want to list all CarTypes.
     * @return An ArrayList containing all CarTypes 
     * in the given CarRentalCompany.
     */
    public List<CarType> getCarTypesForCompany(CarRentalCompany crc) {
        return new ArrayList(crc.getAllCarTypes());
    }
    
    public int getNbReservations(CarType type, CarRentalCompany crc) {
        int result = 0;
        for (Car car: crc.getCars()){
            if (car.getType().equals(type)) {
                result += car.getAllReservations().size();
            }        
        }
        return result;
    }
}
