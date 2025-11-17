package com.proj.FitLifeTracker.service.api;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;

@Service
public class SpoonacularService {
	
	@Value("${spoonacular.api.url}")
	private String apiUrl;
	
	
	@Value("${spoonacular.api.key}")
	private String apiKey;
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	public int getCaloriesFromApi(String foodName) {
		if(foodName == null || foodName.trim().isEmpty()) {
			System.out.println("Food name is empty!");
			return 0;
		}
	try {
		RestTemplate restTemplate = new RestTemplate();
		String url = apiUrl + "?title=" + foodName + "&apiKey=" + apiKey;
		ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
		
		if (response.getStatusCode() != HttpStatus.OK){
			System.out.println("Api Call Filed with status: " + response.getStatusCode());
			return 0;	
		}
		JsonNode root = objectMapper.readTree(response.getBody());
		if(root.has("calories")){
			JsonNode caloriesNode= root.get("calories");
			if(caloriesNode.has("value")) {
				return caloriesNode.get("value").asInt();
			}
			else {
				System.out.println("⚠️ 'calories.value' found in response");
			}
		}else {
			System.out.println("⚠️ 'calories' not  found in response");
		}
		
	}catch(HttpStatusCodeException e) {
		System.err.println("API returned error: " + e.getStatusCode());
        System.err.println("Response Body: " + e.getResponseBodyAsString());
	}catch (Exception e) {
        System.err.println("❌ Unexpected error: " + e.getMessage());
    }
	return 0;
		
	}
	

}
