package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;

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

    @PostMapping("/input")
    public ModelAndView claimNewSequence(@ModelAttribute Sequence sequence) {

        sequence.setDateTime(String.valueOf(new Date().getTime()));
        sequenceService.save(Arrays.asList(sequence));
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        return modelAndView;

    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView input() {

        ModelAndView modelAndView = new ModelAndView();
        Sequence sequence = new Sequence();
        modelAndView.addObject("sequence", sequence);
        modelAndView.setViewName("input");

        return modelAndView;
    }

    @RequestMapping(value = "/greet")
    public String index() {

        return "Greetings from Spring Boot!";

    }

}
