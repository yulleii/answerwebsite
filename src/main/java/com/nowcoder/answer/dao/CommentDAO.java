package com.nowcoder.answer.dao;

import com.nowcoder.answer.model.Comment;
import com.nowcoder.answer.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface CommentDAO {
    String TABLE_NAME=" comment ";
    String INSERT_FILEDS=" user_id,content,created_date,entity_id,entity_type, status  ";
    String SELECT_FILEDS=" id,"+INSERT_FILEDS;

    @Insert({"insert into",
            TABLE_NAME ,"(",INSERT_FILEDS,
            ") values(#{userId},#{content},#{createdDate},#{entityId},#{entityType},#{status})"})
    int addComment(Comment comment);

    @Select({"select ",SELECT_FILEDS," from ",TABLE_NAME,"where entity_id=#{entityId} and entity_type=#{entityType} " +
            "order by created_date desc"})
    List<Comment> selectCommentByEntity(@Param("entityId") int entityId,
                                         @Param("entityType") int entityType);

    @Select({"select count(id) from",TABLE_NAME," where entity_id=#{entityId} and entity_type=#{entityType}"})
    int getCommentCount(@Param("entityId")int entityId,@Param("entityType")int entityType);
}
