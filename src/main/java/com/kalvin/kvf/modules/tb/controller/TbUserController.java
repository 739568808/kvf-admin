package com.kalvin.kvf.modules.tb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.tb.entity.TbUser;
import com.kalvin.kvf.modules.tb.service.TbUserService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-04-27 16:06:11
 */
@RestController
@RequestMapping("tb/user")
public class TbUserController extends BaseController {

    @Autowired
    private TbUserService userService;

    @RequiresPermissions("tb:user:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("tb/user");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("tb/user_edit");
        TbUser user;
        if (id == null) {
            user = new TbUser();
        } else {
            user = userService.getById(id);
        }
        mv.addObject("editInfo", user);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(TbUser user) {
        Page<TbUser> page = userService.listUserPage(user);
        return R.ok(page);
    }

    @RequiresPermissions("tb:user:add")
    @PostMapping(value = "add")
    public R add(TbUser user) {
        userService.save(user);
        return R.ok();
    }

    @RequiresPermissions("tb:user:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        userService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("tb:user:edit")
    @PostMapping(value = "edit")
    public R edit(TbUser user) {
        userService.updateById(user);
        return R.ok();
    }

    @RequiresPermissions("tb:user:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        userService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(userService.getById(id));
    }

}

