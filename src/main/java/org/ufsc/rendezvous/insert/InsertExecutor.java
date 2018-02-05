package org.ufsc.rendezvous.insert;

import org.bson.Document;
import org.neo4j.driver.v1.summary.Plan;
import org.ufsc.rendezvous.access.DatabaseAccess;
import org.ufsc.rendezvous.access.DocumentAccess;
import org.ufsc.rendezvous.access.GraphAccess;
import org.ufsc.rendezvous.access.impl.MongoDBAccess;
import org.ufsc.rendezvous.access.impl.Neo4JAccess;
import org.ufsc.rendezvous.cache.CacheAccess;
import org.ufsc.rendezvous.cache.impl.RedisAccess;
import org.ufsc.rendezvous.concepts.Shape;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.dictionary.Dictionary;
import org.ufsc.rendezvous.fragment.DocumentFragment;
import org.ufsc.rendezvous.fragment.Fragment;
import org.ufsc.rendezvous.fragment.GraphFragment;
import org.ufsc.rendezvous.fragment.fragmenter.DocumentFragmenter;
import org.ufsc.rendezvous.fragment.fragmenter.GraphFragmenter;
import org.ufsc.rendezvous.index.Indexer;
import org.ufsc.rendezvous.statistics.WorkloadStatistics;

import java.util.Set;

public class InsertExecutor {

    private WorkloadStatistics statistics = new WorkloadStatistics();
    private Dictionary dictionary = new Dictionary();
    private Indexer indexer = new Indexer();
    private CacheAccess cache = new RedisAccess();

    private DocumentFragmenter documentFragmenter = new DocumentFragmenter();
    private GraphFragmenter graphFragmenter = new GraphFragmenter();

    public void execute(InsertPlan plan){

        if(!isDuplicated(plan.getTriple())){

            if(plan.isGeo()){

            }

            writeToDatabases(plan);
        }
    }

    private boolean isDuplicated(Triple triple){
        return dictionary.checkDuplicated(triple);
    }

    private void writeToDatabases(InsertPlan insertPlan) {

        Triple triple = insertPlan.getTriple();
        Shape shape = statistics.getShape(triple);
        Integer size = statistics.getHorizon(triple);

        switch (shape) {
            case STAR:
                DatabaseAccess documentAccess = new MongoDBAccess();
                documentAccess.writeToDatabase(documentFragmenter.fragment(insertPlan, size));
                break;
            case CHAIN:
                DatabaseAccess graphAccess = new Neo4JAccess();
                graphAccess.writeToDatabase(graphFragmenter.fragment(insertPlan, size));
                break;
        }

        dictionary.updateDictionary(triple);
        indexer.index(triple);
        cache.put(triple);
    }

}
