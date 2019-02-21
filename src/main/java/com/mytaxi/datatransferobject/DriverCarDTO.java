package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverCarDTO
{
	@JsonIgnore
    private Long carDriverId;

    @NotNull(message = "CarId can not be null!")
    private Long carId;

    @NotNull(message = "DriverId can not be null!")
    private Long driverId;


    private DriverCarDTO()
    {
    }

    public DriverCarDTO(Long carDriverId, Long carId, Long driverId)
    {
    	this.carDriverId = carDriverId;
        this.carId = carId;
        this.driverId = driverId;
    }


    public static DriverCarDTOBuilder newBuilder()
    {
        return new DriverCarDTOBuilder();
    }


    
    /**
	 * @return the carDriverId
	 */
    @JsonProperty
	public Long getCarDriverId() {
		return carDriverId;
	}

	/**
	 * @param carDriverId the carDriverId to set
	 */
	public void setCarDriverId(Long carDriverId) {
		this.carDriverId = carDriverId;
	}

	/**
	 * @return the carId
	 */
	public Long getCarId() {
		return carId;
	}

	/**
	 * @param carId the carId to set
	 */
	public void setCarId(Long carId) {
		this.carId = carId;
	}

	/**
	 * @return the driverId
	 */
	public Long getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}



	public static class DriverCarDTOBuilder
	{
        private Long carDriverId;
        private Long carId;
        private Long driverId;


        
        public DriverCarDTO createDriverCarDTO()
        {
            return new DriverCarDTO(carDriverId, carId, driverId);
        }



		/**
		 * @param carDriverId the carDriverId to set
		 */
		public void setCarDriverId(Long carDriverId) {
			this.carDriverId = carDriverId;
		}



		/**
		 * @param carId the carId to set
		 */
		public void setCarId(Long carId) {
			this.carId = carId;
		}



		/**
		 * @param driverId the driverId to set
		 */
		public void setDriverId(Long driverId) {
			this.driverId = driverId;
		}

    }
}
