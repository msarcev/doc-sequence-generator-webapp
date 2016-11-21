package app.mybatis.mappers;

import app.model.User;
import app.model.UserProfile;
import org.apache.ibatis.annotations.*;

import java.util.Set;

/**
 * Created by msarcevic on 11/16/16.
 */
public interface UserMapper {

    @Select("SELECT * FROM app_user WHERE id = #{userId}")
    @Results({
            @Result(property = "ssoId", column = "sso_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    User findOne(@Param("userId") String userId);

    @Select("SELECT * FROM app_user WHERE sso_id = #{ssoId}")
    @Results({
            @Result(property = "ssoId", column = "sso_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    User findBySsoId(@Param("ssoId") String ssoId);

    @Select("SELECT user_profile.type FROM " +
            "  seqnumgen.user_profile," +
            "  seqnumgen.app_user_user_profile," +
            "  seqnumgen.app_user" +
            " WHERE " +
            "  user_profile.id = app_user_user_profile.user_profile_id AND " +
            "  app_user_user_profile.user_id = app_user.id AND " +
            "  app_user.id = #{userId}")
    @Results({
            @Result(property = "ssoId", column = "sso_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    Set<UserProfile> findUserProfilesByUserId(@Param("userId") int userId);

    @Insert("INSERT INTO app_user(sso_id, password, first_name, last_name, email) " +
            "VALUES (#{ssoId}, #{password}, #{first_name}, #{last_name}, #{email})")
    @Results({
            @Result(property = "ssoId", column = "sso_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    void saveUser(@Param("ssoId") String ssoId,
                  @Param("firstName") String firstName,
                  @Param("lastName") String lastName,
                  @Param("email") String email);
}
