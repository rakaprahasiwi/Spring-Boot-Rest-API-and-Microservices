package prahasiwi.net.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("v1")
public class CommonController {

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private Environment environment;

        @RequestMapping("random-values")
    public Map<String, String> appRandom(){
        Map<String, String> appRandom = new LinkedHashMap<>();
        appRandom.put("Random", environment.getProperty("random.value"));
        appRandom.put("RandomInteger", environment.getProperty("random.int"));
        appRandom.put("RandomLong", environment.getProperty("random.long"));
        appRandom.put("RandomUUID", environment.getProperty("random.uuid"));
        appRandom.put("RandomLessThanHundred", environment.getProperty("random.int(100)"));
        appRandom.put("RandomWithinRange", environment.getProperty("random.int(1000,9999)"));

        return  appRandom;
    }

    @RequestMapping("app-info")
    public Map<String, String> appInfo(){
        Map<String, String> appInfo = new LinkedHashMap<>();
        appInfo.put("Name", environment.getProperty("app.name"));
        appInfo.put("version", environment.getProperty("app.version"));
        appInfo.put("Description", environment.getProperty("app.description"));

        return  appInfo;
    }

    @RequestMapping("app-name")
    public String appName(){
//        return applicationName;
        return environment.getProperty("spring.application.name");
    }
}
