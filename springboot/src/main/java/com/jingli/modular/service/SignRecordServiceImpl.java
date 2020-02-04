package com.jingli.modular.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingli.modular.entity.SignRecord;
import com.jingli.modular.mapper.SignRecordMapper;
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
public class SignRecordServiceImpl extends ServiceImpl<SignRecordMapper, SignRecord> implements IService<SignRecord> {

}
