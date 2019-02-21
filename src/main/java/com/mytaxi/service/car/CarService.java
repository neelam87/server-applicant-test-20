package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarModelDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import java.util.List;

public interface CarService
{
    CarDO findCarById(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

	void updateCarDetails(long carId, long rating, Boolean convertible, long seatCount) throws EntityNotFoundException;

	List<CarDO> findCarByCarModelId(Long carModelId);

	void updateCarAvailabilty(long carId, Boolean available) throws EntityNotFoundException;

	CarDO findByLicencePlate(String licencePlate);

	

}
