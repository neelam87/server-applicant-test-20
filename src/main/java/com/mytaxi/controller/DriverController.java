package com.mytaxi.controller;

import com.mytaxi.controller.mapper.DriverCarMapper;
import com.mytaxi.controller.mapper.DriverMapper;
import com.mytaxi.datatransferobject.DriverCarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarModelDO;
import com.mytaxi.domainobject.DriverCarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.OnlineStatus;
import com.mytaxi.exception.CarAlreadyInUseException;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.DataNotFoundException;
import com.mytaxi.exception.EntityNotFoundException;
import com.mytaxi.service.car.CarModelService;
import com.mytaxi.service.car.CarService;
import com.mytaxi.service.driver.DriverService;
import com.mytaxi.service.driverCab.DriverCarService;

import java.util.ArrayList;
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
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

	private final DriverService driverService;
	private final CarService carService;
	private final CarModelService carModelService;
	private final DriverCarService driverCarService;

	@Autowired
	public DriverController(final DriverService driverService, final CarService carService,
			final CarModelService carModelService, DriverCarService driverCarService) {
		this.driverService = driverService;
		this.carService = carService;
		this.carModelService = carModelService;
		this.driverCarService = driverCarService;
	}

	@GetMapping("/{driverId}")
	public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException {
		return DriverMapper.makeDriverDTO(driverService.find(driverId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
		DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
		return DriverMapper.makeDriverDTO(driverService.create(driverDO));
	}

	@DeleteMapping("/{driverId}")
	public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException {
		driverService.delete(driverId);
	}

	@PutMapping("/{driverId}")
	public void updateLocation(@PathVariable long driverId, @RequestParam double longitude,
			@RequestParam double latitude) throws EntityNotFoundException {
		driverService.updateLocation(driverId, longitude, latitude);
	}

	@GetMapping
	public List<DriverDTO> findDriversByStatus(@RequestParam OnlineStatus onlineStatus, @RequestParam String username,
			@RequestParam String licencePlate, @RequestParam Long rating, @RequestParam Long seatCount,
			@RequestParam EngineType engineType, @RequestParam Boolean convertible) throws EntityNotFoundException, DataNotFoundException {
		List<DriverDO> driverList = new ArrayList<>();
		
		if(username!=null && onlineStatus!=null){
			DriverDO driver = driverService.findByUsername(username);
			if(driver!=null){
				if(driver.getOnlineStatus().equals(onlineStatus)){
					DriverCarDO driverCarMap = driverCarService.findByDriverId(driver.getId());
					if(driverCarMap!=null){
						findCar(driverCarMap, licencePlate, rating, seatCount, engineType, convertible);
					}
					else{
						throw new DataNotFoundException("No match found for this driver with any car");
					}
				}
				else{
					throw new DataNotFoundException("No match found for this onlineStatus");
				}
				driverList.add(driver);
			}
			else{
				throw new DataNotFoundException("No match found for this driver id");
			}
			
		}
		return DriverMapper.makeDriverDTOList(driverList);		
	}
	
	@GetMapping("/selectCar")
	public void selectCar(@RequestParam long driverId, @RequestParam String carModelName) throws EntityNotFoundException, ConstraintsViolationException, CarAlreadyInUseException, DataNotFoundException 
	{  
		CarModelDO carIdByModelName = carModelService.findCarByModelName(carModelName);
		if (carIdByModelName != null) {
			List<CarDO> carListByModelIdList = carService.findCarByCarModelId(carIdByModelName.getCarModelId());
			for(CarDO car: carListByModelIdList){
				DriverCarDO checkMapping = null;
					checkMapping = driverCarService.findByDriverId(driverId);
					if(car.getAvailable() && checkMapping==null){
						Long driverCarId = 0l;
						DriverCarDTO driverCarDTO = new DriverCarDTO(driverCarId, car.getId(), driverId);
						DriverCarDO driverCarDO = DriverCarMapper.makeDriverCarDO(driverCarDTO);
						driverCarService.create(driverCarDO);
						carService.updateCarAvailabilty(car.getId(), false);
					}else{
						throw new CarAlreadyInUseException("Car is used by another driver: " + car.getAvailable());					
					}
					break;
			    }			
			}
		 else 
		   {
		      throw new DataNotFoundException("No entry found for entity with modelName: " + carModelName);
	       }
   }
	
	@DeleteMapping("/deSelectCar")
	public void deSelectCar(@RequestParam long driverId) throws EntityNotFoundException, DataNotFoundException 
	{
		// find the car used by driverId
		DriverCarDO driverCarDO = driverCarService.findByDriverId(driverId);

		// remove the entry from driver car mapping table
		if (driverCarDO.getCarDriverId() != 0l && !driverCarDO.getDeleted()) {
			carService.updateCarAvailabilty(driverCarDO.getCarId(), true);
			driverCarService.delete(driverCarDO.getCarDriverId());
		} else {
			throw new DataNotFoundException("No entry found for this driver id: " + driverId);
		}
	}


	private void findCar(DriverCarDO driverCarMap, String licencePlate, Long rating, Long seatCount, EngineType engineType, Boolean convertible) throws EntityNotFoundException, DataNotFoundException {
		CarDO car=carService.findCarById(driverCarMap.getCarId());
		if(car!=null){
			if(!car.getLicencePlate().equals(licencePlate)){
				throw new DataNotFoundException("No match found for this licence");
			}
			else if(!car.getRating().equals(rating)){
				throw new DataNotFoundException("No match found for this rating");
			}
			else if(!car.getSeatCount().equals(seatCount)){
				throw new DataNotFoundException("No match found for this seatCount");
			}
			else if(!car.getEngineType().equals(engineType)){
				throw new DataNotFoundException("No match found for this engineType");
			}
			else if(!car.getConvertible().equals(convertible)){
				throw new DataNotFoundException("No match found for this convertible");
			}			
		}else{
			throw new DataNotFoundException("No match found for this car");
		}
	}

}
