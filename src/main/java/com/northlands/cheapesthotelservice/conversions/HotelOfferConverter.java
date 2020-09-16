package com.northlands.cheapesthotelservice.conversions;

import com.amadeus.resources.HotelOffer;
import com.amadeus.resources.HotelOffer.Offer;
import com.northlands.cheapesthotelservice.models.Address;
import com.northlands.cheapesthotelservice.models.Contact;
import com.northlands.cheapesthotelservice.models.Hotel;
import com.northlands.cheapesthotelservice.models.Price;

public class HotelOfferConverter {
	
	/**
	 * Helper method used to construct the Hotel's object instance.
	 * @param hotelOffer specifies the Hotel Offering instance to convert.
	 * @return Hotel representing the new Hotel object instance.
	 */
	public static final Hotel convertHotelOfferToHotel(HotelOffer hotelOffer) {
		
		// Create a new Hotel instance from the Hotel Offering instance
		Hotel hotel = new Hotel();
		hotel.setHotelId(hotelOffer.getHotel().getHotelId());
		hotel.setChainCode(hotelOffer.getHotel().getChainCode());
		hotel.setName(hotelOffer.getHotel().getName());
		hotel.setRating(hotelOffer.getHotel().getRating());
		hotel.setCityCode(hotelOffer.getHotel().getCityCode());
		hotel.setLatitude(new Double(hotelOffer.getHotel().getLatitude()));
		hotel.setLongitude(new Double(hotelOffer.getHotel().getLongitude()));
		hotel.setDistance(new Double(hotelOffer.getHotel().getHotelDistance().getDistance()));
		hotel.setDistanceUnit(hotelOffer.getHotel().getHotelDistance().getDistanceUnit());
		
		// Convert the Hotel's Address object instance
		Address address = convertHotelOfferAddress(hotelOffer);
		hotel.setAddress(address);
		
		// Convert the Hotel's Contact object instance
		Contact contact = convertHotelOfferContact(hotelOffer);
		hotel.setContact(contact);
		
		// Convert the Hotel's offer object instance(s)
		Offer[] offers = hotelOffer.getOffers();
		for(int index=0; index<offers.length; index++) {
			Price price = convertHotelPriceOffer(offers[index]);
			hotel.getPrices().add(price);
		}
		
		return hotel;
	}

	/**
	 * Helper method used to construct the Hotel's Address instance.
	 * @param hotelOffer specifies the Hotel Offering instance to convert.
	 * @return Address representing the new Hotel Address instance.
	 */
	private static final Address convertHotelOfferAddress(HotelOffer hotelOffer) {
		Address address = new Address();
		String[] addressLines = hotelOffer.getHotel().getAddress().getLines();
		address.setStreet(addressLines[0]);
		address.setCityName(hotelOffer.getHotel().getAddress().getCityName());
		address.setStateCode(hotelOffer.getHotel().getAddress().getStateCode());
		address.setPostalCode(hotelOffer.getHotel().getAddress().getPostalCode());
		address.setCountryCode(hotelOffer.getHotel().getAddress().getCountryCode());
		return address;
	}
	
	/**
	 * Helper method used to construct the Hotel's Contact instance.
	 * @param hotelOffer specifies the Hotel Offering instance to convert.
	 * @return Contact representing the new Hotel Contact instance.
	 */
	private static final Contact convertHotelOfferContact(HotelOffer hotelOffer) {
		Contact contact = new Contact();
		contact.setPhone(hotelOffer.getHotel().getContact().getPhone());
		contact.setFax(hotelOffer.getHotel().getContact().getFax());
		return contact;
	}
	
	/**
	 * Helper method used to construct the Hotel's Price Offering instances.
	 * @param offer specifies the Hotel's Offering instance to convert.
	 * @return Price representing the Hotel's Price Offering instance.
	 */
	private static final Price convertHotelPriceOffer(Offer offer) {
		Price price = new Price();
		price.setOfferId(offer.getId());
		price.setCurrencyType(offer.getPrice().getCurrency());
		price.setBasePrice(new Float(offer.getPrice().getBase()));
		price.setTotalPrice(new Float(offer.getPrice().getTotal()));
		return price;
	}
}
