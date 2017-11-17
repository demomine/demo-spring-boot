package com.lance.demo.springboot.config;

import java.util.List;
import com.fasterxml.classmate.TypeResolver;
import com.sun.net.httpserver.BasicAuthenticator;
import io.swagger.annotations.Api;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.swagger.web.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static com.google.common.collect.Lists.newArrayList;
import static springfox.documentation.schema.AlternateTypeRules.newRule;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    private final TypeResolver typeResolver;

    @Autowired
    public SwaggerConfig(TypeResolver typeResolver) {
        this.typeResolver = typeResolver;
    }

    @Bean
    // @ConditionalOnProperty(value = SwitchProperties.SWAGGER_ENABLED, havingValue = "true")
    public Docket swaggerBean() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .pathMapping("/")
                /*.directModelSubstitute(LocalDate.class,
                        String.class)*/
                // .genericModelSubstitutes(ResponseEntity.class)
                /*.alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))*/
                .useDefaultResponseMessages(false)
                /*.globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder()
                                .code(500)
                                .message("500 message")
                                .responseModel(new ModelRef("Error"))
                                .build()))*/
                .securitySchemes(newArrayList(apiKey()))
                .securityContexts(newArrayList(securityContext()))
                // .enableUrlTemplating(true)
               /* .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                .name("someGlobalParameter")
                                .description("Description of someGlobalParameter")
                                .modelRef(new ModelRef("string"))
                                .parameterType("query")
                                .required(true)
                                .build()))*/
                // .tags(new Tag("lance", "All apis relating to perdonare"))
                // .additionalModels(typeResolver.resolve(AdditionalModel.class))
                ;
    }

/*    @Bean
    SecurityConfiguration security() {
        return new SecurityConfiguration(
                "test-app-client-id",
                "test-app-client-secret",
                "test-app-realm",
                "test-app",
                "123456",//key
                ApiKeyVehicle.HEADER,
                "Authentication",
                "," *//*scope separator*//*);
    }*/

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(
                "none",// url
                "list",       // docExpansion          => none | list
                "alpha",      // apiSorter             => alpha
                "schema",     // defaultModelRendering => schema
                UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS,
                false,        // enableJsonEditor      => true | false
                true,         // showRequestHeaders    => true | false
                60000L);      // requestTimeout => in milliseconds, defaults to null (uses jquery xh timeout)
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("demo-spring-boot API")
                .description("this api for test only")
                .termsOfServiceUrl("http://www.immerser.net")
                .license("Lance License Version 1.0")
                .licenseUrl("https://github.com/demomine")
                .version("1.0")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("aaa", "Authentication", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("/anyPath.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = new AuthorizationScope("global", "accessEverything");
        return newArrayList(new SecurityReference("aaa", authorizationScopes));
    }


}
