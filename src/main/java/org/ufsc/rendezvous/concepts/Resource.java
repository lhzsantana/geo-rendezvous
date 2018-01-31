package org.ufsc.rendezvous.concepts;

public class Resource {

    private String value;
    private String id;

    public Resource(String id, String value) {
        this.id=id;
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        return id.equals(resource.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
