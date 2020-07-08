package com.learn.jeffrey.dao;

import com.learn.jeffrey.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Description <P></P>
 *
 * @Author lijianfei
 * @Date 2020/7/8 11:02
 **/
public interface ArticleRepository extends MongoRepository<Article,Long> {
    /**
     * 根据标题模糊查询
     *
     * @param title 标题
     * @return 满足条件的文章列表
     */
    List<Article> findByTitleLike(String title);
}
