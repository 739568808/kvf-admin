package com.kalvin.kvf.modules.tb.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.kalvin.kvf.common.controller.BaseController;
import com.kalvin.kvf.common.dto.R;
import com.kalvin.kvf.modules.tb.entity.Message;
import com.kalvin.kvf.modules.tb.service.MessageService;

import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 * @since 2020-04-27 16:06:11
 */
@RestController
@RequestMapping("tb/message")
public class MessageController extends BaseController {

    @Autowired
    private MessageService messageService;

    @RequiresPermissions("tb:message:index")
    @GetMapping("index")
    public ModelAndView index() {
        return new ModelAndView("tb/message");
    }

    @GetMapping(value = "edit")
    public ModelAndView edit(Long id) {
        ModelAndView mv = new ModelAndView("tb/message_edit");
        Message message;
        if (id == null) {
            message = new Message();
        } else {
            message = messageService.getById(id);
        }
        mv.addObject("editInfo", message);
        return mv;
    }

    @GetMapping(value = "list/data")
    public R listData(Message message) {
        Page<Message> page = messageService.listMessagePage(message);
        return R.ok(page);
    }

    @RequiresPermissions("tb:message:add")
    @PostMapping(value = "add")
    public R add(Message message) {
        messageService.save(message);
        return R.ok();
    }

    @RequiresPermissions("tb:message:del")
    @PostMapping(value = "batchdel")
    public R batchdel(@RequestParam("ids") List<Long> ids) {
        messageService.removeByIds(ids);
        return R.ok();
    }

    @RequiresPermissions("tb:message:edit")
    @PostMapping(value = "edit")
    public R edit(Message message) {
        messageService.updateById(message);
        return R.ok();
    }

    @RequiresPermissions("tb:message:del")
    @PostMapping(value = "del/{id}")
    public R del(@PathVariable Long id) {
        messageService.removeById(id);
        return R.ok();
    }

    @GetMapping(value = "get/{id}")
    public R get(@PathVariable Long id) {
        return R.ok(messageService.getById(id));
    }

}

