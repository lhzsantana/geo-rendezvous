package org.ufsc.rendezvous.dictionary;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Dictionary {

    private MultiMap<Resource, Triple> S_PO = new MultiValueMap<>();
    private MultiMap<Resource, Triple> O_SP = new MultiValueMap<>();

    private Set<Triple> triples = new HashSet<>();

    public void updateDictionary(Triple triple) {
        S_PO.put(triple.getSubject(), triple);
        O_SP.put(triple.getObject(), triple);

        triples.add(triple);
    }

    public boolean checkDuplicated(Triple triple) {
        return triples.contains(triple);
    }

}
