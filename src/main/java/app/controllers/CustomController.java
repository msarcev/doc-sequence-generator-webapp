package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;

@RestController
public class CustomController {

    private static int currentPage = 1;

    @Autowired
    SequenceService sequenceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showHomePage(@RequestParam(value = "page", required = false) Integer pageNum,
                                     @RequestParam(value = "filter", required = false) String filter) {

        if (filter != null && filter.length() > 2){
            Sequence seq = sequenceService.filterSequence(filter);
            if (seq != null){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("sequence", seq);
                modelAndView.setViewName("details");

                return modelAndView;
            }
        }

        ModelAndView modelAndView = new ModelAndView();
        int evalPage;

        Page<Sequence> page = sequenceService.findAllPageable(new PageRequest(0,5));

        if (pageNum != null && pageNum > page.getTotalPages()){
            evalPage = currentPage - 1;
        } else {
            evalPage = (pageNum == null || pageNum < 1) ? currentPage - 1 : pageNum - 1;
        }

        page = sequenceService.findAllPageable(new PageRequest(evalPage,5));

        currentPage = page.getNumber() + 1;
        String pageInfo = "Page " + currentPage + "/" + page.getTotalPages();

        modelAndView.addObject("entries", page.getContent());
        modelAndView.addObject("pageinfo", pageInfo);
        modelAndView.addObject("currentPage", page.getNumber() + 1);
        modelAndView.setViewName("home");

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

    @RequestMapping(value = "/greet")
    public String index() {

        return "Greetings from Spring Boot!";

    }

}
