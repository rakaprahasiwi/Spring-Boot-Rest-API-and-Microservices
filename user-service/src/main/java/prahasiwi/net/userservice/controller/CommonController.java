package prahasiwi.net.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class CommonController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private Environment environment;

    @RequestMapping("app-name")
    public String appName(){
//        return applicationName;
        return environment.getProperty("spring.application.name");
    }
}
