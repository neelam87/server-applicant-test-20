package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverCarRepository extends CrudRepository<DriverCarDO, Long>
{
	DriverCarDO findByDriverId(Long driverId);

	DriverCarDO findByCarId(Long carId);

	DriverCarDO findByCarDriverId(Long carDriverId);
}
