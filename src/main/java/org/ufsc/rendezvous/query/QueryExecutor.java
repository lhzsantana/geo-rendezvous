package org.ufsc.rendezvous.query;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.ufsc.rendezvous.access.DatabaseAccess;
import org.ufsc.rendezvous.access.impl.MongoDBAccess;
import org.ufsc.rendezvous.access.impl.Neo4JAccess;
import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Shape;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.statistics.WorkloadStatistics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class QueryExecutor {

    WorkloadStatistics statistics = new WorkloadStatistics();

    private static final Integer STAR_THRESHOLD = 2;

    public void execute(QueryPlan queryPlan){

        Set<Triple> resultSet = new HashSet<>();

        Shape queryShape = decideShape(queryPlan.getBgps());

        switch (queryShape) {
            case STAR:
                DatabaseAccess documentAccess = new MongoDBAccess();
                documentAccess.queryDatabase(queryPlan.getBgps(), queryPlan.getFilters());
                break;
            case CHAIN:
                DatabaseAccess graphAccess = new Neo4JAccess();
                graphAccess.queryDatabase(queryPlan.getBgps(), queryPlan.getFilters());
                break;
        }

        statistics.registerQuery(resultSet, queryShape, queryPlan.getBgps().size());

    }

    private Shape decideShape(Set<BGP> bgps){

        if(bgps.size()<2){
            return Shape.STAR;
        }else{

            MultiMap<String, BGP> S_PO = new MultiValueMap<>();
            MultiMap<String, BGP> O_PS = new MultiValueMap<>();
            Set<String> values = new HashSet<>();

            for(BGP bgp:bgps){
                S_PO.put(bgp.getSubject(), bgp);
                O_PS.put(bgp.getObject(), bgp);

                values.add(bgp.getObject());
                values.add(bgp.getSubject());
            }

            for(String value:values) {
                Collection subjectStar = (Collection) S_PO.get(value);
                if(subjectStar != null && subjectStar.size()>(bgps.size()/STAR_THRESHOLD)){
                    return Shape.STAR;
                }

                Collection objectStar = (Collection) O_PS.get(value);
                if(objectStar != null && objectStar.size()>(bgps.size()/STAR_THRESHOLD)){
                    return Shape.STAR;
                }

                if(subjectStar!=null && objectStar!=null && (subjectStar.size() == objectStar.size())){
                    return Shape.CHAIN;
                }
            }

            return Shape.STAR;
        }
    }
}
