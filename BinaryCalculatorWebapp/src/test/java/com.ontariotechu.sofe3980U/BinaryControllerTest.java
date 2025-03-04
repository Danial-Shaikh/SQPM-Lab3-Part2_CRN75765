package com.ontariotechu.sofe3980U;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.BDDMockito.*;

/**
 * Test class for BinaryController, using Spring Boot's MockMvc for testing HTTP requests.
 */
@WebMvcTest(BinaryController.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc; // Simulates HTTP requests to the controller

    @MockBean
    private BinaryService binaryService; // Mocking the service layer for isolation

    /**
     * Test default GET request to ensure the calculator page loads correctly.
     */
    @Test
    public void getDefault() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", ""))
                .andExpect(model().attribute("operand1Focused", false));
    }

    /**
     * Test GET request with a parameter to check if the calculator correctly sets operand1.
     */
    @Test
    public void getParameter() throws Exception {
        mvc.perform(get("/").param("operand1", "1010"))
                .andExpect(status().isOk())
                .andExpect(view().name("calculator"))
                .andExpect(model().attribute("operand1", "1010"))
                .andExpect(model().attribute("operand1Focused", true));
    }

    /**
     * Test addition of two binary numbers.
     * 1101 + 1010 = 10111
     */
    @Test
    public void postAddition() throws Exception {
        given(binaryService.calculate("1101", "+", "1010")).willReturn("10111");

        mvc.perform(post("/").param("operand1", "1101")
                        .param("operator", "+")
                        .param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10111"))
                .andExpect(model().attribute("operand1", "1101"));
    }

    /**
     * Test multiplication of two binary numbers.
     * 11 * 110 = 10010
     */
    @Test
    public void postMultiply() throws Exception {
        given(binaryService.calculate("11", "*", "110")).willReturn("10010");

        mvc.perform(post("/").param("operand1", "11")
                        .param("operator", "*")
                        .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10010"))
                .andExpect(model().attribute("operand1", "11"));
    }

    /**
     * Additional test for multiplication.
     * 101 * 10 = 1010
     */
    @Test
    public void postMultiplyAdditional() throws Exception {
        given(binaryService.calculate("101", "*", "10")).willReturn("1010");

        mvc.perform(post("/").param("operand1", "101")
                        .param("operator", "*")
                        .param("operand2", "10"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010"))
                .andExpect(model().attribute("operand1", "101"));
    }

    /**
     * Test bitwise AND operation.
     * 1110 & 1011 = 1010
     */
    @Test
    public void postAnd() throws Exception {
        given(binaryService.calculate("1110", "&", "1011")).willReturn("1010");

        mvc.perform(post("/").param("operand1", "1110")
                        .param("operator", "&")
                        .param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010"))
                .andExpect(model().attribute("operand1", "1110"));
    }

    /**
     * Additional test for bitwise AND operation.
     * 1101 & 1001 = 1001
     */
    @Test
    public void postAndAdditional() throws Exception {
        given(binaryService.calculate("1101", "&", "1001")).willReturn("1001");

        mvc.perform(post("/").param("operand1", "1101")
                        .param("operator", "&")
                        .param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1001"))
                .andExpect(model().attribute("operand1", "1101"));
    }

    /**
     * Test bitwise OR operation.
     * 1001 | 1100 = 1101
     */
    @Test
    public void postOr() throws Exception {
        given(binaryService.calculate("1001", "|", "1100")).willReturn("1101");

        mvc.perform(post("/").param("operand1", "1001")
                        .param("operator", "|")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1101"))
                .andExpect(model().attribute("operand1", "1001"));
    }

    /**
     * Additional test for bitwise OR operation.
     * 1010 | 0011 = 1011
     */
    @Test
    public void postOrAdditional() throws Exception {
        given(binaryService.calculate("1010", "|", "0011")).willReturn("1011");

        mvc.perform(post("/").param("operand1", "1010")
                        .param("operator", "|")
                        .param("operand2", "0011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1011"))
                .andExpect(model().attribute("operand1", "1010"));
    }
}
