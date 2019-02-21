package com.mytaxi.domainobject;

import com.mytaxi.domainvalue.EngineType;
import java.time.ZonedDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(
    name = "car",
    uniqueConstraints = @UniqueConstraint(name = "uc_licencePlate", columnNames = {"licencePlate"})
)
public class CarDO
{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "LicencePlate can not be null!")
    private String licencePlate;

    private Long seatCount;
    
    @Column(nullable = false)
    private Boolean convertible;
    
    private Long rating;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EngineType engineType;
    
    private Long carModelId;
    
    @Column(nullable = false)
    private Boolean deleted = false;
    
    @Column(nullable = false)
    private boolean available = false;

    //@Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    
    private CarDO()
    {
    }


    public CarDO(String licencePlate)
    {
        this.licencePlate = licencePlate;
        this.seatCount = seatCount;
        this.convertible = false;
        this.rating = rating;
        this.engineType = EngineType.DUAL;
        this.carModelId = carModelId;
        this.deleted = false;
        this.available = false;
    }
    


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * @return the licencePlate
	 */
	public String getLicencePlate() {
		return licencePlate;
	}


	/**
	 * @param licencePlate the licencePlate to set
	 */
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}


	/**
	 * @return the seatCount
	 */
	public Long getSeatCount() {
		return seatCount;
	}


	/**
	 * @param seatCount the seatCount to set
	 */
	public void setSeatCount(Long seatCount) {
		this.seatCount = seatCount;
	}


	/**
	 * @return the convertible
	 */
	public Boolean getConvertible() {
		return convertible;
	}


	/**
	 * @param convertible the convertible to set
	 */
	public void setConvertible(Boolean convertible) {
		this.convertible = convertible;
	}


	/**
	 * @return the rating
	 */
	public Long getRating() {
		return rating;
	}


	/**
	 * @param rating the rating to set
	 */
	public void setRating(Long rating) {
		this.rating = rating;
	}


	/**
	 * @return the engineType
	 */
	public EngineType getEngineType() {
		return engineType;
	}


	/**
	 * @param engineType the engineType to set
	 */
	public void setEngineType(EngineType engineType) {
		this.engineType = engineType;
	}




	/**
	 * @return the dateCreated
	 */
	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}


	/**
	 * @param dateCreated the dateCreated to set
	 */
	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}


	/**
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}


	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}


	/**
	 * @return the carModelId
	 */
	public Long getCarModelId() {
		return carModelId;
	}


	/**
	 * @param carModelId the carModelId to set
	 */
	public void setCarModelId(Long carModelId) {
		this.carModelId = carModelId;
	}


	/**
	 * @return the available
	 */
	public Boolean getAvailable() {
		return available;
	}


	/**
	 * @param available the available to set
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}


    
}
