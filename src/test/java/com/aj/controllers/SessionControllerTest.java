// package com.aj.controllers;
//
// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MockMvcBuilder;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
// @WebMvcTest(controllers = { SessionController.class })
// public class SessionControllerTest {
//
//     @Autowired
//     private MockMvc mockMvc;
//
//     @Test
//     public void getIndex() throws Exception {
//         mockMvc.perform(get("/"))
//                 .andExpect(view().name("index"));
//     }
// }
