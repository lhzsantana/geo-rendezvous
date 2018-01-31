package org.ufsc.rendezvous.access.impl;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.ufsc.rendezvous.access.DocumentAccess;
import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Filter;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.fragment.DocumentFragment;
import org.ufsc.rendezvous.fragment.Fragment;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MongoDBAccess extends DocumentAccess{

    private static MongoCollection collection;

    private synchronized MongoCollection getCollection(){

        if(collection==null) {

            MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));

            MongoDatabase database = mongoClient.getDatabase("rendezvous");

            collection = database.getCollection("rendezvous");
        }

        return collection;
    }

    @Override
    public void writeToDatabase(Fragment fragment) {

        getCollection().insertOne(((DocumentFragment) fragment).getDocument());
    }

    @Override
    public Set<Triple> queryDatabase(Set<BGP> bgps, Set<Filter> filters) {

        Document query = new Document();

        for(BGP bgp:bgps) {
            query.put("subject.value", bgp.getSubject());
        }

        Set<Triple> triples = new HashSet<>();

        FindIterable<Document> result = getCollection().find(query);
        for(Document document:result){
            triples.addAll(documentToTriple(document));
        }

        return triples;
    }

    private Set<Triple> documentToTriple(Document document){

        Set<Triple> triples = new HashSet<>();

        Resource subject = new Resource("", document.get("subject").toString());

        for(String predicate:document.keySet()){
            Triple triple = new Triple();

            triple.setSubject(subject);
            triple.setPredicate(new Resource("", predicate));
            triple.setObject(new Resource("", document.get(predicate).toString()));

            triples.add(triple);
        }

        return triples;
    }
}
