package avaj.weather;

import java.util.Random;

public class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};
    private final Random random = new Random();

    private WeatherProvider() {
    }

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int fullCoor = coordinates.getLongitude() * random.nextInt(42) + coordinates.getLatitude() * random.nextInt(42) + coordinates.getHeight() * random.nextInt(42);
        return weather[fullCoor % 4];
    }
}
