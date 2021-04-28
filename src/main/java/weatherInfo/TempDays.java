package weatherInfo;

import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TempDays {
	 
	public static void printWeatherReport() {
		
		Response resp = RestAssured.get("http://api.openweathermap.org/data/2.5/forecast?q=Sydney,AU&appid=8402e4ad5a39085c8c7e0cd4e8329329&units=metric");
		
		List<Float> listOfTemp = resp.jsonPath().getList("list.main.temp");   //Temperature
		
		List<String> listOfDate = resp.jsonPath().getList("list.dt_txt");     // Date and time
		
		List<ArrayList<String>> listOfSunny = resp.jsonPath().getList("list.weather.description");
		
		// HashMap data structure to represent date and temperature
		
		HashMap<String, Float> dailyTemp = new HashMap<String, Float>();
		
		// HashMap data structure to represent date and sunny-day
		
		HashMap<String, Boolean> dailyWeather = new HashMap<String, Boolean>();

		for(int i = 0; i < listOfTemp.size(); i++) {
			
			// Split date and time and I considered only the date part as for each day there are three temperature values
			// thereby increasing complexity of computation
			
			String[] splitted = listOfDate.get(i).split(" "); 
			
			String date = splitted[0];
			String time = splitted[1];
			
			Float temp = listOfTemp.get(i);

			if (!dailyTemp.containsKey(date)) {
				dailyTemp.put(date, temp);
			} else {
				if (dailyTemp.get(date) < temp) {   // maximum value among 3 temperature readings for any particular day
					dailyTemp.put(date, temp);
				}
			}

			/* ---- To find what day(s) is sunny ---- */

			String weatherValue = listOfSunny.get(i).get(0);
			
			// Defining time bracket for night
			
			Boolean isNight = time.startsWith("00") || time.startsWith("18") || time.startsWith("21");
			
			// Assuming clear sky represents sunny day
			
			Boolean isSunny = weatherValue.contains("clear sky");

			if(dailyWeather.containsKey(date)) {
				dailyWeather.put(date, isNight ? dailyWeather.get(date) : isSunny);
			} else {
				dailyWeather.put(date, isNight ? false : isSunny);
			}
		}
		
		// Only displaying day(date) when temperature is greater than 20 degree celsius
		
		Iterator<Entry<String, Float>> it = dailyTemp.entrySet().iterator();
		
		while(it.hasNext()) {	
			Entry<String, Float> entry= it.next();
			
			if(entry.getValue() > 20) {
				System.out.println(entry.getKey() + " has temperature over 20 degrees celsius");
			}
		}
		
		// Only showing day(s) which is sunny
		
		Iterator<Entry<String, Boolean>> it2 = dailyWeather.entrySet().iterator();
		while(it2.hasNext()) {
			Entry<String, Boolean> entry= it2.next();
			
			if(entry.getValue()) {
				System.out.println(entry.getKey() + " is predicated to be sunny");
			}
		}	
	}

    // Reloading application every 30 minutes (in milliseconds) to handle change in weather report
	
	public static void main(String[] args) { 
		
	    printWeatherReport();
	    
		long lastFetched = System.currentTimeMillis(); 
		
		while(true) { 
			boolean shouldFetchAgain = System.currentTimeMillis() - lastFetched >= (1800 * 1000);
			
			if (shouldFetchAgain) { 
				System.out.println("");
				System.out.println("updating data..."); 
				System.out.println("");
				printWeatherReport();
				System.currentTimeMillis(); 
			}
		} 
	}
}
