package br.com.terravista.teste.cvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PriceDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5686902290206606153L;
	
	private BigDecimal pricePerDayAdult;
	private BigDecimal pricePerDayChildren;
	
	public BigDecimal getPricePerDayAdult() {
		return pricePerDayAdult;
	}
	
	public void setPricePerDayAdult(BigDecimal pricePerDayAdult) {
		this.pricePerDayAdult = pricePerDayAdult;
	}
	
	public BigDecimal getPricePerDayChildren() {
		return pricePerDayChildren;
	}
	
	public void setPricePerDayChildren(BigDecimal pricePerDayChildren) {
		this.pricePerDayChildren = pricePerDayChildren;
	}
	
}
