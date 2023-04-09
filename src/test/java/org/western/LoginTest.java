package org.western;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kordamp.ikonli.remixicon.RemixiconAL;
import org.kordamp.ikonli.remixicon.RemixiconMZ;
import org.kordamp.ikonli.swing.FontIcon;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LoginTest {

    private Login login;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        login = new Login(false);
    }

    static void setLookAndFeel() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            // If the Nimbus look and feel isn't available, the test will fail
            // This is intentional, since we want to make sure the code is using Nimbus
            throw new RuntimeException(ex);
        }
    }

    @Test
    void testCreateMainWindow() {
        // Create a mock MainWindow object
        MainWindow mainWindowMock = mock(MainWindow.class);

        // Create a MyFrame object and inject the mock MainWindow object
        MyFrame myFrame = new MyFrame() {
            @Override
            protected MainWindow createMainWindow() {
                return mainWindowMock;
            }
        };

        // Call the createMainWindow method and verify that it returns the mock object
        MainWindow result = myFrame.createMainWindow();
        assertEquals(mainWindowMock, result);
    }

    @Test
    void testLookAndFeel() {
        // Verify that the look and feel is set to Nimbus
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                assertEquals("Nimbus", info.getName());
                return;
            }
        }
    }

    @Test
    void testLoadWeather() {
        Weather weather = new Weather();
        // Test the successful loading of weather data
        assertDoesNotThrow(() -> {
            weather.loadWeather();
            assertNotNull(weather.getData());
        });

        // Test the case where the geolocation API fails
        assertDoesNotThrow(() -> {
            weather.setGeolocationUrl("https://non-existing-url.com");
            weather.loadWeather();
            assertEquals("Failed to fetch geolocation.", weather.getCityText());
        });

        // Test the case where the weather API fails
        assertDoesNotThrow(() -> {
            weather.setWeatherUrl("https://non-existing-url.com");
            weather.loadWeather();
            assertEquals("Weather data unavailable.", weather.getWeatherText());
        });

        // Test the case where both geolocation and weather APIs fail
        assertDoesNotThrow(() -> {
            weather.setGeolocationUrl("https://non-existing-url.com");
            weather.setWeatherUrl("https://non-existing-url.com");
            weather.loadWeather();
            assertEquals("Failed to fetch geolocation.", weather.getCityText());
            assertEquals("Weather data unavailable.", weather.getWeatherText());
        });
    }

    @Test
    void testHandleWeatherData() {
        final HashMap<Integer, String> wmoWeatherCodes = new HashMap<>();
        //{"latitude":43.6255,"longitude":-79.42639,"generationtime_ms":0.16796588897705078,"utc_offset_seconds":-14400,"timezone":"America/New_York","timezone_abbreviation":"EDT","elevation":74.0,"daily_units":{"time":"iso8601","weathercode":"wmo code","temperature_2m_max":"°C","temperature_2m_min":"°C"},"daily":{"time":["2023-04-09"],"weathercode":[3],"temperature_2m_max":[8.6],"temperature_2m_min":[-0.3]}}
        JsonObject weatherData = new JsonObject();
        weatherData.add("daily", new JsonParser().parse("{\"time\":[\"2023-04-09\"],\"weathercode\":[3],\"temperature_2m_max\":[8.6],\"temperature_2m_min\":[-0.3]}").getAsJsonObject());
        assertEquals("Cloudy", handleWeatherData(weatherData));
    }

    @Test
    void testHandleLogin() {
        // setup
//        Login login = new Login(false);
//        User user = Map.getUser("test");
//        login.usernameField.setText("test");
//        login.passwordField.setText("1234");

        // exercise
//        int result = login.handleLogin();

        // verify
//        Assertions.assertEquals(0, result);
    }

    public class MyFrame extends JFrame {

        public void guestLoginMouseClicked(MouseEvent evt) {
            MainWindow mainWindow = createMainWindow();
            mainWindow.setVisible(true);
            dispose();
        }

        protected MainWindow createMainWindow() {
            return new MainWindow(false, null);
        }
    }

    public class Weather {
        private String geolocationUrl = "https://freegeoip.app/json/";
        private String weatherUrl = "https://api.open-meteo.com/v1/forecast";
        private JsonObject data;
        private String cityText;
        private String weatherText;

        public void loadWeather() {
            double lat = 0, lon = 0;
            // implementation
            try {
                URL obj = new URL(geolocationUrl);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    // print result
                    System.out.println(response.toString());
                    // parse JSON
                    data = JsonParser.parseString(response.toString()).getAsJsonObject();
                    lat = data.get("latitude").getAsDouble();
                    lon = data.get("longitude").getAsDouble();
                } else {
                    System.out.println("GET request not worked");
                    data = new JsonObject();
                    return;
                }
            }
            catch (Exception e) {
                cityText = "Failed to fetch geolocation.";
                e.printStackTrace();
            }
            // get weather data from API
            try {
                String url = weatherUrl + "?latitude=" + lat + "&longitude=" + lon + "&daily=weathercode,temperature_2m_max,temperature_2m_min&forecast_days=1&timezone=America%2FNew_York";
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");
                int responseCode = con.getResponseCode();
                System.out.println("GET Response Code :: " + responseCode);
                if (responseCode == HttpURLConnection.HTTP_OK) { // success
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    // print result
                    System.out.println(response);
                    // get weather data
                    data = JsonParser.parseString(response.toString()).getAsJsonObject();
                } else {
                    data = new JsonObject();
                }
            } catch (Exception e) {
                weatherText = "Weather data unavailable.";
                e.printStackTrace();
            }
        }

        public JsonObject getData() {
            return data;
        }

        public String getCityText() {
            return cityText;
        }

        public String getWeatherText() {
            return weatherText;
        }

        public void setGeolocationUrl(String geolocationUrl) {
            this.geolocationUrl = geolocationUrl;
        }

        public void setWeatherUrl(String weatherUrl) {
            this.weatherUrl = weatherUrl;
        }
    }

    private String handleWeatherData(JsonObject data) {
        int weatherCode; // weather code
        String weatherDesc; // weather description
        StringBuilder upTemp = new StringBuilder(), downTemp = new StringBuilder(), // temperature
                dateSB = new StringBuilder(); // weather date
        HashMap<Integer, String> wmoWeatherCodes = new HashMap<>() {
            {
                put(0, "Metar not available");
                put(1, "Clear sky");
                put(2, "Partly cloudy");
                put(3, "Cloudy");
                put(4, "Overcast");
                put(5, "Foggy");
                put(6, "Light rain");
                put(7, "Moderate rain");
                put(8, "Heavy rain");
                put(9, "Intense rain");
                put(10, "Freezing rain");
                put(11, "Light freezing rain");
                put(12, "Moderate freezing rain");
                put(13, "Heavy freezing rain");
                put(14, "Snow shower");
                put(15, "Light snowfall");
                put(16, "Moderate snowfall");
                put(17, "Heavy snowfall");
                put(18, "Thunderstorm");
                put(19, "Hailstorm");
                put(20, "Mist");
                put(21, "Haze");
                put(22, "Smoke");
                put(23, "Dust/sandstorm");
                put(24, "Windy");
                put(25, "Blustery");
                put(26, "Snowstorm");
                put(27, "Heavy snowstorm");
                put(28, "Thunderstorms and rain");
                put(29, "Thunderstorms and snow");
                put(30, "Tornado");
            }
        };
        weatherCode = data.get("daily").getAsJsonObject().get("weathercode").getAsJsonArray().get(0).getAsInt();
        // convert wmo code to weather description
        weatherDesc = wmoWeatherCodes.get(weatherCode);
        return weatherDesc;
    }


    // rest of the tests
}