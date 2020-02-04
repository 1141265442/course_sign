package com.jingli.modular.service.impl;

import com.jingli.modular.entity.Announcement;
import com.jingli.modular.mapper.AnnouncementMapper;
import com.jingli.modular.service.IAnnouncementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jingli
 * @since 2020-02-01
 */
@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements IAnnouncementService {

}
