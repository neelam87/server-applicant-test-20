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
public class DefaultCarModelService implements CarModelService
{

    private static final Logger LOG = LoggerFactory.getLogger(DefaultCarModelService.class);

    private final com.mytaxi.dataaccessobject.CarModelRepository carModelRepository;


    public DefaultCarModelService(final com.mytaxi.dataaccessobject.CarModelRepository carModelRepository)
    {
        this.carModelRepository = carModelRepository;
    }


	/**
     * Creates a new car details.
     *
     * @param carDO
     * @return
     * @throws ConstraintsViolationException if a car already exists with the given licencePlate, ... .
     */
    @Override
    public CarModelDO create(CarModelDO carModelDO) throws ConstraintsViolationException
    {
    	CarModelDO carModel;
        try
        {
        	carModel = carModelRepository.save(carModelDO);
        }
        catch (DataIntegrityViolationException e)
        {
            LOG.warn("ConstraintsViolationException while creating a car: {}", carModelDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return carModel;
    }
    
    /**
     * Selects a carModel by model name.
     *
     * @param carId
     * @return found car details
     * @throws EntityNotFoundException if no car with the given model name was found.
     */
    public CarModelDO findCarByModelName(String modelName) throws EntityNotFoundException
    {
        return carModelRepository.findByModelName(modelName);
           //.orElseThrow(() -> new EntityNotFoundException("Could not find entity with modelName: " + modelName));
    }
    
    
//
//
//    /**
//     * Deletes an existing car by id.
//     *
//     * @param carId
//     * @throws EntityNotFoundException if no car with the given id was found.
//     */
//    @Override
//    @Transactional
//	public void delete(Long carId) throws EntityNotFoundException {
//		CarDO carDo = findCarById(carId);
//		carDo.setDeleted(true);
//		
//	}
//
//
//	/**
//     * Update the car details.
//     *
//     * @param carId
//     * @param rating
//     * @param convertible
//     * @param seatCount
//     * @throws EntityNotFoundException
//     */
//	@Override
//    @Transactional
//	public void updateCarDetails(long carId, long rating, Boolean convertible, long seatCount)
//			throws EntityNotFoundException {
//		  CarDO carDo = findCarById(carId);
//		  carDo.setRating(rating);
//		  carDo.setConvertible(convertible);
//		  carDo.setSeatCount(seatCount);
//		
//	}
//


}
