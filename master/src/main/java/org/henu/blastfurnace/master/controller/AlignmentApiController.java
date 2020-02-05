package org.henu.blastfurnace.master.controller;

import org.henu.blastfurnace.master.model.AlignmentEntry;
import org.henu.blastfurnace.master.model.AlignmentRequest;
import org.henu.blastfurnace.master.service.AlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Controller
public class AlignmentApiController implements AlignmentApi {
    private AlignmentService alignmentService;

    @Autowired
    public AlignmentApiController(AlignmentService alignmentService) {
        this.alignmentService = alignmentService;
    }

    @Override
    public ResponseEntity<List<AlignmentEntry>> align(AlignmentRequest request) throws ExecutionException, InterruptedException {
        return new ResponseEntity<>(alignmentService.callSlave(request), HttpStatus.OK);
    }
}
