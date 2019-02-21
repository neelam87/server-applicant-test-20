package com.mytaxi.service.car;

import com.mytaxi.dataaccessobject.CarRepository;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarModelDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultCarService implements CarService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCarService.class);

    private final CarRepository carRepository;


    public DefaultCarService(final CarRepository carRepository)
    {
        this.carRepository = carRepository;
    }


    /**
     * Creates a new car details.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given licencePlate, ... .
     */
    @Override
    public CarDO create(CarDO carDO) throws ConstraintsViolationException
    {
    	CarDO car;
        try
        {
        	car = carRepository.save(carDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a car: {}", carDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }
    
    
    
    
    
    
    /**
     * Selects a car by id.
     *
     * @param carId
     * @return found car details
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    public CarDO findCarById(Long carId) throws EntityNotFoundException
    {
        return carRepository.findById(carId)
            .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }


    /**
     * Deletes an existing car by id.
     *
     * @param carId
     * @throws EntityNotFoundException if no car with the given id was found.
     */
    @Override
    @Transactional
	public void delete(Long carId) throws EntityNotFoundException {
		CarDO carDo = findCarById(carId);
		carDo.setDeleted(true);
		
	}


	/**
     * Update the car details.
     *
     * @param carId
     * @param rating
     * @param convertible
     * @param seatCount
     * @throws EntityNotFoundException
     */
	@Override
    @Transactional
	public void updateCarDetails(long carId, long rating, Boolean convertible, long seatCount)
			throws EntityNotFoundException {
		  CarDO carDo = findCarById(carId);
		  carDo.setRating(rating);
		  carDo.setConvertible(convertible);
		  carDo.setSeatCount(seatCount);
		
	}
	
	/**
     * Update the car availabilty.
     *
     * @param carId
     * @param available
     * @throws EntityNotFoundException
     */
	@Override
    @Transactional
	public void updateCarAvailabilty(long carId, Boolean available)
			throws EntityNotFoundException {
		  CarDO carDo = findCarById(carId);
		  carDo.setAvailable(available);
		
	}


	@Override
	public List<CarDO> findCarByCarModelId(Long carModelId) {
		 return carRepository.findCarByCarModelId(carModelId);
	}


	@Override
	public CarDO findByLicencePlate(String licencePlate) {
		return carRepository.findByLicencePlate(licencePlate);
		
	}


}
