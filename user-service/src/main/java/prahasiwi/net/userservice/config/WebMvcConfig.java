package prahasiwi.net.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import prahasiwi.net.userservice.config.messageconverter.UserMessageConverter;

import java.util.List;

@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters){
        converters.add(new UserMessageConverter());
    }
}
