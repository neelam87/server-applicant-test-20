package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarMapper;
import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarModelDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarService;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController
{

    private final CarService carService;


    @Autowired
    public CarController(final CarService carService)
    {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@PathVariable long carId) throws EntityNotFoundException
    {
        return CarMapper.makecarDTO(carService.findCarById(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException
    {    	
        CarDO carDo = CarMapper.makeCarDO(carDTO);
        return CarMapper.makecarDTO(carService.create(carDo));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@PathVariable long carId) throws EntityNotFoundException
    {
    	carService.delete(carId);
    }


    @PutMapping("/{carId}")
    public void updateLocation(
        @PathVariable long carId, @RequestParam long rating, @RequestParam Boolean convertible, @RequestParam long seatCount)
        throws EntityNotFoundException
    {
    	carService.updateCarDetails(carId, rating, convertible, seatCount);
    }
    
//    @GetMapping("/carModel/{carModelId}")
//    public long selectCar(@PathVariable String carModelId) throws EntityNotFoundException
//    {
//    	List<CarDO> carListByModelNAme = carService.findCarByModelName(carModelId);
//    	return carListByModelNAme.size();
//       // return DriverMapper.makeDriverDTO(driverService.find(driverId));
//    }
}
