package com.rest.API.swagger;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        public static final String AUTHORIZATION_HEADER = "Authorization";

        @Bean
        public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                        .securityContexts(securityContexts())
                        .securitySchemes(Arrays.asList(apiKey()))
                        .select()
                        .apis(RequestHandlerSelectors.basePackage("com.rest.API"))
                        .paths(PathSelectors.any())
                        .build();
        }

        private ApiKey apiKey() {
                return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
        }

        private List<SecurityContext> securityContexts() {
                List<SecurityContext> securityContexts = new ArrayList<>();
                List<HttpMethod> postPutDeleteMethods = Arrays.asList(HttpMethod.PUT,HttpMethod.DELETE,HttpMethod.POST);
                List<HttpMethod> postPutDeleteGetMethods = Arrays.asList(HttpMethod.PUT,HttpMethod.DELETE,HttpMethod.POST,HttpMethod.GET);
                securityContexts.add( SecurityContext.builder().securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("/ingredient.*"))
                        .forHttpMethods(Predicates.in(postPutDeleteMethods))
                        .build()
                );
                securityContexts.add( SecurityContext.builder().securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("/admin.*"))
                        .forHttpMethods(Predicates.in(postPutDeleteGetMethods))
                        .build()
                );
                securityContexts.add( SecurityContext.builder().securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("/product.*"))
                        .forHttpMethods(Predicates.in(postPutDeleteGetMethods))
                        .build()
                );
                securityContexts.add( SecurityContext.builder().securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("/user.*"))
                        .forHttpMethods(Predicates.in(postPutDeleteGetMethods))
                        .build()
                );
                return securityContexts;
        }

        private List<SecurityReference> defaultAuth() {
                AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
                AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
                authorizationScopes[0] = authorizationScope;
                return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
        }
}
