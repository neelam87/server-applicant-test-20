package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarDTO;
import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper
{
    public static CarDO makeCarDO(CarDTO carDTO)
    {
        return new CarDO(carDTO.getLicencePlate());
    }


    public static CarDTO makecarDTO(CarDO carDO)
    {
    	CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
            .setId(carDO.getId())
            .setLicencePlate(carDO.getLicencePlate())
            .setCarModelId(carDO.getCarModelId())
            .setConvertible(carDO.getConvertible())
            .setRating(carDO.getRating())
            .setSeatCount(carDO.getSeatCount())
            .setAvailable(carDO.getAvailable());
    	

        EngineType engineType = carDO.getEngineType();
        if (engineType != null)
        {
        	carDTOBuilder.setEngineType(engineType);
        }

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> drivers)
    {
        return drivers.stream()
            .map(CarMapper::makecarDTO)
            .collect(Collectors.toList());
    }
}
