package org.ufsc.rendezvous;

import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.insert.InsertExecutor;
import org.ufsc.rendezvous.insert.parser.TripleParser;
import org.ufsc.rendezvous.query.QueryExecutor;
import org.ufsc.rendezvous.query.parser.QueryParser;

public class Main {

    public static void main(String [] args){
    }

    private void insert(Triple triple){

        TripleParser parser = new TripleParser();
        InsertExecutor executor = new InsertExecutor();

        executor.execute(parser.parse(triple));
    }

    private void query(String query){

        QueryExecutor executor= new QueryExecutor();
        QueryParser parser = new QueryParser();

        executor.execute(parser.parse(query));
    }
}
