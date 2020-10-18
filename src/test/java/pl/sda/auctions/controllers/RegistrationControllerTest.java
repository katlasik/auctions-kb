package pl.sda.auctions.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import pl.sda.auctions.dto.UserRegistrationDTO;
import pl.sda.auctions.model.Role;
import pl.sda.auctions.model.User;
import pl.sda.auctions.repository.UserRepository;
import pl.sda.auctions.services.RegistrationService;
import pl.sda.auctions.services.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("Anonymous users should be able to see registration screen")
    void getRegistration() throws Exception {
        mockMvc.perform(get("/registration")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Zarejestruj się")));
    }

    @Test
    @DisplayName("User created with correct params should be registrated")
    void postRegisterUser() throws Exception{
        var user = new User(null, "test@test.pl", "test123", "test", true , Role.USER);

        when(userRepository.save(user)).thenReturn(user);
        when(userService.checkIfUserExistsEmail("test@test.pl")).thenReturn(false);
        when(userService.checkIfUserExistsName("test")).thenReturn(false);

        mockMvc.perform(post("/registration")
                .param("name", "test")
                .param("email", "test@test.pl")
                .param("password", "test123")
                .param("passwordRepeat", "test123")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk());


        verify(userService, times(1)).checkIfUserExistsEmail("test@test.pl");
        verify(userService, times(1)).checkIfUserExistsName("test");
    }
}