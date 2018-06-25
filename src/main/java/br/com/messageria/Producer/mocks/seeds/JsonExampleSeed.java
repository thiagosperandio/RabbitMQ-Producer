package br.com.messageria.Producer.mocks.seeds;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonExampleSeed {
	
	public JsonObject getExemploJson() throws IOException {
		JsonObject obj = null;
		
		obj = Json.createObjectBuilder()
				   .add("firstName", "Duke")
				   .add("lastName", "Java")
				   .add("age", 18)
				   .add("streetAddress", "100 Internet Dr")
				   .add("city", "JavaTown")
				   .add("state", "JA")
				   .add("postalCode", "12345")
				   .add("phoneNumbers", Json.createArrayBuilder()
				      .add(Json.createObjectBuilder()
				         .add("type", "mobile")
				         .add("number", "111-111-1111"))
				      .add(Json.createObjectBuilder()
				         .add("type", "home")
				         .add("number", "222-222-2222")))
				   .build();

		return obj;
	}
	
	public JsonObject getExemploJsonByUrl(String urlString) throws IOException {
		JsonObject obj = null;
		
		URL url = new  URL(urlString); // ex.: "https://graph.facebook.com/search?q=java&type=post"
		try (InputStream is = url.openStream();
			JsonReader rdr = Json.createReader(is)) {
				obj = rdr.readObject();
				
				// Exibir no console de saída: 
				JsonArray results = obj.getJsonArray("data");
				for (JsonObject result : results.getValuesAs(JsonObject.class)) {
					System.out.print(result.getJsonObject("from").getString("name"));
					System.out.print(": ");
					System.out.println(result.getString("message", ""));
					System.out.println("-----------");
				}
			}
		return obj;
	}

}
