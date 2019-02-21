package com.mytaxi.dataaccessobject;

import com.mytaxi.datatransferobject.DriverDTO;
import com.mytaxi.domainobject.DriverDO;
import com.mytaxi.domainvalue.OnlineStatus;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long>
{

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);

    @Query(value = "SELECT * FROM DRIVER driver where driver.username = ?0 AND driver.id = ?1", 
            nativeQuery=true)
    List<DriverDO> findAllDriverDO(DriverDTO driverDTO);

	DriverDO findByUsername(String username);
}
