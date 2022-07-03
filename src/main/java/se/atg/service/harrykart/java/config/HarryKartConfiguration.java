package se.atg.service.harrykart.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static se.atg.service.harrykart.java.utils.Constants.TERMS_URL;

@Configuration
@EnableSwagger2
public class HarryKartConfiguration {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("se.atg.service.harrykart"))
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Harry Kart Racing Service",
                "Harry Kart horse racing game service.",
                "1.0",
                TERMS_URL,
                new Contact("Team ATG", TERMS_URL, "someMail@atg.se"),
                "ATG SE License",
                TERMS_URL,
                Collections.emptyList()
        );
    }
}
