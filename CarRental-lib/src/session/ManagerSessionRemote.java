/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.List;
import javax.ejb.Remote;
import rental.CarRentalCompany;
import rental.CarType;

/**
 *
 * @author Joris
 */
@Remote
public interface ManagerSessionRemote {
    abstract List<CarType> getCarTypesForCompany(CarRentalCompany crc);
    abstract int getNbReservations(String type, String crc);
    int getNumberOfReservationsBy(String clientName);
}
