package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mytaxi.domainvalue.EngineType;
import com.mytaxi.domainvalue.GeoCoordinate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO
{
    @JsonIgnore
    private Long id;

    @NotNull(message = "LicencePlate can not be null!")
    private String licencePlate;

    private Long seatCount;
    
    private Boolean convertible;
    
    private Long rating;
    
    private EngineType engineType;
    
    private Long carModelId;
    private Boolean available;
    
    
    private CarDTO()
    {
    }


    private CarDTO(Long id, String licencePlate, Long seatCount, 
    		Boolean convertible, Long rating, EngineType engineType, Long carModelId, Boolean available)
    {
        this.id = id;
        this.licencePlate = licencePlate;
        this.seatCount = seatCount;
        this.convertible = convertible;
        this.rating = rating;
        this.engineType = engineType;
        this.carModelId = carModelId;
        this.available = available;
    }


    public static CarDTOBuilder newBuilder()
    {
        return new CarDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


    

    /**
	 * @return the licencePlate
	 */
	public String getLicencePlate() {
		return licencePlate;
	}


	/**
	 * @return the seatCount
	 */
	public Long getSeatCount() {
		return seatCount;
	}


	/**
	 * @return the convertible
	 */
	public Boolean getConvertible() {
		return convertible;
	}


	/**
	 * @return the rating
	 */
	public Long getRating() {
		return rating;
	}


	/**
	 * @return the engineType
	 */
	public EngineType getEngineType() {
		return engineType;
	}


	



	/**
	 * @return the carModelId
	 */
	public Long getCarModelId() {
		return carModelId;
	}






	/**
	 * @return the available
	 */
	public Boolean getAvailable() {
		return available;
	}



	public static class CarDTOBuilder
    {
		 private Long id;
		 private String licencePlate;
		 private Long seatCount;		    
		 private Boolean convertible;		    
		 private Long rating;		    
		 private EngineType engineType;		    
		 private Long carModelId;
		 private Boolean available;


        public CarDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


		/**
		 * @param licencePlate the licencePlate to set
		 */
		public CarDTOBuilder setLicencePlate(String licencePlate) {
			this.licencePlate = licencePlate;
			return this;
		}


		/**
		 * @param seatCount the seatCount to set
		 */
		public CarDTOBuilder setSeatCount(Long seatCount) {
			this.seatCount = seatCount;
			return this;
		}


		/**
		 * @param convertible the convertible to set
		 */
		public CarDTOBuilder setConvertible(Boolean convertible) {
			this.convertible = convertible;
			return this;
		}


		/**
		 * @param rating the rating to set
		 */
		public CarDTOBuilder setRating(Long rating) {
			this.rating = rating;
			return this;
		}


		/**
		 * @param engineType the engineType to set
		 */
		public CarDTOBuilder setEngineType(EngineType engineType) {
			this.engineType = engineType;
			return this;
		}


		
		 /**
		 * @param carModelId the carModelId to set
		 * @return 
		 */
		public CarDTOBuilder setCarModelId(Long carModelId) {
			this.carModelId = carModelId;
			return this;
		}


		/**
		 * @param available the available to set
		 */
		public CarDTOBuilder setAvailable(Boolean available) {
			this.available = available;
			return this;
		}


		public CarDTO createCarDTO()
	        {
	            return new CarDTO(id, licencePlate, seatCount, convertible, rating, engineType, carModelId, available);
	        }


       
    }
}
