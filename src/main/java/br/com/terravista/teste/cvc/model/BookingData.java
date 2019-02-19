package br.com.terravista.teste.cvc.model;

import java.io.Serializable;
import java.util.Date;

public class BookingData implements Serializable {

	private static final long serialVersionUID = 5548809911148555846L;
	
	private long idHotel;
	private long cityCode;
	private Date checkin;
	private Date checkout;
	private int numberAdults;
	private int numberChildren;
	
	
	public BookingData(long cityCode, Date checkin, Date checkout, int numberAdults, int numberChildren) {
		this(checkin, checkout, numberAdults, numberChildren);
		this.cityCode = cityCode;
	}
	
	
	public BookingData(Date checkin, Date checkout, int numberAdults, int numberChildren) {
		super();
		this.checkin = checkin;
		this.checkout = checkout;
		this.numberAdults = numberAdults;
		this.numberChildren = numberChildren;
	}

	public long getCityCode() {
		return cityCode;
	}
	
	public void setCityCode(long cityCode) {
		this.cityCode = cityCode;
	}
	
	public Date getCheckin() {
		return checkin;
	}
	
	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}
	
	public Date getCheckout() {
		return checkout;
	}
	
	public void setCheckout(Date checkout) {
		this.checkout = checkout;
	}
	
	public int getNumberAdults() {
		return numberAdults;
	}
	
	public void setNumberAdults(int numberAdults) {
		this.numberAdults = numberAdults;
	}
	
	public int getNumberChildren() {
		return numberChildren;
	}
	
	public void setNumberChildren(int numberChildren) {
		this.numberChildren = numberChildren;
	}


	public long getIdHotel() {
		return idHotel;
	}


	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}


	@Override
	public String toString() {
		return "BookingData [idHotel=" + idHotel + ", cityCode=" + cityCode + ", checkin=" + checkin + ", checkout="
				+ checkout + ", numberAdults=" + numberAdults + ", numberChildren=" + numberChildren + "]";
	}
}
