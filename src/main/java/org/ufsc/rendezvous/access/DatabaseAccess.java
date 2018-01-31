package org.ufsc.rendezvous.access;

import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Filter;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.fragment.Fragment;

import java.util.Set;

public interface DatabaseAccess {

    public void writeToDatabase(Fragment fragment);

    public Set<Triple> queryDatabase(Set<BGP> bgp, Set<Filter> filters);
}
