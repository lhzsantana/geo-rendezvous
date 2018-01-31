package org.ufsc.rendezvous.fragment;

import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;

import java.util.List;

public class GraphFragment extends Fragment{

    private Triple coreTriple;

    private Resource subject;

    private List<Resource> predicates;

    private Resource object;

    public Resource getSubject() {
        return subject;
    }

    public void setSubject(Resource subject) {
        this.subject = subject;
    }

    public List<Resource> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Resource> predicates) {
        this.predicates = predicates;
    }

    public Resource getObject() {
        return object;
    }

    public void setObject(Resource object) {
        this.object = object;
    }

    public Triple getCoreTriple() {
        return coreTriple;
    }

    public void setCoreTriple(Triple coreTriple) {
        this.coreTriple = coreTriple;
    }
}
