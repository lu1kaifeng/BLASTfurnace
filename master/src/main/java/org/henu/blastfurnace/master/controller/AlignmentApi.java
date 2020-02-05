package org.henu.blastfurnace.master.controller;

import org.henu.blastfurnace.master.model.AlignmentEntry;
import org.henu.blastfurnace.master.model.AlignmentRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface AlignmentApi {
    @RequestMapping(value = "/align", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<AlignmentEntry>> align(@RequestBody AlignmentRequest request) throws ExecutionException, InterruptedException;
}
