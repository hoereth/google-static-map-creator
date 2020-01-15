package de.pentabyte.googlemaps;

import org.junit.Test;

public class StaticMapTest {
	@Test
	public void test_geocode_below_maximum() {
		StaticMap map = new StaticMap(400, 200, null);
		add15Markers(map);
		map.toString();
	}
	
	@Test(expected = Exception.class)
	public void test_geocode_over_maximum() {
		StaticMap map = new StaticMap(400, 200, null);
		add15Markers(map);
		map.addMarker(new StaticMarker("Frankfurt"));
	}

	private void add15Markers(StaticMap map) {
		map.addMarker(new StaticMarker("London"));
		map.addMarker(new StaticMarker("Paris"));
		map.addMarker(new StaticMarker("Brüssel"));
		map.addMarker(new StaticMarker("Berlin"));
		map.addMarker(new StaticMarker("Madrid"));
		map.addMarker(new StaticMarker("München"));
		map.addMarker(new StaticMarker("Rom"));
		map.addMarker(new StaticMarker("Prag"));
		map.addMarker(new StaticMarker("Wien"));
		map.addMarker(new StaticMarker("Hamburg"));
		map.addMarker(new StaticMarker("New York"));
		map.addMarker(new StaticMarker("Amsterdam"));
		map.addMarker(new StaticMarker("Dublin"));
		map.addMarker(new StaticMarker("Montpellier"));
		map.addMarker(new StaticMarker("Bern"));
	}
}
