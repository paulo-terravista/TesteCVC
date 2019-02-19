package br.com.terravista.teste.cvc.model.hotel;

import java.io.Serializable;
import java.util.List;

public class Hotel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4523781874175433072L;
	
	private Long id;
	private String name;
	private Long cityCode;
	private String cityName;
	private List<Room> rooms;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getCityCode() {
		return cityCode;
	}
	
	public void setCityCode(Long cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}
	
}
