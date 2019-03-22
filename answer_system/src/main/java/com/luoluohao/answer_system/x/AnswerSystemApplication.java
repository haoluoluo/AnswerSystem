package com.luoluohao.answer_system.x;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @Description 应用程序启动类
 * <p>
 * 打包: WAR（第8步）[2018-09-24 09:49:43]
 * 修改程序启动入口类：
 * public class IwillApplication extends SpringBootServletInitializer {
 * @Override protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
 * return builder.sources(IwillApplication.class);
 * }
 * ...
 * }
 * <p>
 * 打包: WAR（第9步）[2018-09-24 09:50:01]
 * IDEA中找到【菜单Build】->【子菜单Build Artifacts...】->点击弹出菜单【Build Artifact】->选择【菜单iwill.zt-service:war】->选择【自惨淡Build】
 * 这样，利用IDEA将SpringBoot的项目打包成war文件。
 * <p>
 * 打包: WAR（第10步）[2018-09-24 09:50:01]
 * 在项目工程目录./target/下，找到iwill-0.0.1-SNAPSHOT.war包文件，部署到tomcat对应的Service目录。
 * <p>
 * 打包: WAR（第11步）[2018-09-24 09:51:16]
 * 启动tomcat，即可自动解压部署。最后在浏览器中输入
 * http://localhost:[端口号]/[打包项目名]/
 * @Param
 * @Return
 */
@MapperScan("com.luoluohao.answer_system.**.dao.mapper")
@SpringBootApplication
@EnableConfigurationProperties
public class AnswerSystemApplication extends SpringBootServletInitializer {

    @PostConstruct
    protected void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(AnswerSystemApplication.class);
    }

    /**
     * @apiDefine REQUEST_SUCCESS
     * @apiSuccessExample {json} Response Success Example
     *   HTTP/1.1 200 OK
     *   {
     *     "code": "200",
     *     "hint":"success",
     *     "info": "",
     *     "memo": "",
     *     "data": ""
     *   }
     */

    /**
     * @apiDefine REQUEST_FAIL
     * @apiSuccessExample {json} Response Fail Example
     * HTTP/1.1 500 Internal Server Error
     * {
     * "code": "500",
     * "hint":"fail message",
     * "info": "",
     * "memo": ""
     * }
     */
    public static void main(String[] args) {
        SpringApplication.run(AnswerSystemApplication.class, args);
    }
}
