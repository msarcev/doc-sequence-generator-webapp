package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import app.validators.SequenceFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
public class SequenceController {

    @Autowired
    SequenceService sequenceService;

    @Autowired
    SequenceFormValidator sequenceFormValidator;

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

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public ModelAndView claimNewSequence(@Valid @ModelAttribute Sequence sequence, BindingResult bindingResult) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date = sdf.format(new Date().getTime());
        sequence.setDateTime(date);


        ModelAndView modelAndView = new ModelAndView("redirect:/");

        if (bindingResult.hasErrors()){
            modelAndView = new ModelAndView();
            modelAndView.addObject(sequence);
            modelAndView.setViewName("input");
            return modelAndView;
        }

        sequenceService.saveSequences(Arrays.asList(sequence));

        return modelAndView;

    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam(value = "id", required = true) Integer id) {

        Sequence seq = sequenceService.findById(id);
        seq.setFormattedsequence();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sequence", seq);
        modelAndView.setViewName("details");

        return modelAndView;

    }

    @InitBinder("sequence")
    private void initSequenceFormValidator(WebDataBinder binder){
        binder.setValidator(sequenceFormValidator);
    }

}
