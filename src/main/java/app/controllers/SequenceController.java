package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import app.validators.SequenceFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.sql.Timestamp;
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

        Sequence sequence = generateNewSequence();
        modelAndView.addObject("sequence", sequence);
        modelAndView.setViewName("input");

        return modelAndView;
    }

    @RequestMapping(value = "/input", method = RequestMethod.POST)
    public ModelAndView claimNewSequence(@Valid @ModelAttribute Sequence sequence, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();

        Timestamp stamp = new Timestamp(new Date().getTime());
        sequence.setDateTime(stamp);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("sequence", sequence);
            modelAndView.setViewName("input");
            return modelAndView;
        }

        try {
            sequenceService.saveSequences(Arrays.asList(sequence));

        } catch (OptimisticLockingFailureException ex) {

            bindingResult.rejectValue("dateTime", "concurrency.lock.fail", "This sequence was taken. Assigning new...");
            sequence.setId(sequence.getId() + 1);
            modelAndView.addObject(sequence);
            modelAndView.setViewName("input");

            return modelAndView;
        }

        modelAndView.setViewName("redirect:/");

        return modelAndView;

    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam(value = "id", required = true) Integer id) {

        Sequence seq = sequenceService.findById(id);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sequence", seq);
        modelAndView.setViewName("details");

        return modelAndView;

    }

    private Sequence generateNewSequence() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Sequence sequence = new Sequence(auth.getName(), null, null);

        int count = sequenceService.countSequences();
        int last = sequenceService.getLastSequenceId();

        if (count == 0) {
            sequence.setId(last);
        } else {
            sequence.setId(++last);
        }

        return sequence;
    }

    @InitBinder("sequence")
    private void initSequenceFormValidator(WebDataBinder binder) {
        binder.setValidator(sequenceFormValidator);
    }

}
