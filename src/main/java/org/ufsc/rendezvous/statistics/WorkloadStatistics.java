package org.ufsc.rendezvous.statistics;

import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Shape;
import org.ufsc.rendezvous.concepts.Triple;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.ufsc.rendezvous.concepts.Shape.CHAIN;
import static org.ufsc.rendezvous.concepts.Shape.STAR;

public class WorkloadStatistics{

    private static final Map<Triple, Integer> chainCounter = new HashMap<>();
    private static final Map<Triple, Integer> starCounter = new HashMap<>();
    private static final Map<Triple, Integer> querySize = new HashMap<>();

    public void registerQuery(Set<Triple> results, Shape shape, Integer size){

        for(Triple triple:results){

            if(shape.equals(Shape.STAR)) {
                starCounter.merge(triple, 1, (oldValue, one) -> oldValue + one);
            }

            if(shape.equals(Shape.STAR)) {
                chainCounter.merge(triple, 1, (oldValue, one) -> oldValue + one);
            }

            querySize.put(triple, querySize.get(triple)>size?size:querySize.get(triple));
        }
    }

    public int getHorizon(Triple triple) {

        if(!querySize.containsKey(triple)){
            return 1;
        }

        return querySize.get(triple);
    }

    public Shape getShape(Triple triple) {

        Integer chainCount = chainCounter.get(triple);
        Integer starCount = starCounter.get(triple);

        if(chainCount!=null & starCount!=null && chainCount > starCount) {
            return CHAIN;
        }

        return STAR;
    }

    public List<Resource> getLongestChain(Triple triple) {
        return null;
    }
}
