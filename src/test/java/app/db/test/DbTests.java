package app.db.test;

import app.model.User;
import app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbTests {

    @Autowired
    UserService userService;

    @Test
    @Transactional
    public void firstUserTest() throws Exception {
        User testUser = userService.findById(1);
        assertThat(testUser.getSsoId(), equalTo("user_1"));
    }

}
