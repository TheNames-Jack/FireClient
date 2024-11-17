package net.minecraft.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PlayerInfo {
	public static final String USERNAME_TO_UUID_URL = "https://api.mojang.com/users/profiles/minecraft/";
	public static final String PLAYER_PROFILE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/";
	
	public static String GetSkinURL(String uuid) {
		try {
			URL url = new URL(PLAYER_PROFILE_URL + uuid);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				while((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> profileData = mapper.readValue(response.toString(), Map.class);
				// Access "properties" array to get skin URL
				List<Map<String, String>> properties = (List<Map<String, String>>) profileData.get("properties");
				for(Map<String, String> property : properties) {
					if("textures".equals(property.get("name"))) {
						// The "value" field is base64 encoded, so decode it
						String textureDataJson = new String(Base64.getDecoder().decode(property.get("value")));
						JsonNode texturesNode = mapper.readTree(textureDataJson);
						return texturesNode.get("textures").get("SKIN").get("url").asText();
					}
				}
				return null;
			}else {
				System.out.println("SKIN: GET request failed. Response code: " + responseCode);
				return "";
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String GetCapeURL(String uuid) {
		try {
			URL url = new URL(PLAYER_PROFILE_URL + uuid);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				while((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> profileData = mapper.readValue(response.toString(), Map.class);
				// Access "properties" array to get skin URL
				List<Map<String, String>> properties = (List<Map<String, String>>) profileData.get("properties");
				for(Map<String, String> property : properties) {
					if("textures".equals(property.get("name"))) {
						// The "value" field is base64 encoded, so decode it
						String textureDataJson = new String(Base64.getDecoder().decode(property.get("value")));
						JsonNode texturesNode = mapper.readTree(textureDataJson);
						if(texturesNode.get("textures").has("CAPE")) {
							System.out.println("CAPE IN METHOD TEST: " + texturesNode.get("textures").get("CAPE").get("url").asText());
							return texturesNode.get("textures").get("CAPE").get("url").asText();
						}
					}
				}
				return null;
			}else {
				System.out.println("CAPE: GET request failed. Response code: " + responseCode);
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static EnumSkinType GetPlayerSkinType(String uuid) {
		try {
			URL url = new URL(PLAYER_PROFILE_URL + uuid);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				while((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> profileData = mapper.readValue(response.toString(), Map.class);
				// Access "properties" array to get skin URL
				List<Map<String, String>> properties = (List<Map<String, String>>) profileData.get("properties");
				for(Map<String, String> property : properties) {
					if("textures".equals(property.get("name"))) {
						// The "value" field is base64 encoded, so decode it
						String textureDataJson = new String(Base64.getDecoder().decode(property.get("value")));
						JsonNode texturesNode = mapper.readTree(textureDataJson);
						JsonNode metadataNode = texturesNode.get("textures").get("SKIN").get("metadata");
						if(metadataNode != null) {
							if(metadataNode.get("model").asText().equals("slim")) {
								return EnumSkinType.Slim;
							}
						}
					}
				}
				return EnumSkinType.Classic;
			}else {
				System.out.println("SKIN TYPE: GET request failed. Response code: " + responseCode);
				return EnumSkinType.Classic;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return EnumSkinType.Classic;
		}
	}
	
	public static String GetUUID(String username) {
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(USERNAME_TO_UUID_URL + username).openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				StringBuilder response = new StringBuilder();
				String inputLine;
				while((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> responseBody = mapper.readValue(response.toString(), Map.class);
				return responseBody.get("id");
			}else {
				System.out.println("UUID: GET request failed. Response code: " + responseCode);
				return null;
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}