package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainobject.CarDO;
import com.mytaxi.domainvalue.GeoCoordinate;
import com.mytaxi.domainvalue.OnlineStatus;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {
	@JsonIgnore
	private Long id;

	@NotNull(message = "Username can not be null!")
	private String username;

	@NotNull(message = "Password can not be null!")
	private String password;

	private GeoCoordinate coordinate;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OnlineStatus onlineStatus;

	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "id", referencedColumnName = "id")
	// private CarDO carDo;

	private DriverDTO() {
	}

	private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, OnlineStatus onlineStatus) // ,
																														// CarDO
																														// carDo
	{
		this.id = id;
		this.username = username;
		this.password = password;
		this.coordinate = coordinate;
		this.onlineStatus = onlineStatus;
		// this.carDo = carDo;
	}

	public static DriverDTOBuilder newBuilder() {
		return new DriverDTOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public GeoCoordinate getCoordinate() {
		return coordinate;
	}

	// /**
	// * @return the carDo
	// */
	// public CarDO getCarDo() {
	// return carDo;
	// }
	//
	//
	// /**
	// * @param carDo the carDo to set
	// */
	// public void setCarDo(CarDO carDo) {
	// this.carDo = carDo;
	// }

	/**
	 * @return the onlineStatus
	 */
	public OnlineStatus getOnlineStatus() {
		return onlineStatus;
	}

	public static class DriverDTOBuilder {
		private Long id;
		private String username;
		private String password;
		private GeoCoordinate coordinate;
		private OnlineStatus onlineStatus;

		// @OneToOne(cascade = CascadeType.ALL)
		// @JoinColumn(name = "id", referencedColumnName = "id")
		// private CarDO carDo;

		public DriverDTOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public DriverDTOBuilder setUsername(String username) {
			this.username = username;
			return this;
		}

		public DriverDTOBuilder setPassword(String password) {
			this.password = password;
			return this;
		}

		public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate) {
			this.coordinate = coordinate;
			return this;
		}

		// /**
		// * @param carDo the carDo to set
		// */
		// public DriverDTOBuilder setCarDo(CarDO carDo) {
		// this.carDo = carDo;
		// return this;
		// }

		/**
		 * @param onlineStatus
		 *            the onlineStatus to set
		 */
		public DriverDTOBuilder setOnlineStatus(OnlineStatus onlineStatus) {
			this.onlineStatus = onlineStatus;
			return this;
		}

		public DriverDTO createDriverDTO() {
			return new DriverDTO(id, username, password, coordinate, onlineStatus);
		}

	}
}
