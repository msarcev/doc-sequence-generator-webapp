package app.mybatis.mappers;

import app.model.Sequence;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by msarcevic on 11/17/16.
 */
public interface SequencesMapper {

    @Select("SELECT * from sequence")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "author", column = "author"),
            @Result(property = "purpose", column = "purpose"),
            @Result(property = "dateTime", column = "date")
    })
    List<Sequence> findAll();

    @Select("SELECT * from sequence WHERE id = #{sequenceId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "author", column = "author"),
            @Result(property = "purpose", column = "purpose"),
            @Result(property = "dateTime", column = "date")
    })
    Sequence findOne(@Param("sequenceId") int sequenceId);

    @Select("SELECT last_value FROM sequence_id_seq")
    int getLast();

    @Select("SELECT COUNT(*) FROM sequence")
    int getElementsCount();

    Iterable<Sequence> save(Iterable<Sequence> persons);
}
