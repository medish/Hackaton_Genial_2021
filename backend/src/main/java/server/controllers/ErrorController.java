package server.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    @ResponseBody

    public String errorMessage(HttpServletRequest req){
        Integer errorCode = (Integer) req.getAttribute("javax.servlet.error.status_code");
        Exception exc = (Exception) req.getAttribute("javax.servlet.error.exception");
        return String.format("<html><body><h2>Cette page n'existe pas</h2>" +
                        "<div>Code d'erreur : <b>%s</b></div>"
                        + "<div>Message d'exception: <b>%s</b></div><body></html>",
                errorCode, exc==null? "N/A": exc.getMessage());
    }


    public String getErrorPath() {
        return "/error";
    }
}
