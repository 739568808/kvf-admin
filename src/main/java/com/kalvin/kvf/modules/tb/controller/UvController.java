package com.kalvin.kvf.modules.tb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.tb.entity.Uv;
import com.kalvin.kvf.modules.tb.service.UvService;

import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-04-27 16:06:12
 */
@RestController
@RequestMapping("tb/uv")
public class UvController extends BaseController {

    @Autowired
    private UvService uvService;

    @RequiresPermissions("tb:uv:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("tb/uv");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("tb/uv_edit");
        Uv uv;
        if (id == null) {
            uv = new Uv();
        } else {
            uv = uvService.getById(id);
        }
        mv.addObject("editInfo", uv);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Uv uv) {
        Page<Uv> page = uvService.listUvPage(uv);
        return R.ok(page);
    }

    @RequiresPermissions("tb:uv:add")
    @PostMapping(value = "add")
    public R add(Uv uv) {
        uvService.save(uv);
        return R.ok();
    }

    @RequiresPermissions("tb:uv:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        uvService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("tb:uv:edit")
    @PostMapping(value = "edit")
    public R edit(Uv uv) {
        uvService.updateById(uv);
        return R.ok();
    }


    @RequiresPermissions("tb:uv:edit")
    @PostMapping(value = "jiesuan")
    public R jiesuan(Uv uv) {
        LambdaQueryWrapper<Uv> queryWrapper = new LambdaQueryWrapper<>();
        Uv en = new Uv();
        en.setStatus(1);
        en.setUpdaeDate(LocalDateTime.now());
        queryWrapper.setEntity(uv);
        uvService.update(en,queryWrapper);
        return R.ok();
    }

    @RequiresPermissions("tb:uv:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        uvService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(uvService.getById(id));
    }

}

