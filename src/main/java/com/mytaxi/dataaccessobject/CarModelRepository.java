package com.mytaxi.dataaccessobject;

import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainobject.CarModelDO;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface CarModelRepository extends CrudRepository<CarModelDO, Long>
{

    List<CarModelDO> findById(long id);

	CarModelDO findByModelName(String modelName);
}
