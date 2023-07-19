package eureka.resourceservice.api;


import eureka.resourceservice.config.PostgresExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith({PostgresExtension.class})
public class ResourceControllerTest {
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp(WebApplicationContext webApplicationContext) {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//
//    @Test
//    void whenSaveResourceCalled_shouldSaveResource() throws Exception {
//        File songFile = ResourceUtils.getFile("classpath:files/test.mp3");
//        System.out.println(songFile);
//        MockMultipartFile multipartFile = new MockMultipartFile(
//                "multipartFile",
//                songFile.getName(),
//                "audio/mpeg",
//                Files.readAllBytes(songFile.toPath())
//        );


//        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/v1/resources")
//                        .file(multipartFile))
//                        .andExpect(status().isCreated());
//    }
}
