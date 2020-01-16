package de.pentabyte.googlemaps;

import java.io.Serializable;

/**
 * A geographic location as used by {@link StaticMap} or {@link StaticMarker}.
 * 
 * @author michael hoereth
 */
public class Location implements Serializable {
	private static final long serialVersionUID = -2890131634409376834L;
	/**
	 * what will be sent to Google
	 */
	private final String query;
	/**
	 * just a note to keep track of those locations which will trigger geocoding at
	 * Google
	 */
	private boolean geocodingRequired;

	/**
	 * @param query anything which can be geocoded to a coordinate by Google
	 */
	public Location(String query) {
		this.query = query;
		this.geocodingRequired = true;
	}

	/**
	 * Will create a location which will not require geocoding.
	 */
	public Location(double latitude, double longitude) {
		this((float) latitude + "," + (float) longitude);
		this.geocodingRequired = false;
	}

	protected boolean isGeocodingRequired() {
		return geocodingRequired;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return query;
	}
}
