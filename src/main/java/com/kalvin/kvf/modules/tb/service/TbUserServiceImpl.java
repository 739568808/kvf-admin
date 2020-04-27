package com.kalvin.kvf.modules.tb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.tb.entity.TbUser;
import com.kalvin.kvf.modules.tb.mapper.TbUserMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-04-27 16:06:11
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements TbUserService {

    @Override
    public Page<TbUser> listUserPage(TbUser user) {
        Page<TbUser> page = new Page<>(user.getCurrent(), user.getSize());
        List<TbUser> users = baseMapper.selectUserList(user, page);
        return page.setRecords(users);
    }

}
