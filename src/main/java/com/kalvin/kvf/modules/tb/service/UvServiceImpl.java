package com.kalvin.kvf.modules.tb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.tb.entity.Uv;
import com.kalvin.kvf.modules.tb.mapper.UvMapper;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 * @since 2020-04-27 16:06:12
 */
@Service
public class UvServiceImpl extends ServiceImpl<UvMapper, Uv> implements UvService {

    @Override
    public Page<Uv> listUvPage(Uv uv) {
        Page<Uv> page = new Page<>(uv.getCurrent(), uv.getSize());
        List<Uv> uvs = baseMapper.selectUvList(uv, page);
        return page.setRecords(uvs);
    }

}
