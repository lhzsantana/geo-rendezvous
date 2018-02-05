package org.ufsc.rendezvous.concepts.spatioTemporal;

import org.ufsc.rendezvous.concepts.Resource;

public class SpatioTemporalResource extends Resource {

    private String location;

    public SpatioTemporalResource(String id, String value, String location) {
        super(id, value);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
