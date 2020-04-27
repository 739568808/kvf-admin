package com.kalvin.kvf.modules.tb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.tb.entity.Uv;

/**
 * <p>
 *  服务类
 * </p>
 * @since 2020-04-27 16:06:12
 */
public interface UvService extends IService<Uv> {

    /**
     * 获取列表。分页
     * @param uv 查询参数
     * @return page
     */
    Page<Uv> listUvPage(Uv uv);

}
