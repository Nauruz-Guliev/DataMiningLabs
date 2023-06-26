package ru.kpfu.itis.gnt.hwpebble.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.gnt.hwpebble.exception.DatabaseException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView internalError(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", exception.getMessage() == null ? "Internal server error" : exception.getMessage() + " class: "  + exception.getClass());
        mav.setViewName("error");
        return mav;
    }
}
