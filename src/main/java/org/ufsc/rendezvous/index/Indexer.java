package org.ufsc.rendezvous.index;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;

import java.util.*;

public class Indexer {

    private static MultiMap<Resource, Triple> S_PO = new MultiValueMap<>();
    private static MultiMap<Resource, Triple> O_SP = new MultiValueMap<>();

    public void index(Triple triple) {
        S_PO.put(triple.getSubject(), triple);
        O_SP.put(triple.getObject(), triple);
    }

    public Set<Triple> getStarSubjectNeighbours(Resource subject) {

        Set<Triple> neighbours = new HashSet<>();

        if(S_PO.containsKey(subject)) {
            neighbours.addAll((Collection<? extends Triple>) S_PO.get(subject));
        }

        return neighbours;
    }
}
