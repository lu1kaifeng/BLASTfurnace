package org.henu.blastfurnace.datasplit;

import org.biojava.bio.BioException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DatasplitApplicationTests {
    @Autowired
    DataImportService importService;
    @Autowired
    DataPrepService prepService;

    @Test
    void contextLoads() throws IOException, BioException {
        prepService.prepDataPrecise(4, "ATCACACACACACACATCTCTCTCTCTCATCTCTCTCTCATCTCTCTCTCATCTCTCTCTTGTGT");
    }

}
