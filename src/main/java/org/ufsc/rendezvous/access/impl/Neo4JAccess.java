package org.ufsc.rendezvous.access.impl;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.driver.v1.Session;
import org.neo4j.driver.v1.StatementResult;
import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;
import org.ufsc.rendezvous.access.GraphAccess;
import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Filter;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.fragment.Fragment;
import org.ufsc.rendezvous.fragment.GraphFragment;

import java.util.List;
import java.util.Set;

import static org.neo4j.driver.v1.Values.parameters;

public class Neo4JAccess extends GraphAccess {
    private final Driver driver;

    public Neo4JAccess()
    {
        driver = GraphDatabase.driver( "bolt://localhost:7687", AuthTokens.basic( "neo4j", "rendezvous") );
    }

    private void insertResource(Resource resource){

        String message = "";

        try (Session session = driver.session())
        {
            String greeting = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    StatementResult result = tx.run( "CREATE (a:Resource) " +
                                    "SET a.message = $message " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( greeting );
        }
    }

    @Override
    public void writeToDatabase(Fragment fragment) {

        GraphFragment graphFragment = (GraphFragment) fragment;

        insertResource(graphFragment.getObject());
        insertResource(graphFragment.getSubject());
        //createEdge(fragment.getObject(), fragment.getSubject(), fragment.getPredicates());
    }

    @Override
    public Set<Triple> queryDatabase(Set<BGP> bgp, Set<Filter> filters) {
        return null;
    }

    /*
    private void createEdge(Resource object, Resource subject, List<Resource> predicates){

        try (Session session = driver.session())
        {
            @Override
            public String execute( Transaction tx )
            {
                StatementResult result = tx.run("CREATE (baeldung:Company {name:\"Baeldung\"}) " +
                                "-[:owns]-> (tesla:Car {make: 'tesla', model: 'modelX'})" +
                                "RETURN baeldung, tesla");
                return result.single().get( 0 ).asString();
            }
        }
    }

    private void queryNode(){

        Result result = graphDb.execute(
                "MATCH (company:Company)-[:owns]-> (car:Car)" +
                        "WHERE car.make='tesla' and car.model='modelX'" +
                        "RETURN company.name");
    }*/
}