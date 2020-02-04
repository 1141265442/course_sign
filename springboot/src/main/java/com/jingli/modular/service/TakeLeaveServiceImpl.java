package com.jingli.modular.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingli.modular.entity.TakeLeave;
import com.jingli.modular.mapper.TakeLeaveMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@Service
public class TakeLeaveServiceImpl extends ServiceImpl<TakeLeaveMapper, TakeLeave> implements IService<TakeLeave> {

}
