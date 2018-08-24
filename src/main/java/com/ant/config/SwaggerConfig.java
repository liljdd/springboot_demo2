package com.ant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lilj
 * @date 18/08/24
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.ant.web"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Demo2 Swagger2 Test APIs")
                .description("API 描述")
                .licenseUrl("http://http://www.baidu.com")
                .contact(new Contact("lilj", "http://www.baidu.com", ""))
                .version("1.0")
                .build();
    }

    /*  restful 风格接口 注解描述：
        @Api：描述Controller
        @ApiIgnore：忽略该Controller，指不对当前类做扫描
        @ApiOperation：描述Controller类中的method接口
        @ApiParam：单个参数描述，与@ApiImplicitParam不同的是，他是写在参数左侧的。如（@ApiParam(name = "username",value = "用户名") String username）
        @ApiModel：描述POJO对象
        @ApiProperty：描述POJO对象中的属性值
        @ApiImplicitParam：描述单个入参信息
        @ApiImplicitParams：描述多个入参信息
        @ApiResponse：描述单个出参信息
        @ApiResponses：描述多个出参信息
        @ApiError：接口错误所返回的信息
     */
}
