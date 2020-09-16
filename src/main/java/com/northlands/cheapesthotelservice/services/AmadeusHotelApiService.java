package com.northlands.cheapesthotelservice.services;

import com.amadeus.resources.HotelOffer;

public interface AmadeusHotelApiService {
	public HotelOffer getBestHotelOffer(double latitude, double longitude, String checkInDate, String checkOutDate);
}
