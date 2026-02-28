package aulasenora.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LoginViewController.class)
class LoginViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Redirects admin user to admin dashboard")
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void dashboardAdmin() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-admin"))
                .andExpect(model().attribute("rol", "ADMIN"));
    }

    @Test
    @DisplayName("Redirects volunteer user to volunteer dashboard")
    @WithMockUser(username = "vol", roles = {"VOLUNTARIO"})
    void dashboardVoluntario() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-voluntario"))
                .andExpect(model().attribute("rol", "VOLUNTARIO"));
    }

    @Test
    @DisplayName("Redirects student user to student dashboard")
    @WithMockUser(username = "est", roles = {"ESTUDIANTE"})
    void dashboardEstudiante() throws Exception {
        mockMvc.perform(get("/dashboard"))
                .andExpect(status().isOk())
                .andExpect(view().name("dashboard-estudiante"))
                .andExpect(model().attribute("rol", "ESTUDIANTE"));
    }
}
