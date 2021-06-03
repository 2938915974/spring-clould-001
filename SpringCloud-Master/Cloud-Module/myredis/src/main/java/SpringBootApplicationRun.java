

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableFeignClients(basePackages = "com.mytest.service")
@ComponentScan("com.**")
@SpringCloudApplication
public class SpringBootApplicationRun {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplicationRun.class, args);
    }

}