package com.kust.edujunction;

import com.kust.edujunction.common.BaseTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EdujunctionApplicationTests extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() throws Exception {
        Assert.assertTrue(postgres.isCreated());
        Assert.assertTrue(postgres.isRunning());
    }
}
