package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface CarRepository extends CrudRepository<CarDO, Long>
{

    List<CarDO> findById(long id);
	List<CarDO> findCarByCarModelId(Long carModelId);
	CarDO findByLicencePlate(String licencePlate);
}
