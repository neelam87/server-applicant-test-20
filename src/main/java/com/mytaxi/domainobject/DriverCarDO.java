package com.mytaxi.domainobject;

import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "driverCar", uniqueConstraints = @UniqueConstraint(name = "uc_driverCar", columnNames = { "driverId" }))
public class DriverCarDO {

	@Id
	@GeneratedValue
	private Long carDriverId;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Column(nullable = false)
	@NotNull(message = "CarId can not be null!")
	private Long carId;

	@Column(nullable = false)
	@NotNull(message = "DriverId can not be null!")
	private Long driverId;

	@Column(nullable = false)
	private Boolean deleted = false;

	@Column(nullable = false)
	private Boolean available = false;

	private DriverCarDO() {
	}

	public DriverCarDO(Long carId, Long driverId) {
		this.carId = carId;
		this.driverId = driverId;
		this.deleted = false;
		this.available = false;
	}

	/**
	 * @return the carDriverId
	 */
	public Long getCarDriverId() {
		return carDriverId;
	}

	/**
	 * @return the dateCreated
	 */
	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	/**
	 * @return the carId
	 */
	public Long getCarId() {
		return carId;
	}

	/**
	 * @return the driverId
	 */
	public Long getDriverId() {
		return driverId;
	}

	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * @return the available
	 */
	public Boolean getAvailable() {
		return available;
	}

}
