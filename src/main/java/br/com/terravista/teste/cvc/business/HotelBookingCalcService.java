package br.com.terravista.teste.cvc.business;

import java.util.List;

import br.com.terravista.teste.cvc.model.BookingData;
import br.com.terravista.teste.cvc.model.BookingOption;

public interface HotelBookingCalcService {
	
	List<BookingOption> getBookingOptionsByIdCity(BookingData bookingData);
	List<BookingOption> getBookingOptionsByIdHotel(BookingData bookingData);
}
