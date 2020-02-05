package org.henu.blastfurnace.master;

import org.henu.blastfurnace.master.config.SlaveCaller;
import org.henu.blastfurnace.master.model.AlignmentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

@SpringBootTest
class MasterApplicationTests {
    @Test
    void contextLoads() throws ExecutionException, InterruptedException {
    }
}
