package org.ufsc.rendezvous.query;

import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Filter;

import java.util.Set;

public class QueryPlan {

    private Set<BGP> bgps;

    private Set<Filter> filters;

    public Set<BGP> getBgps() {
        return bgps;
    }

    public void setBgps(Set<BGP> bgps) {
        this.bgps = bgps;
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
    }
}
