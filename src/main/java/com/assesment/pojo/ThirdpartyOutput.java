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
	
}
