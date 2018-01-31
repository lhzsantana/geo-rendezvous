package org.ufsc.rendezvous.fragment.fragmenter;

import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.fragment.GraphFragment;
import org.ufsc.rendezvous.insert.InsertPlan;
import org.ufsc.rendezvous.statistics.WorkloadStatistics;

import java.util.List;

public class GraphFragmenter {

    private WorkloadStatistics statistics = new WorkloadStatistics();

    public GraphFragment fragment(InsertPlan insertPlan, Integer size){

        GraphFragment fragment = new GraphFragment();

        fragment.setCoreTriple(insertPlan.getTriple());

        List<Resource> chain = statistics.getLongestChain(insertPlan.getTriple());

        fragment.setSubject(chain.get(0));
        fragment.setPredicates(chain);
        fragment.setObject(chain.get(chain.size()));

        return fragment;
    }
}
