package de.pentabyte.googlemaps;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.ClassRule;
import org.junit.Test;

import de.pentabyte.googlemaps.StaticMap.Maptype;

/**
 * Will run the tests and download the map images from Google. But only if you
 * provide the system property {@link #GOOGLEAPI_PROPERTYNAME}.
 * 
 * @author michael hoereth
 */
public class ExampleCreator {
	private static String GOOGLEAPI_PROPERTYNAME = "GOOGLEAPI";
	private String googleApiKey = System.getProperty(GOOGLEAPI_PROPERTYNAME);

	@ClassRule
	public static SystemPropertyPreCondition check = new SystemPropertyPreCondition(GOOGLEAPI_PROPERTYNAME);

	@Test
	public void createLocation() throws ClientProtocolException, IOException {
		StaticMap map = new StaticMap(400, 200, googleApiKey);
		map.setLocation(new Location("Eiffeltower"), 16);
		map.setMaptype(Maptype.hybrid);

		create(map, "location.png");
	}

	@Test
	public void createMarkers() throws ClientProtocolException, IOException {
		StaticMap map = new StaticMap(400, 200, googleApiKey);
		map.setMaptype(Maptype.hybrid);

		map.addMarker(new StaticMarker("Eiffeltower"));

		StaticMarker notreDame = new StaticMarker(48.853000, 2.349983);
		notreDame.setLabel('N');
		notreDame.setColor(Color.orange);
		map.addMarker(notreDame);

		create(map, "markers.png");
	}

	@Test
	public void createCustomMarker() throws ClientProtocolException, IOException {
		StaticMap map = new StaticMap(400, 200, googleApiKey);
		map.setMaptype(Maptype.hybrid);

		StaticMarker m1 = new StaticMarker(50.844943, 6.856998);
		m1.setCustomIconUrl("http://cableparks.info/poi.png");
		m1.setScale(2);
		map.addMarker(m1);

		StaticMarker m2 = new StaticMarker(50.844782, 6.856730);
		m2.setCustomIconUrl("http://cableparks.info/poi_2.png");
		m2.setScale(2);
		map.addMarker(m2);

		create(map, "customMarkers.png");
	}

	@Test
	public void createEncodedPolyline() throws ClientProtocolException, IOException {
		List<LatLon> coords = new ArrayList<>();
		coords.add(new LatLonImpl(40.800568, -73.958185));
		coords.add(new LatLonImpl(40.796855, -73.949294));
		coords.add(new LatLonImpl(40.764311, -73.973011));
		coords.add(new LatLonImpl(40.768060, -73.981840));
		coords.add(new LatLonImpl(40.800568, -73.958185));
		StaticPath centralPark = new StaticPath(coords);
		centralPark.setColor(Color.red);
		centralPark.setFillColor(Color.black);

		StaticMap map = new StaticMap(400, 200, googleApiKey);
		map.addPath(centralPark);

		create(map, "encodedPolyline.png");
	}

	private void create(StaticMap map, String filename) throws ClientProtocolException, IOException {
		File myFile = new File("src/test/resources/" + filename);
		String url = map.toString();

		File myUrl = new File("src/test/resources/" + filename + ".txt");
		FileWriter writer = new FileWriter(myUrl);
		writer.write(url.replace(googleApiKey, "*****"));
		writer.close();

		CloseableHttpClient client = HttpClients.createDefault();
		try (CloseableHttpResponse response = client.execute(new HttpGet(url))) {
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() != 200)
				throw new RuntimeException(response.getStatusLine().toString() + " for URL [" + url + "]");
			if (entity != null) {
				try (FileOutputStream outstream = new FileOutputStream(myFile)) {
					entity.writeTo(outstream);
				}
			}
		}
	}

}