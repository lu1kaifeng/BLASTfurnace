package org.henu.blastfurnace.master.config;

import org.henu.blastfurnace.master.client.Slave;
import org.henu.blastfurnace.master.model.AlignmentEntry;
import org.henu.blastfurnace.master.model.AlignmentRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SlaveCaller {
    private Map<Slave, ExecutorService> slaves = new HashMap<>();

    public SlaveCaller(String[] slaveURIs, String path) {
        for (String uri : slaveURIs) {
            slaves.put(new Slave(uri, path), Executors.newCachedThreadPool());
        }
    }

    private Future<List<AlignmentEntry>> call(AlignmentRequest request, Map.Entry<Slave, ExecutorService> slave) {
        return slave.getValue().submit(() -> slave.getKey().align(request));
    }

    public List<AlignmentEntry> callAll(AlignmentRequest request) throws ExecutionException, InterruptedException {
        List<AlignmentEntry> entries = new ArrayList<>();
        List<Future<List<AlignmentEntry>>> futures = new ArrayList<>();
        for(Map.Entry<Slave,ExecutorService> slave : slaves.entrySet()){
            futures.add(this.call(request,slave));
        }
        for(Future<List<AlignmentEntry>> future : futures){
            entries.addAll(future.get());
        }
        return entries;
    }
}
