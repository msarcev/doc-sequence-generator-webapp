package app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class CustomController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showPersonsPage() {
        ModelAndView modelAndView = new ModelAndView();

        List<SequenceEntry> dummyEntries = new ArrayList<>(5);
        dummyEntries.add(new SequenceEntry(1, "admin", "test1", String.valueOf(new Date().getTime())));
        dummyEntries.add(new SequenceEntry(2, "admin", "test2", String.valueOf(new Date().getTime())));
        dummyEntries.add(new SequenceEntry(3, "admin", "test3", String.valueOf(new Date().getTime())));
        dummyEntries.add(new SequenceEntry(5, "admin", "test4", String.valueOf(new Date().getTime())));
        dummyEntries.add(new SequenceEntry(6, "admin", "test5", String.valueOf(new Date().getTime())));
        modelAndView.addObject("entries", dummyEntries);
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

    public class SequenceEntry {

        private int id;
        private String author;
        private String purpose;
        private String time;

        public SequenceEntry(int id, String author, String purpose, String time) {

            this.setId(id);
            this.setAuthor(author);
            this.setPurpose(purpose);
            this.setTime(time);
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getPurpose() {
            return purpose;
        }

        public void setPurpose(String purpose) {
            this.purpose = purpose;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

    }

}
