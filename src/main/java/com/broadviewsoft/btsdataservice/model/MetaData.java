package com.broadviewsoft.btsdataservice.model;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "1. Information", "2. Symbol", "3. Last Refreshed", "4. Interval", "5. Output Size",
		"6. Time Zone" })
public class MetaData {

	@JsonProperty("1. Information")
	private String _1Information;
	@JsonProperty("2. Symbol")
	private String _2Symbol;
	@JsonProperty("3. Last Refreshed")
	private String _3LastRefreshed;
	@JsonProperty("4. Interval")
	private String _4Interval;
	@JsonProperty("5. Output Size")
	private String _5OutputSize;
	@JsonProperty("6. Time Zone")
	private String _6TimeZone;

	@JsonProperty("1. Information")
	public String get1Information() {
		return _1Information;
	}

	@JsonProperty("1. Information")
	public void set1Information(String _1Information) {
		this._1Information = _1Information;
	}

	@JsonProperty("2. Symbol")
	public String get2Symbol() {
		return _2Symbol;
	}

	@JsonProperty("2. Symbol")
	public void set2Symbol(String _2Symbol) {
		this._2Symbol = _2Symbol;
	}

	@JsonProperty("3. Last Refreshed")
	public String get3LastRefreshed() {
		return _3LastRefreshed;
	}

	@JsonProperty("3. Last Refreshed")
	public void set3LastRefreshed(String _3LastRefreshed) {
		this._3LastRefreshed = _3LastRefreshed;
	}

	@JsonProperty("4. Interval")
	public String get4Interval() {
		return _4Interval;
	}

	@JsonProperty("4. Interval")
	public void set4Interval(String _4Interval) {
		this._4Interval = _4Interval;
	}

	@JsonProperty("5. Output Size")
	public String get5OutputSize() {
		return _5OutputSize;
	}

	@JsonProperty("5. Output Size")
	public void set5OutputSize(String _5OutputSize) {
		this._5OutputSize = _5OutputSize;
	}

	@JsonProperty("6. Time Zone")
	public String get6TimeZone() {
		return _6TimeZone;
	}

	@JsonProperty("6. Time Zone")
	public void set6TimeZone(String _6TimeZone) {
		this._6TimeZone = _6TimeZone;
	}


}