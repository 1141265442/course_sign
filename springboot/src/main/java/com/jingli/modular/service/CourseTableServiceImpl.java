package com.jingli.modular.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jingli.modular.entity.CourseTable;
import com.jingli.modular.mapper.CourseTableMapper;
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
public class CourseTableServiceImpl extends ServiceImpl<CourseTableMapper, CourseTable> implements IService<CourseTable> {

}
