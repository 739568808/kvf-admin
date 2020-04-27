package com.kalvin.kvf.modules.tb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.tb.entity.TbUser;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-04-27 16:06:11
 */
public interface TbUserService extends IService<TbUser> {

    /**
     * 获取列表。分页
     * @param user 查询参数
     * @return page
     */
    Page<TbUser> listUserPage(TbUser user);

}
