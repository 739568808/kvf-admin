package com.kalvin.kvf.modules.tb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.tb.entity.Article;
import com.kalvin.kvf.modules.tb.service.ArticleService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-04-27 16:06:11
 */
@RestController
@RequestMapping("tb/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @RequiresPermissions("tb:article:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("tb/article");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("tb/article_edit");
        Article article;
        if (id == null) {
            article = new Article();
        } else {
            article = articleService.getById(id);
        }
        mv.addObject("editInfo", article);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Article article) {
        Page<Article> page = articleService.listArticlePage(article);
        return R.ok(page);
    }

    @RequiresPermissions("tb:article:add")
    @PostMapping(value = "add")
    public R add(Article article) {
        if (null == article.getPv()){
            article.setPv(0);
        }
        article.setCreateDate(LocalDateTime.now());
        article.setUpdateDate(LocalDateTime.now());
        articleService.save(article);
        return R.ok();
    }

    @RequiresPermissions("tb:article:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        articleService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("tb:article:edit")
    @PostMapping(value = "edit")
    public R edit(Article article) {
        article.setUpdateDate(LocalDateTime.now());
        articleService.updateById(article);
        return R.ok();
    }

    @RequiresPermissions("tb:article:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        articleService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(articleService.getById(id));
    }

}

