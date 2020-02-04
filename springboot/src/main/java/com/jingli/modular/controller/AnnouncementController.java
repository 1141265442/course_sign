package com.jingli.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.core.constant.PathUtil;
import com.jingli.core.msg.SuccessTip;
import com.jingli.modular.entity.Announcement;
import com.jingli.modular.entity.CourseTable;
import com.jingli.modular.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jingli
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    @Autowired
    AnnouncementMapper announcementMapper;

    @Autowired
    PathUtil pathUtil;

    @RequestMapping("/detail")
    public Object detail(Integer id){
        return new SuccessTip(announcementMapper.selectById(id));
    }

    @RequestMapping("/selectAll")
    public Object selectAll() throws IOException {
        List<Announcement> announcements = announcementMapper.selectList(null);
        for (Announcement announcement : announcements) {
            Path path= Paths.get(pathUtil.getUploadDir()+"/"+announcement.getName()+announcement.getId()+".jpg");
            //if(!Files.exists(path)){
                Files.write(path, announcement.getImg());
            //}
            announcement.setImgUrl(announcement.getName()+announcement.getId()+".jpg");
            announcement.updateById();
        }
        return new SuccessTip(announcements);
    }

    @RequestMapping("modify")
    public Object modify(MultipartFile[] file, String name,String content,String id) throws IOException {
        Announcement announcement = announcementMapper.selectById(Integer.parseInt(id));
        announcement.setName(name);
        announcement.setContent(content);
        if(file!=null){
            for (MultipartFile multipartFile : file) {
                announcement.setImg(multipartFile.getBytes());
            }
        }
        announcement.updateById();
        return new SuccessTip("公告修改成功");
    }

    @RequestMapping("/getImg")
    public void getImg(String fileName, HttpServletResponse response){
        FileInputStream fis = null;
        response.setContentType("image/gif");
        try {
            OutputStream out = response.getOutputStream();
            File file = new File(pathUtil.getUploadDir()+"/"+fileName);
            fis = new FileInputStream(file);
            byte[] b = new byte[fis.available()];
            fis.read(b);
            out.write(b);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
