/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movieexamproject.dal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author domin
 */
public class OmdbAPI {
    private static final String GET_URL = "http://www.omdbapi.com/?t=";
    private static final String API_KEY = "&apikey=";

    public String sendGET(String q) throws IOException {
        String querry = q.replaceAll(" ", "+");
        
        URL obj = new URL(GET_URL+querry+API_KEY);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(
                                con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                }
                in.close();

                return response.toString();
        } else {
                System.out.println("GET request didnt work");
        }
        return null;
    }
}
