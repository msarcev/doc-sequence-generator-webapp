package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
public class InputController {

    @Autowired
    SequenceService sequenceService;

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public ModelAndView claimNewSequence(@ModelAttribute Sequence sequence) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date = sdf.format(new Date().getTime());
        sequence.setDateTime(date);

        if (!sequence.getPurpose().equals("")) {
            sequenceService.saveSequences(Arrays.asList(sequence));
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/");

        return modelAndView;

    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public ModelAndView input() {

        ModelAndView modelAndView = new ModelAndView();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Sequence sequence = new Sequence(auth.getName(),null,null);
        Sequence last = sequenceService.getLastSequence();
        if (last != null){
            sequence.setId(sequenceService.getLastSequence().getId()+1);
        } else {
            sequence.setId(1);
        }
        sequence.setFormattedsequence();
        modelAndView.addObject("sequence", sequence);
        modelAndView.setViewName("input");

        return modelAndView;
    }

}
