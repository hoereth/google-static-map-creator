package de.pentabyte.googlemaps;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import de.pentabyte.googlemaps.StaticMap.Maptype;

/**
 * @author michael hoereth
 *
 */
public class ExampleCreator {
	private static String APIKEY = "AIzaSyDV7nYgIcNdNaoqzt6pk0yxObvp0bUtH9o";

	@Test
	public void createLocation() throws ClientProtocolException, IOException {
		StaticMap map = new StaticMap(400, 200, APIKEY);
		map.setLocation(new StaticLocation("Eiffeltower"), 16);
		map.setMaptype(Maptype.hybrid);

		create(map, "location.png");
	}

	@Test
	public void createMarkers() throws ClientProtocolException, IOException {
		StaticMap map = new StaticMap(400, 200, APIKEY);
		map.setMaptype(Maptype.hybrid);

		map.addMarker(new StaticMarker("Eiffeltower"));

		StaticMarker notreDame = new StaticMarker(48.853000, 2.349983);
		notreDame.setLabel('N');
		notreDame.setColor("orange");
		map.addMarker(notreDame);

		create(map, "markers.png");
	}

	@Test
	public void createCustomMarker() throws ClientProtocolException, IOException {
		StaticMap map = new StaticMap(400, 200, APIKEY);
		map.setMaptype(Maptype.hybrid);

		StaticMarker m1 = new StaticMarker(50.844943, 6.856998);
		m1.setCustomIconUrl("http://cableparks.info/poi.png");
		map.addMarker(m1);

		StaticMarker m2 = new StaticMarker(50.844782, 6.856730);
		m2.setCustomIconUrl("http://cableparks.info/poi_2.png");
		map.addMarker(m2);

		create(map, "customMarkers.png");
	}

	private void create(StaticMap map, String filename) throws ClientProtocolException, IOException {
		File myUrl = new File("src/test/resources/" + filename + ".txt");
		FileWriter writer = new FileWriter(myUrl);
		writer.write(map.toString());
		writer.close();

		File myFile = new File("src/test/resources/" + filename);

		// CloseableHttpClient client = HttpClients.createDefault();
		// try (CloseableHttpResponse response = client.execute(new
		// HttpGet(map.toString()))) {
		// HttpEntity entity = response.getEntity();
		// if (response.getStatusLine().getStatusCode() != 200)
		// throw new RuntimeException(response.getStatusLine().toString());
		// if (entity != null) {
		// try (FileOutputStream outstream = new FileOutputStream(myFile)) {
		// entity.writeTo(outstream);
		// }
		// }
		// }
	}

}