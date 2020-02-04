package com.jingli.modular.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingli.modular.entity.Dept;
import com.jingli.modular.mapper.DeptMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IService<Dept> {

}
