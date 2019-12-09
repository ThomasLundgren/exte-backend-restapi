package se.hig.exte.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
//@WebAppConfiguration
//@ContextConfiguration(classes = WebConfig.class)
class CourseControllerTest {

//	private MockMvc mockMvc;
//
//	@Autowired
//	private WebApplicationContext context;
//
//	@BeforeEach
//	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.apply(documentationConfiguration(restDocumentation)).build();
//	}
//
//	@Test
//	public void getCourse_withIDNotInDb_returns200() {
//		try {
//			mockMvc.perform(get("/api/course/1").accept(MediaType.ALL))
//				.andExpect(status().isOk())
//				.andDo(document("index"));
//		} catch (Exception e) {
//			fail();
//		}
//	}
//
//	@Test
//	public void test() throws Exception {
//		this.mockMvc.perform(get("/api/course/1").accept(MediaType.ALL))
//				.andExpect(status().isOk())
//				.andDo(document("index"));
//	}

}
