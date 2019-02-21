package com.mytaxi.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarModelDTO
{
    @JsonIgnore
    private Long id;

    private String modelName;

    private String modelDescription;


    private CarModelDTO()
    {
    }


    private CarModelDTO(Long id, String modelName, String modelDescription)
    {
        this.id = id;
        this.modelName = modelName;
        this.modelDescription = modelDescription;
    }


    public static CarModelDTOBuilder newBuilder()
    {
        return new CarModelDTOBuilder();
    }


    @JsonProperty
    public Long getId()
    {
        return id;
    }


   

    /**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}


	/**
	 * @return the modelDescription
	 */
	public String getModelDescription() {
		return modelDescription;
	}




	public static class CarModelDTOBuilder
    {
		private Long id;
	    private String modelName;
	    private String modelDescription;


        public CarModelDTOBuilder setId(Long id)
        {
            this.id = id;
            return this;
        }


        /**
		 * @param modelName the modelName to set
		 */
		public CarModelDTOBuilder setModelName(String modelName) {
			this.modelName = modelName;
			return this;
		}





		/**
		 * @param modelDescription the modelDescription to set
		 */
		public CarModelDTOBuilder setModelDescription(String modelDescription) {
			this.modelDescription = modelDescription;
			return this;
		}





		public CarModelDTO createCarModelDTO()
        {
            return new CarModelDTO(id, modelName, modelDescription);
        }

    }
}
