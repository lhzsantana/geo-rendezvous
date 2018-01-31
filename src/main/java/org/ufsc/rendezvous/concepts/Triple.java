package org.ufsc.rendezvous.concepts;

public class Triple {

    public Triple(){

    }

    public Triple(Resource subject, Resource predicate, Resource object){
        this.subject=subject;
        this.predicate=predicate;
        this.object=object;
    }

    private Resource subject;
    private Resource predicate;
    private Resource object;

    public Resource getPredicate() {
        return predicate;
    }

    public void setPredicate(Resource predicate) {
        this.predicate = predicate;
    }

    public Resource getSubject() {
        return subject;
    }

    public void setSubject(Resource subject) {
        this.subject = subject;
    }

    public Resource getObject() {
        return object;
    }

    public void setObject(Resource object) {
        this.object = object;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triple triple = (Triple) o;

        if (subject != null ? !subject.equals(triple.subject) : triple.subject != null) return false;
        if (predicate != null ? !predicate.equals(triple.predicate) : triple.predicate != null) return false;
        return object != null ? object.equals(triple.object) : triple.object == null;
    }

    @Override
    public int hashCode() {
        int result = subject != null ? subject.hashCode() : 0;
        result = 31 * result + (predicate != null ? predicate.hashCode() : 0);
        result = 31 * result + (object != null ? object.hashCode() : 0);
        return result;
    }
}
