package feedback.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "feedback.workshop")
public class App {

    public static void main(String[] args) {
        System.out.println("Hello architects.");
        SpringApplication.run(App.class, args);
    }
}