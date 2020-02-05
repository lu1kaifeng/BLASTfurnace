package org.henu.blastfurnace.datasplit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DatasplitApplicationTests {
    @Autowired
    DataPrepService dataPrepService;
    @Test
    void contextLoads() throws IOException {
        dataPrepService.prepData(4);
    }

}