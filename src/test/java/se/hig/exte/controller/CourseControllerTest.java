package se.hig.exte.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest
//@WebAppConfiguration
//@ContextConfiguration(classes = WebConfig.class)
class CourseControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@BeforeEach
	public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(documentationConfiguration(restDocumentation)).build();
	}

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

	@Test
	public void test() throws Exception {
		this.mockMvc.perform(get("/api/course/1").accept(MediaType.ALL))
				.andExpect(status().isOk())
				.andDo(document("index"));
	}

}
