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
@Table(
    name = "carModel",
    uniqueConstraints = @UniqueConstraint(name = "uc_modelName", columnNames = {"modelName"})
)
public class CarModelDO
{

    @Id
    @GeneratedValue
    private Long carModelId;

    //@Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    @NotNull(message = "Model Name can not be null!")
    private String modelName;

    private String modelDescription;

    private CarModelDO()
    {
    }


    public CarModelDO(String modelName)
    {
        this.modelName = modelName;
        this.modelDescription = modelDescription;
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
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}


	/**
	 * @param modelName the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}


	/**
	 * @return the modelDescription
	 */
	public String getModelDescription() {
		return modelDescription;
	}


	/**
	 * @param modelDescription the modelDescription to set
	 */
	public void setModelDescription(String modelDescription) {
		this.modelDescription = modelDescription;
	}



}
