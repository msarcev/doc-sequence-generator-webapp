package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;

@RestController
public class InputController {

    @Autowired
    SequenceService sequenceService;

    @PostMapping("/input")
    public ModelAndView claimNewSequence(@ModelAttribute Sequence sequence) {

        sequence.setDateTime(String.valueOf(new Date().getTime()));
        sequenceService.saveSequences(Arrays.asList(sequence));
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        return modelAndView;

    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView input() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Sequence sequence = new Sequence(auth.getName(),null,null);
        sequence.setId(sequenceService.getLastSequence().getId()+1);
        modelAndView.addObject("sequence", sequence);
        modelAndView.setViewName("input");

        return modelAndView;
    }

}
