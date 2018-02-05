package org.ufsc.rendezvous.concepts.spatioTemporal.filter;

import org.ufsc.rendezvous.concepts.Filter;

public class SpatialFilter extends Filter {

    public SpatialFilter(String field, Type type, String value) {
        super();

        this.type=type;
        this.field=field;
        this.value=value;
    }

    public enum Type {CONTAINS, INTERSECTS};

    private Type type;
    private String value;
    private String field;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
