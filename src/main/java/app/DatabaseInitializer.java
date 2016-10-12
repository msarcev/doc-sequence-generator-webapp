package app;

import app.model.Sequence;
import app.model.User;
import app.service.SequenceService;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Date;

@Component
public class DatabaseInitializer {

    private UserService usersService;

    private SequenceService sequenceService;

    @Autowired
    public DatabaseInitializer(UserService usersService, SequenceService sequenceService) {
        this.usersService = usersService;
        this.sequenceService = sequenceService;
    }

    @PostConstruct
    public void populateDatabase() {

        User firstPerson = new User("user_1", "password", "Mate", "Matic", "name1@domain.com");
        User secondPerson = new User("user_2", "password", "Ante", "Antic", "name2@domain.com");
        User thirdPerson = new User("user_3", "password", "Pero", "Peric", "name3@domain.com");
        User fourthPerson = new User("user_4", "password", "Sime", "Simic", "name3@domain.com");

        Sequence defaultSeq1 = new Sequence("system", "Test1", String.valueOf(new Date().getTime()));
        Sequence defaultSeq2 = new Sequence("system", "Test2", String.valueOf(new Date().getTime()));
        Sequence defaultSeq3 = new Sequence("system", "Test3", String.valueOf(new Date().getTime()));

        usersService.saveUser(firstPerson);
        usersService.saveUser(secondPerson);
        usersService.saveUser(thirdPerson);
        usersService.saveUser(fourthPerson);

        sequenceService.saveSequences(Arrays.asList(defaultSeq1,defaultSeq2,defaultSeq3));

    }

}
