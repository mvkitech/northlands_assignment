package com.northlands.cheapesthotelservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amadeus.resources.HotelOffer;
import com.google.gson.Gson;
import com.northlands.cheapesthotelservice.conversions.HotelOfferConverter;
import com.northlands.cheapesthotelservice.models.Hotel;
import com.northlands.cheapesthotelservice.services.AmadeusHotelApiService;

@RestController
public class HotelRestController {
	
	@Autowired
	private AmadeusHotelApiService hotelService;
	
	/**
	 * Used to extract the best hotel price offering for the specified latitude and longitude
	 * values for a target city airport as well as check-in and check-out date parameter values.
	 * @param cityCode specifies the target city airport code.
	 * @param checkInDate specifies the target check-in date.
	 * @param checkOutDate specifies the target check-in date.
	 * @return String representing best hotel offer in JSON format.
	 */
	@GetMapping("/hotels/latitude/{latitude}/longitude/{longitude}/checkIn/{checkInDate}/checkOut/{checkOutDate}")
	public String getBestHotelOffer(@PathVariable double latitude,
									@PathVariable double longitude,
									@PathVariable String checkInDate,
									@PathVariable String checkOutDate) {

		// Extract the best Hotel Offering from the HotelService component.
		HotelOffer bestOffer = hotelService.getBestHotelOffer(latitude, longitude, checkInDate, checkOutDate);

		// Please note that presently the code only looks for the lowest price
		// offering. The distance from the target airport to the possible hotels 
		// in relation to how much taxi cab fair could been might have also been 
		// factored into this equation. But that seemed overkill especially since 
		// taxi fairs are unknown and would need to be applied. 
		
		// Convert the HotelOffer service class into a local Hotel domain entity
		// prior to returning the Hotel instance as a JSON output stream.
		Hotel hotel = HotelOfferConverter.convertHotelOfferToHotel(bestOffer);
		return new Gson().toJson(hotel);
	}
}
