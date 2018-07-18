/**
 * 
 */
package de.pentabyte.googlemaps;

import java.util.Collection;

/**
 * A geographic rectangle
 */
public class InternalBoundingBox {
	private double latMin = Double.POSITIVE_INFINITY;
	private double latMax = Double.NEGATIVE_INFINITY;
	private double lonMin = Double.POSITIVE_INFINITY;
	private double lonMax = Double.NEGATIVE_INFINITY;
	private double latCenter;
	private double lonCenter;
	private double latRange;
	private double lonRange;

	protected InternalBoundingBox(Collection<? extends StaticLatLon> coords) {
		for (StaticLatLon coord : coords) {
			if (coord.getLatitude() > latMax)
				latMax = coord.getLatitude();
			if (coord.getLatitude() < latMin)
				latMin = coord.getLatitude();
			if (coord.getLongitude() > lonMax)
				lonMax = coord.getLongitude();
			if (coord.getLongitude() < lonMin)
				lonMin = coord.getLongitude();
		}
		latCenter = (latMin + latMax) / 2;
		lonCenter = (lonMin + lonMax) / 2;
		latRange = latMax - latMin;
		lonRange = lonMax - lonMin;
	}

	/**
	 * Liegt die Koordinate innherhalb der Boundigbox?
	 */
	protected boolean contains(StaticLatLon coord) {
		double lat = coord.getLatitude();
		double lon = coord.getLongitude();
		return lat >= latMin && lat <= latMax && lon >= lonMin && lon <= lonMax;
	}

	/**
	 * Liegt die Koordinate innerhalb des Quadrats, das die BoundingBox
	 * umschließt?
	 */
	protected boolean squareContains(StaticLatLon coord) {
		return squareContains(coord, 1);
	}

	/**
	 * Liegt die Koordinate innerhalb des Quadrats, das die BoundingBox
	 * umschließt? (inklusive Abweichung, z.B: 1.1)
	 */
	protected boolean squareContains(StaticLatLon coord, double deviation) {
		double latEdge = latRange * deviation;
		double lonEdge = lonRange * deviation;
		double latMin = latCenter - latEdge / 2;
		double latMax = latCenter + latEdge / 2;
		double lonMin = lonCenter - lonEdge / 2;
		double lonMax = lonCenter + lonEdge / 2;

		double lat = coord.getLatitude();
		double lon = coord.getLongitude();
		return lat >= latMin && lat <= latMax && lon >= lonMin && lon <= lonMax;
	}

	protected double getLatitude() {
		return latCenter;
	}

	protected double getLongitude() {
		return lonCenter;
	}

	protected double getHeightMeters() {
		StaticLatLon upperLeft = new StaticLatLonImpl(latMax, lonMin);
		StaticLatLon bottomLeft = new StaticLatLonImpl(latMin, lonMin);
		return StaticPath.distanceAuto(upperLeft, bottomLeft);
	}

	protected StaticLatLon getUpperLeft() {
		return new StaticLatLonImpl(latMax, lonMin);
	}

	protected StaticLatLon getUpperRight() {
		return new StaticLatLonImpl(latMax, lonMax);
	}

	protected StaticLatLon getLowerLeft() {
		return new StaticLatLonImpl(latMin, lonMin);
	}

	protected StaticLatLon getLowerRight() {
		return new StaticLatLonImpl(latMin, lonMax);
	}

	protected StaticLatLon getCenter() {
		return new StaticLatLonImpl(latCenter, lonCenter);
	}

	protected double getLatRange() {
		return latRange;
	}

	protected double getLonRange() {
		return lonRange;
	}
}
