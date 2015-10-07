package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("")
public class RighBannerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(
                RighBannerApplication.class);
        app.setBanner(new RichBanner("hajiboot.png"));
        app.run(args);
    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}
