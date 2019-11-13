package prahasiwi.net.userservice.config.messageconverter;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import prahasiwi.net.userservice.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class UserMessageConverter extends AbstractHttpMessageConverter<User> {

    public UserMessageConverter() {
        super(new MediaType("text", "user"));
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return User.class.isAssignableFrom(aClass);
    }

    @Override
    protected User readInternal(Class<? extends User> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        String requestMessage = toUserMessage(httpInputMessage.getBody());
        int i = requestMessage.indexOf("\n");
        if (i == -1){
            throw new HttpMessageNotReadableException("No First Line Found", httpInputMessage);
        }

        String email = requestMessage.substring(0, i).trim();
        String firstName = requestMessage.substring(i).split(" ")[0].trim();
        String lastName = requestMessage.substring(i).split(" ")[1].trim();
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return user;
    }

    private String toUserMessage(InputStream body) {
        Scanner scanner = new Scanner(body, "UTF-8");

        return scanner.useDelimiter("\\A").next();
    }

    @Override
    protected void writeInternal(User user, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        OutputStream outputStream = httpOutputMessage.getBody();

        String body = user.getEmail()+"\n"+user.getFirstName()+" "+user.getLastName();
        outputStream.write(body.getBytes());
        outputStream.close();
    }
}
