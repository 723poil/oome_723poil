package org.oome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.oome.api.qna.controllers.QnaApiController;
import org.oome.api.qna.dto.req.QuestionSaveReqDto;
import org.oome.api.qna.services.QnaService;
import org.oome.entity.question.Question;
import org.oome.entity.question.repository.QuestionJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(properties = {
        "spring.profiles.active=local",
})
public class SimpleTest {

    private MockMvc mockMvc;

    @MockBean
    private QnaService qnaService;

    @Autowired
    private QnaApiController qnaApiController;

    @Autowired
    private QuestionJpaRepository questionJpaRepository;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(qnaApiController).build();
    }

    @Test
    public void saveQuestionTest() throws Exception {
    QuestionSaveReqDto requestDto = QuestionSaveReqDto
            .builder()
            .title("test title")
            .contents("test contents")
            .build();

        Long savedId = 1L;

        ResultActions result = mockMvc.perform(post("/api/v1/qna/question")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(requestDto)));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").value(savedId));

        Question entity = questionJpaRepository.findById(savedId).orElseThrow(IllegalArgumentException::new);

        assertThat(entity.getCreater().getUsername()).isEqualTo("admin");

    }
}
