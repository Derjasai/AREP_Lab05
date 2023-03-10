package edu.eci.arep.app.loadBalancers.impl;

import edu.eci.arep.app.loadBalancers.LoadBalancer;

import java.util.ArrayList;
import java.util.List;

public class RoundRobin implements LoadBalancer {

    private static RoundRobin instance;

    private List<String> servers;
    private int actual;

    private RoundRobin(){
        actual = 0;
        servers = new ArrayList<>();
        servers.add("http://172.31.26.120:42000");
        servers.add("http://172.31.26.120:42001");
        servers.add("http://172.31.26.120:42002");
    }

    public static RoundRobin getInstance() {
        if(instance == null){
            instance = new RoundRobin();
        }
        return instance;
    }

    @Override
    public String getServer() {
        synchronized (servers){
            if(actual >= servers.size()){
                actual = 0;
            }
            String server = servers.get(actual);
            actual+=1;
            return server;
        }
    }
}
