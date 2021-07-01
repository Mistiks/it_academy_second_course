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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import storage.api.IUserRepository;
import java.util.Arrays;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for RegisterServlet class
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
public class RegisterServletTest {

    /** A web application configuration */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** Main entry point for server-side Spring MVC test support */
    private MockMvc mockMvc;

    /** Test storage for User objects */
    @MockBean
    private IUserRepository repository;

    /** Initializes mockMvc object before every test */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    /**
     * Checks result view while performing Get request to servlet
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkURL() throws Exception {
        this.mockMvc.perform(get("/signUp")).andDo(print())
                .andExpect(view().name("/views/signUp.jsp"));
    }

    /**
     * Checks scenario with empty field with first name
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkNullFirstName() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", null),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks scenario with empty field with last name
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkNullLastName() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", null),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks scenario with empty field with patronymic
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkNullPatronymic() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", null),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks scenario with empty field with username
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkNullUsername() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", null),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks scenario with empty field with password
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkEmptyPassword() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", ""),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks scenario with empty field with birth date
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkNullBirthDate() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", null)
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks registration scenario with used username
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkExistingUsername() throws Exception {
        User user = new User("firstName", "lastName", "patronymic", "username", "password", "10/10/2010");
        when(this.repository.getByUsername("username")).thenReturn(user);
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("fail_sign_up","true"));
    }

    /**
     * Checks success registration scenario
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkSuccessSignUp() throws Exception {
        ResultActions resultActions = mockMvc.perform(post("/signUp")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("firstName", "firstName"),
                        new BasicNameValuePair("lastName", "lastName"),
                        new BasicNameValuePair("patronymic", "patronymic"),
                        new BasicNameValuePair("username", "username"),
                        new BasicNameValuePair("password", "password"),
                        new BasicNameValuePair("dateOfBirth", "10/10/2010")
                )))));

        resultActions.andDo(print()).andExpect(view().name("redirect:/signIn"));
    }

}