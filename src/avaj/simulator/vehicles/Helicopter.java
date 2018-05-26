package avaj.simulator.vehicles;

import avaj.simulator.CreateFile;
import avaj.weather.Coordinates;
import avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(super.coordinates);

        switch (weather) {
            case "RAIN":
                updateCoor(super.coordinates.getLongitude() + 5, super.coordinates.getLatitude(),
                        super.coordinates.getHeight(), "I do not like when the blades are wet.");
                break;
            case "FOG":
                updateCoor(super.coordinates.getLongitude() + 1, super.coordinates.getLatitude(),
                        super.coordinates.getHeight(), "I'm flying the instrument.");
                break;
            case "SUN":
                updateCoor(super.coordinates.getLongitude() + 2, super.coordinates.getLatitude(),
                        super.coordinates.getHeight() + 4, "The sun shines right in the peak.");
                break;
            case "SNOW":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude(),
                        super.coordinates.getHeight() - 12, "Winter cocktails ordered?");
                break;
            default:
                break;
        }
        if (super.coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            CreateFile.writeFile("Helicopter#" + super.name + "(" + super.id + ") landing.");
            CreateFile.writeFile("Tower says: Helicopter#" + super.name + "(" + super.id + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        CreateFile.writeFile("Tower says: Helicopter#" + super.name + "(" + super.id + ") registered to weather tower.");
    }

    private void updateCoor(int longi, int lati, int height, String message) {
        Coordinates newCoor;
        newCoor = new Coordinates(longi, lati, height);
        super.setCoordinates(newCoor);
        CreateFile.writeFile("Helicopter#" + super.name + "(" + super.id + "): " + message);
    }
}
