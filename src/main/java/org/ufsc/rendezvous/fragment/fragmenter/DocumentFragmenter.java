package org.ufsc.rendezvous.fragment.fragmenter;

import org.bson.Document;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.fragment.DocumentFragment;
import org.ufsc.rendezvous.index.Indexer;
import org.ufsc.rendezvous.insert.InsertPlan;

import java.util.Set;

public class DocumentFragmenter {

    private Indexer indexer = new Indexer();

    public DocumentFragment fragment(InsertPlan insertPlan, Integer size){

        DocumentFragment documentFragment = new DocumentFragment();

        documentFragment.setDocument(expandFragment(insertPlan.getTriple().getSubject(), size));

        return documentFragment;
    }

    private Document expandFragment(Resource subject, Integer size){

        Document document = new Document();
        document.put("subject", resourceToDocument(subject));

        if(size > 0){
            for(Triple neighbour: indexer.getStarSubjectNeighbours(subject)){

                Document expandedFragment = expandFragment(neighbour.getObject(), --size);
                document.put(neighbour.getPredicate().getId(), expandedFragment);
            }
        }

        return document;
    }

    private Document resourceToDocument(Resource resource){

        Document document = new Document();
        document.put("id", resource.getId());
        document.put("value", resource.getValue());

        return document;
    }
}
