package com.mytaxi.controller;

import com.mytaxi.controller.mapper.CarModelMapper;
import com.mytaxi.datatransferobject.CarModelDTO;
import com.mytaxi.domainobject.CarModelDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.service.car.CarModelService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/carModel")
public class CarModelController
{
    private final CarModelService carModelService;


    @Autowired
    public CarModelController(final CarModelService carModelService)
    {
       this.carModelService = carModelService;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarModelDTO createCarManufacturer(@Valid @RequestBody CarModelDTO carModelDTO) throws ConstraintsViolationException
    {    	
        CarModelDO carModelDo = CarModelMapper.makeCarModelDO(carModelDTO);
        return CarModelMapper.makeCarModelDTO(carModelService.create(carModelDo));
    }


}
