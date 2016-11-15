package app;

import app.model.Sequence;
import app.model.User;
import app.service.SequenceService;
import app.service.UserService;
import app.service.impl.PassEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Component
public class DatabaseInitializer {

    @Autowired
    PassEncoder passEncoder;

    private UserService usersService;

    private SequenceService sequenceService;

    @Autowired
    public DatabaseInitializer(UserService usersService, SequenceService sequenceService) {
        this.usersService = usersService;
        this.sequenceService = sequenceService;
    }

    @PostConstruct
    public void populateDatabase() {

        User firstPerson = new User("user1", passEncoder.encode("password"), "Mate", "Matic", "name1@domain.com");
        User secondPerson = new User("user2", passEncoder.encode("password"), "Ante", "Antic", "name2@domain.com");
        User thirdPerson = new User("user3", passEncoder.encode("password"), "Pero", "Peric", "name3@domain.com");
        User fourthPerson = new User("user4", passEncoder.encode("password"), "Sime", "Simic", "name3@domain.com");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String date = sdf.format(new Date().getTime());

        Sequence defaultSeq1 = new Sequence("system", "Test1", date);
        Sequence defaultSeq2 = new Sequence("system", "Test2", date);
        Sequence defaultSeq3 = new Sequence("system", "Test3", date);
        Sequence defaultSeq4 = new Sequence("system", "Test3", date);

        usersService.saveUser(firstPerson);
        usersService.saveUser(secondPerson);
        usersService.saveUser(thirdPerson);
        usersService.saveUser(fourthPerson);

        sequenceService.saveSequences(Arrays.asList(defaultSeq1,defaultSeq2,defaultSeq3,defaultSeq4));

    }

}
