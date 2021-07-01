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
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Integration tests for MessageServlet class
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@WebAppConfiguration
public class MessageServletTest {

    /** A web application configuration */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /** Main entry point for server-side Spring MVC test support */
    private MockMvc mockMvc;

    /** MockHttpSession used in tests */
    private MockHttpSession session;

    /** Initializes mockMvc and User objects before every test */
    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        User user = new User("firstName", "lastName", "patronymic", "username", "password", "10/10/2010");
        this.session = new MockHttpSession();
        session.setAttribute("currentUser", user);
    }

    /**
     * Checks result view while performing Get request to servlet
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkURL() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/message").session(session);
        this.mockMvc.perform(builder).andExpect(view().name("/views/message.jsp"));
    }

    /**
     * Checks sending messages functionality (success and fail scenarios)
     *
     * @throws Exception in case of errors
     */
    @Test
    public void checkSending() throws Exception {
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/message").session(session);
        mockMvc.perform((builder).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("recipient", "test"),
                        new BasicNameValuePair("text", "test")
                ))))).andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("messageSent","true"));

        mockMvc.perform((builder).contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                        new BasicNameValuePair("recipient", ""),
                        new BasicNameValuePair("text", null)
                ))))).andDo(print()).andExpect(MockMvcResultMatchers.model().attribute("emptyField","true"));
    }
}