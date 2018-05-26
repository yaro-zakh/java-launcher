package avaj.weather;

public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude, int latitude, int height) {
        if (longitude < 0) {
            longitude = 0;
        } else if (latitude < 0) {
            latitude = 0;
        } else if (height > 100) {
			height = 100;
		}
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() {
        return longitude;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getHeight() {
        return height;
    }

}
