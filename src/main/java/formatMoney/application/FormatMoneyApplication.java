package formatMoney.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "formatMoney.base",
        "formatMoney.controller",
        "formatMoney.model"})

@SpringBootApplication
public class FormatMoneyApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormatMoneyApplication.class, args);
    }
}