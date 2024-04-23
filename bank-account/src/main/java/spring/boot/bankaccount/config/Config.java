package spring.boot.bankaccount.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Config {

  /*
   * To use Swagger just type in the browser the port of the local host (in this
   * code the server port is 8080) and type: /swagger-ui/  Like: " localhost:8080/swagger-ui/ "
   * The reason for this was provided in this question:
   * https://stackoverflow.com/questions/46151540/added-springfox-swagger-ui-and-its-not-working-what-am-i-missing
   */

  @Bean
  public static Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select() 
        /** Be careful to type the correct main package name */
        .apis(RequestHandlerSelectors.basePackage("spring.boot.bankaccount"))
        .build()
        .apiInfo(metaData());
  }

  private static ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title(" Banking Account REST API =D ")
        .description("Spring Boot REST API for Banl Account")
        .version("1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
        .build();
  }

}
