package org.ufsc.rendezvous.insert.parser;

import org.ufsc.rendezvous.concepts.Triple;
import org.ufsc.rendezvous.insert.InsertPlan;

public class TripleParser {

    public InsertPlan parse(Triple triple){

        InsertPlan insertPlan = new InsertPlan();
        insertPlan.setTriple(triple);

        return insertPlan;
    }
}
