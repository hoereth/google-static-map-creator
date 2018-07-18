package de.pentabyte.googlemaps;

/**
 * Simple Lat-Lon Coordinate.
 * 
 * @author michael hoereth
 */
public class StaticLatLonImpl implements StaticLatLon {
	private final double latitude;
	private final double longitude;

	public StaticLatLonImpl(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.pentabyte.googlemaps.Coordinate#getLatitude()
	 */
	@Override
	public double getLatitude() {
		return latitude;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.pentabyte.googlemaps.Coordinate#getLongitude()
	 */
	@Override
	public double getLongitude() {
		return longitude;
	}
}