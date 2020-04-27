package com.kalvin.kvf.modules.tb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.tb.entity.Message;
import com.kalvin.kvf.modules.tb.mapper.MessageMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-04-27 16:06:11
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {

    @Override
    public Page<Message> listMessagePage(Message message) {
        Page<Message> page = new Page<>(message.getCurrent(), message.getSize());
        List<Message> messages = baseMapper.selectMessageList(message, page);
        return page.setRecords(messages);
    }

}
