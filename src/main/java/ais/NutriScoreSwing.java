package ais;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.ws.rs.core.UriBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NutriScoreSwing {

	private static final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1)
			.connectTimeout(Duration.ofSeconds(10)).build();

	private static JFrame frame = new JFrame("NutriScore");
	private static Label nutriScoreValueLabel = new Label("");
	private static TimerTask timerTask = new TimerTask() 
	{ 
		public void run()  
		{ 
			try {
				if(frame.isActive()) {
					updateNutriScore(nutriScoreValueLabel);
				}
			} catch (IOException | InterruptedException | JSONException e) {
				e.printStackTrace();
			}
		} 
	};
	
	
	public static void main(String[] args)  {
		frame.setSize(400, 500);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);

		
		frame.add(nutriScoreValueLabel);
		List<NutriButton> nutributtons = buildNutiButtons(nutriScoreValueLabel);
		nutributtons//
				.forEach(nutriButton -> frame.add(nutriButton));

		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(timerTask, 0, 10000);		
	}

	private static void updateNutriScore(Label nutriScoreValue)
			throws IOException, InterruptedException, JSONException {
		// json formatted data
		String json = new StringBuilder().append("{")

				.append("\"jsonrpc\":\"2.0\",")//
				.append("\"method\":\"generateIntegers\",")//
				.append("\"params\":")
				.append("{")//
				.append("\"apiKey\":\"00000000-0000-0000-0000-000000000000\",")//
				.append("\"n\":1,")
				.append("\"min\":0,")//
				.append("\"max\":4,")//
				.append("\"replacement\":true,")//
				.append("\"base\":10")
				.append("},")//
				.append("\"id\":17465")//
				.append("}")//
				.toString();

		HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create("https://api.random.org/json-rpc/2/invoke")).header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		// print status code
		System.out.println(response.statusCode());

		// print response body
		System.out.println(response.body());
		JSONObject body = new JSONObject(response.body());
	    JSONObject result = body.getJSONObject("result");
	    JSONObject random = result.getJSONObject("random");
	    JSONArray data = (JSONArray)random.get("data");
	    ArrayList<String> listData = new ArrayList<String>();

	    for(int i=0; i<data.length(); i++){
	        listData.add(data.getString(i));
	    }

	    listData.forEach(text->nutriScoreValue.setText(""+text));
	}

	private static List<NutriButton> buildNutiButtons(Label nutriScoreValue) {
		NutriButton nutributtonA = new NutriButton("A", Color.GREEN, 0, nutriScoreValue);
		NutriButton nutributtonB = new NutriButton("B", Color.CYAN, 1, nutriScoreValue);
		NutriButton nutributtonC = new NutriButton("C", Color.YELLOW, 2, nutriScoreValue);
		NutriButton nutributtonD = new NutriButton("D", Color.ORANGE, 3, nutriScoreValue);
		NutriButton nutributtonE = new NutriButton("E", Color.RED, 4, nutriScoreValue);

		List<NutriButton> nutributtons = Arrays.asList(nutributtonA, nutributtonB, nutributtonC, nutributtonD,
				nutributtonE);
		return nutributtons;
	}
	
}