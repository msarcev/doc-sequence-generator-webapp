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

import java.util.List;

@RestController
public class HomeController {

    private static int currentPage = 1;

    private final boolean serverSidePaging = false;

    @Autowired
    SequenceService sequenceService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showHomePage(@RequestParam(value = "page", required = false) Integer pageNum) {

        ModelAndView modelAndView = new ModelAndView();
        int evalPage;


        if (serverSidePaging) {

            Page<Sequence> page = sequenceService.findAllPageable(new PageRequest(0, 5));

            if (pageNum != null && pageNum > page.getTotalPages()) {
                evalPage = currentPage - 1;
            } else {
                evalPage = (pageNum == null || pageNum < 1) ? currentPage - 1 : pageNum - 1;
            }

            page = sequenceService.findAllPageable(new PageRequest(evalPage, 5));
            formatSequences(page.getContent());

            currentPage = page.getNumber() + 1;
            String pageInfo = "Page " + currentPage + "/" + page.getTotalPages();

            modelAndView.addObject("entries", page.getContent());
            modelAndView.addObject("pageinfo", pageInfo);
            modelAndView.addObject("currentPage", page.getNumber() + 1);

        } else {

            List<Sequence> page = sequenceService.getAll();

            modelAndView.addObject("entries", page);
        }

        modelAndView.setViewName("home");

        return modelAndView;

    }

    private void formatSequences(List<Sequence> content) {
        for (Sequence sequence : content){
            sequence.setFormattedsequence();
        }
    }

}
