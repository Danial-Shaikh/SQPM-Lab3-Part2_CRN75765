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
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // Test for "/add" endpoint, expecting a binary sum result
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "1011").param("operand2", "1001"))
                .andExpect(status().isOk())
                .andExpect(content().string("10100"));  // 11 + 9 = 20, binary result is 10100
    }

    // Test for "/add_json" endpoint, verifying JSON response
    @Test
    public void addJson() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "110").param("operand2", "11"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("1001"))  // 6 + 3 = 9 (1001)
                .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test for "/multiply" endpoint, expecting a binary product result
    @Test
    public void multiply() throws Exception {
        // First test case for multiply
        this.mvc.perform(get("/multiply").param("operand1", "11").param("operand2", "101"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));  // 3 * 5 = 15, binary result is 1111
    }

    @Test
    public void multiplySecondCase() throws Exception {
        // Second test case for multiply
        this.mvc.perform(get("/multiply").param("operand1", "100").param("operand2", "100"))
                .andExpect(status().isOk())
                .andExpect(content().string("10000"));  // 4 * 4 = 16, binary result is 10000
    }

    // Test for "/and" endpoint, expecting a binary AND operation result
    @Test
    public void and() throws Exception {
        // First test case for AND
        this.mvc.perform(get("/and").param("operand1", "1110").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(content().string("1010"));  // AND operation (1110 & 1011) = 1010 (decimal 10)
    }

    @Test
    public void andSecondCase() throws Exception {
        // Second test case for AND
        this.mvc.perform(get("/and").param("operand1", "1101").param("operand2", "1011"))
                .andExpect(status().isOk())
                .andExpect(content().string("1001"));  // AND operation (1101 & 1011) = 1001 (decimal 9)
    }

    // Test for "/or" endpoint, expecting a binary OR operation result
    @Test
    public void or() throws Exception {
        // First test case for OR
        this.mvc.perform(get("/or").param("operand1", "1001").param("operand2", "1101"))
                .andExpect(status().isOk())
                .andExpect(content().string("1101"));  // OR operation (1001 | 1101) = 1101 (decimal 13)
    }

    @Test
    public void orSecondCase() throws Exception {
        // Second test case for OR
        this.mvc.perform(get("/or").param("operand1", "1010").param("operand2", "0101"))
                .andExpect(status().isOk())
                .andExpect(content().string("1111"));  // OR operation (1010 | 0101) = 1111 (decimal 15)
    }
}
