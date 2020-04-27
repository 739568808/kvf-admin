package com.kalvin.kvf.modules.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.tb.entity.TbUser;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-04-27 16:06:11
 */
public interface TbUserMapper extends BaseMapper<TbUser> {

    /**
     * 查询列表(分页)
     * @param user 查询参数
     * @param page 分页参数
     * @return list
     */
    List<TbUser> selectUserList(TbUser user, IPage page);

}
