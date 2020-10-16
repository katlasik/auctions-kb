package pl.sda.auctions.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Anonymous users should be able to see login screen")
    void getLogin() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("id=\"username\"")))
                .andExpect(content().string(containsString("id=\"password\"")));

    }

    @Test
    @DisplayName("Authenticated users should be redirected from login to welcome page")
    @WithMockUser(username = "name@gmail.com", password = "pass123")
    void getLoginAuthenticated() throws Exception {
        mockMvc.perform(get("/login")).andDo(print()).andExpect(redirectedUrl(""));

    }

    @Test
    @DisplayName("Anonymous users should be able to log in")
    @WithMockUser(username = "name@gmail.com", password = "pass123")
    void postLogin() throws Exception {

        mockMvc.perform(post("/login")
                .param("username", "admin@dummy.pl")
                .param("password", "pass123")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isFound());

    }

    @Test
    @DisplayName("Authenticated users should be able to log out")
    @WithMockUser(username = "name@gmail.com", password = "pass123")
    void logout() throws Exception {

        mockMvc.perform(get("/logout"))
                .andExpect(redirectedUrl("/"));

    }
}
