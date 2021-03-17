package rmntw.rgmapnumbertowordjava.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
  private static final int SUCCESS_CODE = 200;
  private static final int RESOURCE_NOT_FOUND_CODE = 404;
  private static final int ALL_FAILURE_CODE = 500;
  private final ServletContext servletContext;
  @Value("${application.name}")
  private String applicationName;
  @Value("${application.apiVersion}")
  private String applicationVersion;

  @Autowired
  public SwaggerConfig(ServletContext servletContext) {
    this.servletContext = servletContext;
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(SwaggerExpose.class))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo())
        .useDefaultResponseMessages(false)
        .globalResponseMessage(RequestMethod.GET, globalResponses())
        .pathProvider(
            new RelativePathProvider(servletContext) {
              @Override
              public String getApplicationBasePath() {
                return "";
              }
            });
  }

  private ApiInfo apiInfo() {
    return new ApiInfo(
        applicationName,
        "List of available API",
        applicationVersion,
        null,
        null,
        null,
        null,
        Collections.emptyList());
  }

  private List<ResponseMessage> globalResponses() {
    final List<ResponseMessage> globalResponses =
        Arrays.<ResponseMessage>asList(
            new ResponseMessageBuilder().code(SUCCESS_CODE).message("OK").build(),
            new ResponseMessageBuilder()
                .code(RESOURCE_NOT_FOUND_CODE)
                .message("Bad Request")
                .build(),
            new ResponseMessageBuilder().code(ALL_FAILURE_CODE).message("Internal Error").build());
    return globalResponses;
  }
}
