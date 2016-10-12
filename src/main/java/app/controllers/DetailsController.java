package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DetailsController {

    @Autowired
    SequenceService sequenceService;

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public ModelAndView details(@RequestParam(value = "id", required = true) Integer id) {

        Sequence seq = sequenceService.findById(id);
        seq.setFormattedsequence();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sequence", seq);
        modelAndView.setViewName("details");

        return modelAndView;

    }

}
