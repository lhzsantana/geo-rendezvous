import org.ufsc.rendezvous.concepts.*;
import org.ufsc.rendezvous.insert.InsertExecutor;
import org.ufsc.rendezvous.insert.InsertPlan;
import org.ufsc.rendezvous.query.QueryExecutor;
import org.ufsc.rendezvous.query.QueryPlan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InsertFragment {

    public static void main(String [] args) {

        for(Triple t : createInitialGraph()) {
            InsertPlan insertPlan = new InsertPlan();
            insertPlan.setTriple(t);
            insertPlan.setGeo(true);

            InsertExecutor executor = new InsertExecutor();
            executor.execute(insertPlan);
        }

        QueryPlan queryPlanChain = new QueryPlan();
        queryPlanChain.setBgps(createChainQuery());

        QueryExecutor queryExecutor = new QueryExecutor();
        queryExecutor.execute(queryPlanChain);

        QueryPlan queryPlanStar = new QueryPlan();
        queryPlanStar.setBgps(createStarQuery());
        queryPlanStar.setFilters(createFilter());
        queryExecutor.execute(queryPlanStar);

        for(Triple t : createOptimizedGraph()) {
            InsertPlan insertPlan = new InsertPlan();
            insertPlan.setTriple(t);
            insertPlan.setGeo(true);

            InsertExecutor executor = new InsertExecutor();
            executor.execute(insertPlan);
        }
    }

    private static List<Triple> createInitialGraph(){

        Resource r1 = new Resource("1", "r1");
        Resource r2 = new Resource("2", "r2");
        Resource r3 = new Resource("3", "r3");
        Resource r4 = new Resource("4", "r4");
        Resource r5 = new Resource("5", "r5");

        List<Triple> triples = new ArrayList<>();

        triples.add(new Triple(r1, r2, r3));
        triples.add(new Triple(r3, r2, r4));
        triples.add(new Triple(r1, r2, r5));

        return triples;
    }

    private static List<Triple> createOptimizedGraph(){

        Resource r1 = new Resource("1", "r1");
        Resource r2 = new Resource("2", "r2");
        Resource r3 = new Resource("3", "r3");
        Resource r4 = new Resource("4", "r4");
        Resource r5 = new Resource("5", "r5");

        List<Triple> triples = new ArrayList<>();

        triples.add(new Triple(r1, r2, r3));
        triples.add(new Triple(r3, r2, r4));
        triples.add(new Triple(r1, r2, r5));

        return triples;
    }

    private static Set<BGP> createChainQuery(){

        Set<BGP> bgps = new HashSet<>();

        bgps.add(new BGP("r1", "p1", "r2"));
        bgps.add(new BGP("r2", "p2", "r3"));
        bgps.add(new BGP("r3", "p3", "r4"));

        return bgps;
    }

    private static Set<BGP> createStarQuery(){

        Set<BGP> bgps = new HashSet<>();

        bgps.add(new BGP("r1", "p1", "r2"));
        bgps.add(new BGP("r1", "p2", "r3"));
        bgps.add(new BGP("r1", "p3", "r4"));

        return bgps;
    }

    private static Set<Filter> createFilter(){

        Set<Filter> filters = new HashSet<>();

        filters.add(new GeoFilter("r1", GeoFilter.Type.INTERSECTS, "r2"));

        return filters;

    }
}
