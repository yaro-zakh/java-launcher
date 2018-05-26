package avaj.simulator.vehicles;

import avaj.simulator.CreateFile;
import avaj.weather.Coordinates;
import avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(super.coordinates);

        switch (weather) {
            case "RAIN":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude(),
                        super.coordinates.getHeight() - 5, "Damn my balls again to get wet.");
                break;
            case "FOG":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude(),
                        super.coordinates.getHeight() - 3, "This fog prevents admiring landscapes.");
                break;
            case "SUN":
                updateCoor(super.coordinates.getLongitude() + 2, super.coordinates.getLatitude(),
                        super.coordinates.getHeight() + 4, "Incredible heat, it's a shame for me to forget the sunglasses.");
                break;
            case "SNOW":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude(),
                        super.coordinates.getHeight() - 15, "As soon as I land, I immediately glitter the snowman.");
                break;
            default:
                break;
        }
        if (super.coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            CreateFile.writeFile("Baloon#" + super.name + "(" + super.id + ") landing.");
            CreateFile.writeFile("Tower says: Baloon#" + super.name + "(" + super.id + ") unregistered from weather tower.");
		}
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        CreateFile.writeFile("Tower says: Baloon#" + super.name + "(" + super.id + ") registered to weather tower.");
    }

    private void updateCoor(int longi, int lati, int height, String message) {
        Coordinates newCoor;
        newCoor = new Coordinates(longi, lati, height);
        super.setCoordinates(newCoor);
        CreateFile.writeFile("Baloon#" + super.name + "(" + super.id + "): " + message);
    }
}