package com.south.elasticsearch.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * knife4j的配置类
 */
@EnableSwagger2WebMvc
@Configuration
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jConfig {

    /**
     * 这里配置swagger扫描的包
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors
                        //注意 这里写上 控制器的包的路径 注意
                        .basePackage("com.south.elasticsearch.controller"))
                .paths(PathSelectors.any()).build();
    }
    /**
     * 这里配置swagger对外提供服务的端口
     * @return
     */
    private ApiInfo apiInfo() {
        //主页的标题
        return new ApiInfoBuilder().title("elasticsearch接口")
                //简介的描述
                .description("elasticsearch接口restful风格接口")
                //服务的Url地址
                .termsOfServiceUrl("http://127.0.0.1:8080/doc.html")
                //版本
                .version("1.0").build();
    }
}
