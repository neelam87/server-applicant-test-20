package com.mytaxi.service.driverCab;

import com.mytaxi.dataaccessobject.DriverCarRepository;
import com.mytaxi.dataaccessobject.DriverRepository;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have
 * business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverCarService implements DriverCarService {

	private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverCarService.class);

	private final DriverCarRepository driverCarRepository;

	public DefaultDriverCarService(final DriverCarRepository driverCarRepository) {
		this.driverCarRepository = driverCarRepository;
	}

	/**
	 * Selects a driver car map record by driver id.
	 *
	 * @param driverId
	 * @return found driver
	 * @throws EntityNotFoundException
	 *             if no driver with the given id was found.
	 */
	@Override
	public DriverCarDO findByDriverId(Long driverId) throws EntityNotFoundException {
		return driverCarRepository.findByDriverId(driverId);
	}

	private DriverCarDO findDriverCheckedIn(Long carDriverId) throws EntityNotFoundException {
		return driverCarRepository.findByCarDriverId(carDriverId);
				//.orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
	}

	/**
	 * Creates a new driver.
	 *
	 * @param driverDO
	 * @return
	 * @throws ConstraintsViolationException
	 *             if a driver already exists with the given username, ... .
	 */
	@Override
	public DriverCarDO create(DriverCarDO driverCarDO) throws ConstraintsViolationException {
		DriverCarDO driverCar;
		try {
			driverCar = driverCarRepository.save(driverCarDO);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("ConstraintsViolationException while creating a driver: {}", driverCarDO, e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return driverCar;
	}

	 /**
	 * Deletes an car from driver by id.
	 *
	 * @param driverId
	 * @throws EntityNotFoundException if no driver with the given id was
	 found.
	 */
	 @Override
	 @Transactional
	 public void delete(Long carDriverId) throws EntityNotFoundException
	 {
	 DriverCarDO driverCarDO = findDriverCheckedIn(carDriverId);
	 driverCarDO.setDeleted(true);
	 }

	@Override
	public DriverCarDO findByCarId(Long carId) {
		return driverCarRepository.findByCarId(carId);
		
	}
	
	
	// /**
	// * Update the location for a driver.
	// *
	// * @param driverId
	// * @param longitude
	// * @param latitude
	// * @throws EntityNotFoundException
	// */
	// @Override
	// @Transactional
	// public void updateLocation(long driverId, double longitude, double
	// latitude) throws EntityNotFoundException
	// {
	// DriverDO driverDO = findDriverChecked(driverId);
	// driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
	// }
	//
	//
	// /**
	// * Find all drivers by online state.
	// *
	// * @param onlineStatus
	// */
	// @Override
	// public List<DriverDO> find(OnlineStatus onlineStatus)
	// {
	// return driverRepository.findByOnlineStatus(onlineStatus);
	// }
	//
	//

}
