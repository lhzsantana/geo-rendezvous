package org.ufsc.rendezvous.insert;

import org.ufsc.rendezvous.concepts.Triple;

public class InsertPlan {

    private Triple triple;
    private boolean geo;

    public boolean isGeo() {
        return geo;
    }

    public void setGeo(boolean geo) {
        this.geo = geo;
    }

    public Triple getTriple() {
        return triple;
    }

    public void setTriple(Triple triple) {
        this.triple = triple;
    }
}
