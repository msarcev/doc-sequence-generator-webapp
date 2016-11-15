package app.dao.repository;

import app.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by msarcevic on 11/14/16.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findBySsoId(String ssoId);
}
