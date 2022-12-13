package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class CustomersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCustomerByNameHttpRequestWithStatus200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/customer/{name}", "DONALD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("DONALD"))
                .andReturn();
    }

    @Test
    public void getCustomerByNameHttpRequestWithStatus404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/customer/{name}", "jura"))
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$").value("We do not have that customer in our database!"))
                .andReturn();
    }

    @Test
    public void getCustomerByNameHttpWrongRequestWithStatus404() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{name}", "jura"))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }
}
