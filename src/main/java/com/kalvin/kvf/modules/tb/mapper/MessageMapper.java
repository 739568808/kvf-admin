package com.kalvin.kvf.modules.tb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kalvin.kvf.modules.tb.entity.Message;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * @since 2020-04-27 16:06:11
 */
public interface MessageMapper extends BaseMapper<Message> {

    /**
     * 查询列表(分页)
     * @param message 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Message> selectMessageList(Message message, IPage page);

}
