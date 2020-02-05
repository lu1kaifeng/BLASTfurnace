package org.henu.blastfurnace.slave.controller;

import org.henu.blastfurnace.slave.model.AlignmentEntry;
import org.henu.blastfurnace.slave.model.AlignmentRequest;
import org.henu.blastfurnace.slave.service.ConcurrentAlignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AlignmentApiController implements AlignmentApi {
    private ConcurrentAlignmentService alignmentService;
    @Autowired
    public AlignmentApiController(ConcurrentAlignmentService alignmentService) {
        this.alignmentService = alignmentService;
    }

    @Override
    public ResponseEntity<List<AlignmentEntry>> align(AlignmentRequest request) {
        return new ResponseEntity<>(alignmentService.align(request), HttpStatus.OK);
    }
}
