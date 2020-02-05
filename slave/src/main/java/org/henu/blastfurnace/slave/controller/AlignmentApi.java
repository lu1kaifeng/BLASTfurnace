package org.henu.blastfurnace.slave.controller;

import org.biojava.nbio.structure.align.pairwise.AlignmentResult;
import org.henu.blastfurnace.slave.model.AlignmentEntry;
import org.henu.blastfurnace.slave.model.AlignmentRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface AlignmentApi {
    @RequestMapping(value = "/align",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AlignmentEntry>> align(@RequestBody AlignmentRequest request);
}
