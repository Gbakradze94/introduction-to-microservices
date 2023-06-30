package org.resourceservice.api;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.resourceservice.config.PostgresExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({PostgresExtension.class})
public class ResourceControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext) {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void whenSaveResourceCalled_shouldSaveResource() throws Exception {
        File songFile = ResourceUtils.getFile("classpath:files/mpthreetest.mp3");
        System.out.println(songFile);
        MockMultipartFile multipartFile = new MockMultipartFile(
                "multipartFile",
                songFile.getName(),
                "audio/mpeg",
                Files.readAllBytes(songFile.toPath())
        );

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/resources")
                        .file(multipartFile))
                        .andExpect(status().isCreated());
    }
}
