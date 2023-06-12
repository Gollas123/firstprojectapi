package com.myfirstspringproject.firstspringrestapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.http.JsonNode;

// import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
  @ResponseBody
public class firstController {
    
    @GetMapping("/health")
  
    public String first_api(){

        return "Running...........";
    }

    
// weather

@GetMapping("/weather")
        public String getCurrentWeather(){     
       try {
HttpResponse<JsonNode> response = Unirest.get("https://api.open-meteo.com/v1/forecast")
                      .queryString("daily","temperature_2m_max,temperature_2m_min,sunrise,sunset")
                      .queryString("longitude",83.22)
                      .queryString("latitude",17.72)
                      .queryString("current_weather",true)
                      .queryString("forecast_days",1)
                      .queryString("timezone","auto")
                      .header("content-type","application/json")
                      .asJson();
                      System.out.println("response  1"+response.getBody());
                      System.out.println("----------------------");
                        System.out.println("response  3"+response.getBody().getObject());

                    String myobj = response.getBody().toString();
                     return myobj;
              
                   //System.out.println("sss"+myobj);
    } catch (UnirestException e) {
        e.printStackTrace();
    }
   return "success";
       
        
    }

//end of weather

    


    
    // third api
    
     @GetMapping("/w3")
    public static void main(String[] args) {

        /*
        Maven dependency for JSON-simple:
            <dependency>
                <groupId>com.googlecode.json-simple</groupId>
                <artifactId>json-simple</artifactId>
                <version>1.1.1</version>
            </dependency>
         */

        try {
            //Public API:
            //https://www.metaweather.com/api/location/search/?query=<CITY>
            //https://www.metaweather.com/api/location/44418/

            URL url = new URL("https://www.metaweather.com/api/location/search/?query=London");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            // 200 OK
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {

                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);


                //JSON simple library Setup with Maven is used to convert strings to JSON
                JSONParser parse = new JSONParser();
                JSONArray dataObject = (JSONArray) parse.parse(String.valueOf(informationString));

                //Get the first JSON object in the JSON array
                System.out.println(dataObject.get(1));              

                JSONObject countryData = (JSONObject) dataObject.get(0);

                System.out.println(countryData.toString());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
}

    // end of 3rd api

    




}
