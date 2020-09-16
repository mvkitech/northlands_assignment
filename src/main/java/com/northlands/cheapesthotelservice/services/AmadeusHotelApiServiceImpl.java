package com.northlands.cheapesthotelservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.exceptions.ResponseException;
import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.Offer;

@Service
public class AmadeusHotelApiServiceImpl implements AmadeusHotelApiService {

	private Logger LOGGER = LoggerFactory.getLogger(AmadeusHotelApiServiceImpl.class);
	
	private static final int SEARCH_RADIUS = 10;
	private static final String RADIUS_UNIT = "KM";
	
	private Amadeus amadeus;
	
	/**
	 * Default class constructor exists to ensure that the Amadeus service is launched.
	 */
	public AmadeusHotelApiServiceImpl() {
		amadeus = Amadeus.builder("FkXpz6C5dCg0jFzGgudPUWYFXbdVMmte", "XSSYFmqG37ZGIgLM").build();
	}
	
	/**
	 * Used to query the Amadeus service for the best hotel price offering based on the supplied
	 * latitude/longitude for the target airport as well as check-in and check-out dates.
	 */
	public HotelOffer getBestHotelOffer(double latitude, double longitude, String checkInDate, String checkOutDate) {
		
		try {
			
	    	LOGGER.info("inside of getBestHotelOffer["+latitude+"]["+longitude+"]["+checkInDate+"]["+checkOutDate+"]");
			HotelOffer[] offers = amadeus.shopping.hotelOffers.get(Params
					.with("latitude", latitude)
					.and("longitude", longitude)
					.and("radius", SEARCH_RADIUS)
					.and("radiusUnit", RADIUS_UNIT)
					.and("checkInDate", checkInDate)
					.and("checkOutDate", checkOutDate));
			
			// Did not want to put this first "if" condition here, but was not getting
			// the expected behavior when I passed in invalid search parameters (such 
			// as using a bad cityCode value) to the Amedeus service.
			if (offers.length > 0) {
				
		    	LOGGER.info("Status code: " + offers[0].getResponse().getStatusCode());
			    if (offers[0].getResponse().getStatusCode() == 200) {
			    	return findBestHotelOffer(offers);
			    }
			}
			
		} catch(ResponseException re) {
			LOGGER.error("ResponseException: "+re.getMessage());
			re.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Helper method used to iterate through each Hotel Offering which was
	 * returned by the Amadeus service in the attempt to find the lowest price.
	 * @param hotelOffers specifies the collection of hotel offering candidates.
	 * @return HotelOffer representing the best Hotel Offering.
	 */
	private HotelOffer findBestHotelOffer(HotelOffer[] hotelOffers) {
		
    	LOGGER.info("inside of findBestHotelOffer() totalOffers to parse is: "+hotelOffers.length);
    	
    	HotelOffer bestOffer = null;
    	Float bestPrice = null;
    	
    	// Please note that I did not really want to use a "for" loop here.
    	// I used it because was experiencing issues trying to iterate the 
    	// hotelOffers array returned by the Amadeus service with the following snippet:
    	//   hotelOffers.forEach(offer -> LOGGER.info(offer.getHotel().getName()));
    	for(int index=0; index<hotelOffers.length; index++) {
			Offer[] offerPrices = hotelOffers[index].getOffers();
			if(bestOffer != null) {
 				Float candidate = new Float(offerPrices[0].getPrice().getTotal());
 				int result = candidate.compareTo(bestPrice);
				if (result < 0) {
					bestOffer = hotelOffers[index];
					bestPrice = candidate;
				}
			} else {
				bestOffer = hotelOffers[index];
				bestPrice = new Float(offerPrices[0].getPrice().getTotal());
			}
    	}
		
    	System.out.println("return: ["+bestOffer.getHotel().getHotelId()+"]["+bestOffer.getHotel().getName()+"]["+bestPrice+"]");
		return bestOffer;
	}
}
