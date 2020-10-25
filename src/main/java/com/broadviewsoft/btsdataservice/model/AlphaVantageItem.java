package com.broadviewsoft.btsdataservice.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "Meta Data", "Time Series" })
public class AlphaVantageItem {

	@JsonProperty("Meta Data")
	private MetaData metaData;
	@JsonProperty("Time Series")
	private List<TimeSeries> timeSeries = null;

	@JsonProperty("Meta Data")
	public MetaData getMetaData() {
		return metaData;
	}

	@JsonProperty("Meta Data")
	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	@JsonProperty("Time Series")
	public List<TimeSeries> getTimeSeries() {
		return timeSeries;
	}

	@JsonProperty("Time Series")
	public void setTimeSeries1min(List<TimeSeries> timeSeries) {
		this.timeSeries = timeSeries;
	}

}