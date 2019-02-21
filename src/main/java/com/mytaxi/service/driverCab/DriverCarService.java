package com.mytaxi.service.driverCab;

import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface DriverCarService
{

	DriverCarDO findByDriverId(Long driverId) throws EntityNotFoundException;

    DriverCarDO create(DriverCarDO driverCarDO) throws ConstraintsViolationException;

    void delete(Long carDriverId) throws EntityNotFoundException;
//
//    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    DriverCarDO findByCarId(Long id);

    //List<DriverDO> find(OnlineStatus onlineStatus);

}
