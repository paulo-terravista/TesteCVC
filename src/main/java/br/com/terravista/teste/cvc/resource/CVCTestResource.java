package br.com.terravista.teste.cvc.resource;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.terravista.teste.cvc.business.HotelBookingCalcService;
import br.com.terravista.teste.cvc.model.BookingData;
import br.com.terravista.teste.cvc.model.BookingOption;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/booking")
@Api(tags = {"class"})
@SwaggerDefinition(tags = {
    @Tag(name = "class", description = "Cálculo de reservas (Teste CVC)")
})
public class CVCTestResource {

	
	@Autowired private HotelBookingCalcService hotelBookingCalcService;
	
	@ApiOperation(value="Lista as opções de reserva em uma cidade")
	@RequestMapping(value = "/option/city", method = RequestMethod.GET)
	public List<BookingOption> getBookingOptionsByidCity(
			@ApiParam(value = "id da Cidade", required = true) @RequestParam(required=true, name="id") long cityCode,
			@ApiParam(value = "data de checkin no formato yyyy-mm-dd", required = true) @RequestParam(required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date checkin,
			@ApiParam(value = "data de checkout no formato yyyy-mm-dd", required = true) @RequestParam(required=true) @DateTimeFormat(pattern="yyyy-MM-dd") Date checkout,
			@ApiParam(value = "quantidade de adultos", required = true) @RequestParam(required=true) int numberAdults,
			@ApiParam(value = "quantidade de crianças", required = true) @RequestParam(required=true) int numberChildren) {
		validateDates(checkin, checkout);
		BookingData bookingData = new BookingData(cityCode, checkin, checkout, numberAdults, numberChildren);
		return hotelBookingCalcService.getBookingOptionsByIdCity(bookingData);
	}
	
	@ApiOperation(value="Lista as opções de reserva em um hotel")
	@RequestMapping(value = "/option/hotel", method = RequestMethod.GET)
	public List<BookingOption> getBookingOptionsByIdHotel(
			@ApiParam(value = "id da Cidade", required = true) @RequestParam(required=true, name="id") long idHotel,
			@ApiParam(value = "data de checkin no formato yyyy-mm-dd", required = false) @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date checkin,
			@ApiParam(value = "data de checkout no formato yyyy-mm-dd", required = false) @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date checkout,
			@ApiParam(value = "quantidade de adultos", required = false) @RequestParam(required=false, defaultValue="1") int numberAdults,
			@ApiParam(value = "quantidade de crianças", required = false) @RequestParam(required=false, defaultValue="0") int numberChildren) {
		Date pCheckin = checkin == null? getActualDate(): checkin;
		Date pCheckout = checkout == null? getActualDate(): checkout;
		validateDates(pCheckin, pCheckout);
		BookingData bookingData = new BookingData(pCheckin, pCheckout, numberAdults, numberChildren);
		bookingData.setIdHotel(idHotel);
		return hotelBookingCalcService.getBookingOptionsByIdHotel(bookingData);
	}

	private void validateDates(Date checkin, Date checkout) {
		if (checkout.before(checkin)) {
			throw new ValidationException("Data de checking maior que a data de checkout.");
		}
		Date now = getActualDate();
		
		if (now.after(checkin)) {
			throw new ValidationException("Data de checking menor que a data atual.");
		}
	}

	private Date getActualDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	
	
	
}
