package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomController {

    @Autowired
    SequenceService sequenceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView();

        Page<Sequence> dummyEntries = sequenceService.findAllPageable(new PageRequest(0,5));

        modelAndView.addObject("entries", dummyEntries.getContent());
        modelAndView.setViewName("home");

        return modelAndView;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("details");

        return modelAndView;
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView input() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("input");

        return modelAndView;
    }

    @RequestMapping(value = "/greet")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
