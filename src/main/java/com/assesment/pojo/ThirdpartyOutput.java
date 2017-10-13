package com.assesment.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ThirdpartyOutput")
public class ThirdpartyOutput {

	@ApiModelProperty(value = "thirdPartyData")
	@JsonProperty("thirdPartyData")
	private String thrirdPartyData;
	
	@ApiModelProperty(value = "someFields")
	@JsonProperty("someFields")
	private String someFields;

	public String getThrirdPartyData() {
		return thrirdPartyData;
	}

	public void setThrirdPartyData(String thrirdPartyData) {
		this.thrirdPartyData = thrirdPartyData;
	}

	public String getSomeFields() {
		return someFields;
	}

	public void setSomeFields(String someFields) {
		this.someFields = someFields;
	}

	@Override
	public String toString() {
		return "ThirdpartyOutput [thrirdPartyData=" + thrirdPartyData + ", someFields=" + someFields + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((someFields == null) ? 0 : someFields.hashCode());
		result = prime * result + ((thrirdPartyData == null) ? 0 : thrirdPartyData.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThirdpartyOutput other = (ThirdpartyOutput) obj;
		if (someFields == null) {
			if (other.someFields != null)
				return false;
		} else if (!someFields.equals(other.someFields))
			return false;
		if (thrirdPartyData == null) {
			if (other.thrirdPartyData != null)
				return false;
		} else if (!thrirdPartyData.equals(other.thrirdPartyData))
			return false;
		return true;
	}
	
}
