package com.mytaxi.service.car;

import com.mytaxi.domainobject.CarModelDO;
import com.mytaxi.exception.ConstraintsViolationException;
import com.mytaxi.exception.EntityNotFoundException;

public interface CarModelService
{
        
    CarModelDO create(CarModelDO carModelDo) throws ConstraintsViolationException;

	CarModelDO findCarByModelName(String carModelName) throws EntityNotFoundException;
}
