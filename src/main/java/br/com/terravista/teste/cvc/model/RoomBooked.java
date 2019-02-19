package br.com.terravista.teste.cvc.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RoomBooked implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2023039387181268400L;
	

	private Long roomID;
	private String categoryName;
	private BigDecimal totalPrice;
	private PriceDetail priceDetail;
	
	public RoomBooked() {
		super();
		setPriceDetail(new PriceDetail());
	}
	
	
	public RoomBooked(Long roomID, String categoryName) {
		this();
		this.roomID = roomID;
		this.categoryName = categoryName;
	}

	
	public Long getRoomID() {
		return roomID;
	}
	
	public void setRoomID(Long roomID) {
		this.roomID = roomID;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public PriceDetail getPriceDetail() {
		return priceDetail;
	}
	
	public void setPriceDetail(PriceDetail priceDetail) {
		this.priceDetail = priceDetail;
	}

}
