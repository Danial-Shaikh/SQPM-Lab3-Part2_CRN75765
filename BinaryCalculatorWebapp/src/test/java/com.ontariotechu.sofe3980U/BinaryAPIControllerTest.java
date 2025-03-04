package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // Test for "/add" (string result)
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1011").param("operand2", "1101"))
                .andExpect(status().isOk())
                .andExpect(content().string("11000"));  // 11 + 13 = 24, binary result is 11000
    }

    // Test for "/add_json" (JSON result)
    @Test
    public void addJson() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "1011").param("operand2", "1101"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(1011))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1101))
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("11000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test for "/multiply" (string result)
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "110").param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(content().string("10010"));  // 6 * 3 = 18, binary result is 10010
    }

    // Second test case for "/multiply"
    @Test
    public void multiply2() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101").param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(content().string("11001"));  // 5 * 5 = 25, binary result is 11001
    }

    // Test for "/and" (string result)
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1010").param("operand2", "1101"))
                .andExpect(status().isOk())
                .andExpect(content().string("1000"));  // 10 AND 13 = 8, binary result is 1000
    }

    // Second test case for "/and"
    @Test
    public void and2() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1111").param("operand2", "1010"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));  // 15 AND 10 = 10, binary result is 1010
    }

    // Test for "/or" (string result)
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "1100"))
                .andExpect(status().isOk())
                .andExpect(content().string("1110"));  // 10 OR 12 = 14, binary result is 1110
    }

    // Second test case  for "/or"
    @Test
    public void or2() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1001").param("operand2", "0110"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));  // 9 OR 6 = 15, binary result is 1111
    }
}