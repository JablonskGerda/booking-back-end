package lt.sdacademy.booking.beauty.config;

import java.util.ArrayList;
import java.util.Collections;
import javax.servlet.http.HttpServletResponse;
import lt.sdacademy.booking.beauty.models.exceptions.ServerError;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlers {

    private final MessageSource messageSource;

    public ExceptionHandlers(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public ServerError handleIllegalArgumentException(HttpServletResponse response, IllegalArgumentException ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String header = ex.getMessage();
        return new ServerError(header, new ArrayList<>());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerError handleGeneralException(HttpServletResponse response, Exception ex) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        String header = "Server error";
        ex.printStackTrace();
        return new ServerError(header, Collections.singletonList(ex.getMessage()));
    }
}
