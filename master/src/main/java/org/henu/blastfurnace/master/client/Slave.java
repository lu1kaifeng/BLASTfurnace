package org.henu.blastfurnace.master.client;

import feign.Headers;
import feign.RequestLine;
import org.henu.blastfurnace.master.model.AlignmentEntry;
import org.henu.blastfurnace.master.model.AlignmentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;
@FeignClient(name = "slave")
public interface Slave {
    @RequestLine("POST /align")
    @Headers("Content-Type: application/json")
    List<AlignmentEntry> align(AlignmentRequest request);
}
