package coderio.open.pay.marvel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "coderio.open.pay.wrapper.api.marvel",
        "coderio.open.pay.marvel"})
public class OpenPayMarvelApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenPayMarvelApplication.class, args);
    }

}
