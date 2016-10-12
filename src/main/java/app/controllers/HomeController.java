package app.controllers;

import app.model.Sequence;
import app.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

    private static int currentPage = 1;

    @Autowired
    SequenceService sequenceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showHomePage(@RequestParam(value = "page", required = false) Integer pageNum,
                                     @RequestParam(value = "filter", required = false) String filter) {

        ModelAndView details = tryResolveFilter(filter);
        if (details != null) return details;

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

    private ModelAndView tryResolveFilter(@RequestParam(value = "filter", required = false) String filter) {
        if (filter != null && filter.length() > 2){
            Sequence seq = sequenceService.filterSequence(filter);
            if (seq != null){
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.addObject("sequence", seq);
                modelAndView.setViewName("details");

                return modelAndView;
            }
        }
        return null;
    }

    @RequestMapping(value = "/greet")
    public String index() {

        return "Greetings from Spring Boot!";

    }

}
