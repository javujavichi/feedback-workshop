/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package feedback.workshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public String getGreeting() {
        return "Hello architects.";
    }

    public static void main(String[] args) {
       SpringApplication.run(App.class, args);
    }
}
