package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import storage.api.IMessageRepository;
import storage.api.IUserRepository;
import view.AuthService;
import view.MessageService;
import view.UserService;
import view.api.IAuthService;
import view.api.IMessageService;
import view.api.IUserService;

/**
 * Defining configuration for user's non-web components
 *
 * @author Vadim Rataiko
 * @version 1.0
 */
@Configuration
@ComponentScan("config")
public class RootConfig {

    /**
     * Configuration for IMessageService interface
     *
     * @param repository instance of IMessageRepository interface
     * @return object which implements IMessageService interface
     */
    @Bean
    public IMessageService getMessageService(IMessageRepository repository) {
        return new MessageService(repository);
    }

    /**
     * Configuration for IUserService interface
     *
     * @param repository instance of IUserRepository interface
     * @return object which implements IUserService interface
     */
    @Bean
    public IUserService getUserService(IUserRepository repository) {
        return new UserService(repository);
    }

    /**
     * Configuration for IAuthService interface
     *
     * @param userService instance of IUserService interface
     * @return object which implements IAuthService interface
     */
    @Bean
    public IAuthService getAuthService(IUserService userService) {
        return new AuthService(userService);
    }
}