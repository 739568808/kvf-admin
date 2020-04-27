package com.kalvin.kvf.modules.tb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.tb.entity.Article;
import com.kalvin.kvf.modules.tb.mapper.ArticleMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-04-27 16:06:11
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Override
    public Page<Article> listArticlePage(Article article) {
        Page<Article> page = new Page<>(article.getCurrent(), article.getSize());
        List<Article> articles = baseMapper.selectArticleList(article, page);
        return page.setRecords(articles);
    }

}
