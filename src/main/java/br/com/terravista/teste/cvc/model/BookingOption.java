package br.com.terravista.teste.cvc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BookingOption implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = -6155275419046786588L;
	
	private Long id;
	private String cityName;
	private List<RoomBooked> rooms;
	
	public BookingOption() {
		super();
		rooms = new ArrayList<>();
	}

	
	public BookingOption(Long id, String cityName) {
		this();
		this.id = id;
		this.cityName = cityName;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public List<RoomBooked> getRooms() {
		return rooms;
	}
	
	public void setRooms(List<RoomBooked> rooms) {
		this.rooms = rooms;
	}
	
	
	public void addRoom(RoomBooked room) {
		this.getRooms().add(room);
	}
	
}
