package com.ontariotechu.sofe3980U;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

    /**
     * Test default GET request to ensure the calculator page loads correctly.
     */
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))
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
        this.mvc.perform(get("/").param("operand1", "1010"))
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
    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1101")
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
    public void postMultiplyParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1", "11")
                        .param("operator", "*")
                        .param("operand2", "110"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "10010"))
                .andExpect(model().attribute("operand1", "11"));
    }

    /**
     * Test bitwise AND operation.
     * 1110 & 1011 = 1010
     */
    @Test
    public void postAndParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1110")
                        .param("operator", "&")
                        .param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1010"))
                .andExpect(model().attribute("operand1", "1110"));
    }

    /**
     * Test bitwise OR operation.
     * 1001 | 1100 = 1101
     */
    @Test
    public void postOrParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1", "1001")
                        .param("operator", "|")
                        .param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(view().name("result"))
                .andExpect(model().attribute("result", "1101"))
                .andExpect(model().attribute("operand1", "1001"));
    }
}