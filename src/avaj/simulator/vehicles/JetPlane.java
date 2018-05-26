package avaj.simulator.vehicles;

import avaj.simulator.CreateFile;
import avaj.weather.Coordinates;
import avaj.simulator.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateConditions() {
        String weather = weatherTower.getWeather(super.coordinates);

        switch (weather) {
            case "RAIN":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude() + 5,
                        super.coordinates.getHeight(), "I'm fast as a flash and I'm mourning for rain.");
                break;
            case "FOG":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude() + 1,
                        super.coordinates.getHeight(), "There is no pedal in the floor and no fog.");
                break;
            case "SUN":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude() + 10,
                        super.coordinates.getHeight() + 2, "The sun is burning the river flows Summer in the street so hot!");
                break;
            case "SNOW":
                updateCoor(super.coordinates.getLongitude(), super.coordinates.getLatitude(),
                        super.coordinates.getHeight() - 7, "-40°C? have not heard.");
                break;
            default:
                break;
        }
        if (super.coordinates.getHeight() <= 0) {
            weatherTower.unregister(this);
            CreateFile.writeFile("JetPlane#" + super.name + "(" + super.id + ") landing.");
            CreateFile.writeFile("Tower says: JetPlane#" + super.name + "(" + super.id + ") unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        weatherTower.register(this);
        CreateFile.writeFile("Tower says: JetPlane#" + super.name + "(" + super.id + ") registered to weather tower.");
    }

    private void updateCoor(int longi, int lati, int height, String message) {
        Coordinates newCoor;
        newCoor = new Coordinates(longi, lati, height);
        super.setCoordinates(newCoor);
        CreateFile.writeFile("JetPlane#" + super.name + "(" + super.id + "): " + message);
    }

}
