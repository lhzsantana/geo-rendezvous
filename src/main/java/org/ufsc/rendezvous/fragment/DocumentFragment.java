package org.ufsc.rendezvous.fragment;

import org.bson.Document;
import org.ufsc.rendezvous.concepts.Resource;
import org.ufsc.rendezvous.concepts.Triple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DocumentFragment extends Fragment{

    private Triple coreTriple;;
    private Document document;

    public Triple getCoreTriple() {
        return coreTriple;
    }

    public void setCoreTriple(Triple coreTriple) {
        this.coreTriple = coreTriple;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

}
