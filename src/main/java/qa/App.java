package qa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import qa.domain.Post;
import qa.domain.Token;
import qa.repository.PostRepository;
import qa.service.PostService;

@SpringBootApplication(exclude = {
    SecurityAutoConfiguration.class,
    RepositoryRestMvcAutoConfiguration.class
})
@EnableTransactionManagement
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
public class App {

    public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

    @Bean
    @Transactional
    public CommandLineRunner loadData(
            PostRepository postRepository
    ) {
        return (args) -> {


        };
    }
}
