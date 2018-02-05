package org.ufsc.rendezvous.cache;

import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Triple;

import java.util.Set;

public interface CacheAccess {

    void put(Triple triple);

    Set<Triple> get(BGP bgp);
}
