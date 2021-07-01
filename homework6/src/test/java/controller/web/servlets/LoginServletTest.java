package controller.web.servlets;

import config.RootConfig;
import model.User;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import storage.api.IUserRepository;
import javax.servlet.ServletContext;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Integration tests for LoginServlet class
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
public class LoginServletTest {

     /** A web application configuration */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** Main entry point for server-side Spring MVC test support */
    private MockMvc mockMvc;

    /** Test storage for User objects */
    @MockBean
    private IUserRepository repository;

    /** Initializes mockMvc and User objects before every test */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        User user = new User("firstName", "lastName", "patronymic", "username", "password", "10/10/2010");
        when(repository.getByUsername("username")).thenReturn(user);
    }

    /** Verifies test configuration */
    @Test
    public void checkServletCall() {
        ServletContext servletContext = webApplicationContext.getServletContext();

        assertNotNull(servletContext);
        assertTrue(servletContext instanceof MockServletContext);
        assertNotNull(webApplicationContext.getBean("loginServlet"));
    }

    /**
     * Checks result view while performing Get request to servlet
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkURL() throws Exception {
        this.mockMvc.perform(get("/signIn")).andDo(print())
                .andExpect(view().name("/views/signIn.jsp"));
    }

    /**
     * Checks all fail login scenarios
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkLoginFail() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signIn")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", "me"),
                        new BasicNameValuePair("password", "secret")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_in","true"));

        resultActions = mockMvc.perform(post("/signIn")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "secret")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_in","true"));
    }

    /**
     * Checks success login scenario
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkLoginSuccess() throws Exception {
        mockMvc.perform(post("/signIn")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password")
                ))))).andDo(print()).andExpect(view().name("redirect:/profile"));
    }
}