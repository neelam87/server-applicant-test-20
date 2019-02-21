package com.mytaxi.controller.mapper;

import com.mytaxi.datatransferobject.CarModelDTO;
import com.mytaxi.domainobject.CarModelDO;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

public class CarModelMapper
{
    public static CarModelDO makeCarModelDO(CarModelDTO carModelDTO)
    {
        return new CarModelDO(carModelDTO.getModelName());
    }


    public static CarModelDTO makeCarModelDTO(@Valid CarModelDO carModelDo)
    {
    	CarModelDTO.CarModelDTOBuilder carModelDTOBuilder = CarModelDTO.newBuilder()
            .setId(carModelDo.getCarModelId())
            .setModelName(carModelDo.getModelName())
            .setModelDescription(carModelDo.getModelDescription());
    	

        return carModelDTOBuilder.createCarModelDTO();
    }


    public static List<CarModelDTO> makeCarModelDTOList(Collection<CarModelDO> carModel)
    {
        return carModel.stream()
            .map(CarModelMapper::makeCarModelDTO)
            .collect(Collectors.toList());
    }
}
