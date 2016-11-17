package app.mybatis.mappers;

import app.model.User;
import org.apache.ibatis.annotations.*;

/**
 * Created by msarcevic on 11/16/16.
 */
public interface UserMapper {

    @Select("SELECT * FROM app_user WHERE id = #{userId}")
    User getUserById(@Param("userId") String userId);

    @Select("SELECT * FROM app_user WHERE sso_id = #{ssoId}")
    @Results({
            @Result(property = "ssoId", column = "sso_id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name")
    })
    User getUserBySsoId(@Param("ssoId") String ssoId);
}
