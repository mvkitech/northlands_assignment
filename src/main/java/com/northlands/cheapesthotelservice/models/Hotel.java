package com.northlands.cheapesthotelservice.models;

import java.util.HashSet;
import java.util.Set;

//@Entity
public class Hotel {
	
    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String hotelId;
	private String chainCode;
	private String name;
	private Integer rating;
	private String cityCode;
	private Double latitude;
	private Double longitude;
	private Double distance;
	private String distanceUnit;
	
    //@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
    //@OneToOne(cascade = CascadeType.ALL)
	private Contact contact;
	
    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "hotel")
    private Set<Price> prices = new HashSet<>();
    
    public Hotel() {
    	// Empty on purpose
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHotelId() {
		return hotelId;
	}

	public void setHotelId(String hotelId) {
		this.hotelId = hotelId;
	}

	public String getChainCode() {
		return chainCode;
	}

	public void setChainCode(String chainCode) {
		this.chainCode = chainCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public Set<Price> getPrices() {
		return prices;
	}
//
//	public void setPrices(Set<Price> prices) {
//		this.prices = prices;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hotelId == null) ? 0 : hotelId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hotel other = (Hotel) obj;
		if (hotelId == null) {
			if (other.hotelId != null)
				return false;
		} else if (!hotelId.equals(other.hotelId))
			return false;
		return true;
	}
}
