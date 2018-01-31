package org.ufsc.rendezvous.concepts;

public class GeoResource extends Resource {

    private String location;

    public GeoResource(String id, String value, String location) {
        super(id, value);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
