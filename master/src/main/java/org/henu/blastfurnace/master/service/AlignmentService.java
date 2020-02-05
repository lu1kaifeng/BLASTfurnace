package org.henu.blastfurnace.master.service;

import org.henu.blastfurnace.master.config.SlaveCaller;
import org.henu.blastfurnace.master.model.AlignmentEntry;
import org.henu.blastfurnace.master.model.AlignmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class AlignmentService {
    private SlaveCaller caller;

    @Autowired
    public AlignmentService(SlaveCaller caller) {
        this.caller = caller;
    }

    public List<AlignmentEntry> callSlave(AlignmentRequest request) throws ExecutionException, InterruptedException {
        return caller.callAll(request);
    }
}
