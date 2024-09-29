package pets.cats.data.petscatsaccessapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"pets.cats.data.petscatsaccessservices", "pets.cats.data.petscatsaccesssecurity"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
