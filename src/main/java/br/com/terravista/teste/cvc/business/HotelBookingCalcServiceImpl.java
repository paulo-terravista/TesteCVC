package br.com.terravista.teste.cvc.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.terravista.teste.cvc.model.BookingData;
import br.com.terravista.teste.cvc.model.BookingOption;
import br.com.terravista.teste.cvc.model.RoomBooked;
import br.com.terravista.teste.cvc.model.hotel.Hotel;
import br.com.terravista.teste.cvc.model.hotel.Room;

@Service
public class HotelBookingCalcServiceImpl implements HotelBookingCalcService {
	
	private static final int CASAS_DECIMAIS = 2;
    private static final BigDecimal COMISSAO = new BigDecimal(.70);
	
	@Autowired private RestTemplate restTemplate;
	
	public List<BookingOption> getBookingOptionsByIdCity(BookingData bookingData) {

		List<Hotel> hotels = getHotelsByIdCity(bookingData.getCityCode());
		return processOptions(bookingData, hotels);
	}

	@Override
	public List<BookingOption> getBookingOptionsByIdHotel(BookingData bookingData) {
		List<Hotel> hotels = getHotelByIdHotel(bookingData.getIdHotel());
		return processOptions(bookingData, hotels);
	}
	
	private List<BookingOption> processOptions(BookingData bookingData, List<Hotel> hotels) {
		List<BookingOption> result = new ArrayList<>();
		hotels.parallelStream().forEach(hotel -> {
			BookingOption option = new BookingOption(hotel.getId(), hotel.getCityName());
			calculePrice(bookingData, hotel.getRooms(), option);
			result.add(option);
		});
		return result;
	}


	
	private void calculePrice(BookingData bookingData, List<Room> rooms, BookingOption option) {
		rooms.parallelStream().forEach(room -> {
			RoomBooked roomBooked = new RoomBooked(room.getRoomID(), room.getCategoryName());
			BigDecimal day = getDaysReserve(bookingData.getCheckin(),
						                    bookingData.getCheckout());
			BigDecimal adultValue = calculeDiarias(room.getPrice().getAdult(), day);
			BigDecimal childrenValue = calculeDiarias(room.getPrice().getChild(), day);
			roomBooked.getPriceDetail().setPricePerDayAdult(adultValue);
			roomBooked.getPriceDetail().setPricePerDayChildren(childrenValue);
			roomBooked.setTotalPrice(adultValue.add(childrenValue));
			option.addRoom(roomBooked);
		});
	}

	private BigDecimal calculeDiarias(BigDecimal value, BigDecimal day) {
		return value.multiply(day).divide(COMISSAO, CASAS_DECIMAIS, RoundingMode.FLOOR);
	}

	private BigDecimal getDaysReserve(Date chkIn, Date chkOut) {
		LocalDate checkin = chkIn.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate checkout = chkOut.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Period period = Period.between(checkin, checkout);
		return new BigDecimal(Math.max(period.getDays(), 1));
	}
	
	
	private List<Hotel> getHotelsByIdCity(Long id) {
		String url = "https://cvcbackendhotel.herokuapp.com/hotels/avail/{ID_da_Cidade}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("ID_da_Cidade", String.valueOf(id));
		
		UriComponents uriComponent = UriComponentsBuilder.fromUriString(url).buildAndExpand(params);
		URI uri = uriComponent.toUri();
		ResponseEntity<List<Hotel>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Hotel>>() {});
		
		return responseEntity.getBody();
	}
	
	
	private List<Hotel> getHotelByIdHotel(Long id) {
		String url = "https://cvcbackendhotel.herokuapp.com/hotels/{ID_Do_Hotel}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("ID_Do_Hotel", String.valueOf(id));
		
		UriComponents uriComponent = UriComponentsBuilder.fromUriString(url).buildAndExpand(params);
		URI uri = uriComponent.toUri();
		ResponseEntity<List<Hotel>> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, null, 
				new ParameterizedTypeReference<List<Hotel>>() {});
		
		return responseEntity.getBody();
	}


	

}
