package com.broadviewsoft.btsdataservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stock")
public class Stock implements Cloneable {
	@Id
    @Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "currency")
	private CurrencyType currencyType;
	
	@Column(name = "symbol")
	private String symbol;
	
	@Column(name = "comp_name")
	private String companyName;

	public Stock() {
		currencyType = CurrencyType.USD;
	}

	public Stock(String symbol) {
		currencyType = CurrencyType.USD;
		this.symbol = symbol;
	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public boolean eqauls(Object obj) {
		if (!(obj instanceof Stock)) {
			return false;
		}

		Stock s = (Stock) obj;
		return symbol.equalsIgnoreCase(s.getSymbol());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.currencyType);
		sb.append(" ");
		sb.append(this.symbol);
		sb.append(" ");
		sb.append(this.companyName);

		return sb.toString();
	}

}
