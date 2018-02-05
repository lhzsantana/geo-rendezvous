package org.ufsc.rendezvous.cache.impl;

import org.ufsc.rendezvous.cache.CacheAccess;
import org.ufsc.rendezvous.concepts.BGP;
import org.ufsc.rendezvous.concepts.Triple;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

public class RedisAccess implements CacheAccess{

    private static Jedis jedis;


    private Jedis connect(){

        if(jedis==null) {
            jedis = new Jedis("localhost");
        }

        return jedis;
    }

    @Override
    public void put(Triple triple)  {

        connect().set(
                generateKey(
                        triple.getSubject().getValue(),
                        triple.getPredicate().getValue(),
                        triple.getObject().getValue()
                ), triple.toString());
    }

    @Override
    public Set<Triple> get(BGP bgp) {
        String value = connect().get(generateKey(bgp.getSubject(), bgp.getPredicate(), bgp.getObject()));

        return new HashSet<>();
    }

    private String generateKey(String subject, String predicate, String object){
        return "";
    }
}
