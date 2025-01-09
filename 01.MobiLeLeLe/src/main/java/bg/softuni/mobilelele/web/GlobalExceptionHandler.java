package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ModelAndView handleObjectNotFound(ObjectNotFoundException e) {
        ModelAndView mav = new ModelAndView("object-not-found");
        mav.addObject("objectId", e.getId());

        return mav;
    }
}
