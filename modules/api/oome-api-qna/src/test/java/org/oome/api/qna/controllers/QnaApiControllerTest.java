package org.oome.api.qna.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.oome.api.qna.controllers.QnaApiController;
import org.oome.api.qna.dto.req.AnswerSaveReqDto;
import org.oome.api.qna.dto.req.QuestionSaveReqDto;
import org.oome.api.qna.services.QnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(QnaApiController.class)
public class QnaApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSaveQuestion() throws Exception {
        QuestionSaveReqDto questionDto = null;

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/qna/question")
                        .content(asJsonString(questionDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));
    }

    @Test
    public void testSaveAnswer() throws Exception {
        AnswerSaveReqDto answerDto = null;

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/qna/question/1/answer")
                        .content(asJsonString(answerDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("1"));
    }

    private String asJsonString(Object obj) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
