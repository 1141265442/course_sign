package com.jingli.modular.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jingli.core.constant.PathUtil;
import com.jingli.core.msg.ErrorTip;
import com.jingli.core.msg.SuccessTip;
import com.jingli.modular.entity.CourseTable;
import com.jingli.modular.entity.Dept;
import com.jingli.modular.mapper.CourseTableMapper;
import com.jingli.modular.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jingli
 * @since 2020-01-31
 */
@RestController
@RequestMapping("/courseTable")
public class CourseTableController {

    @Autowired
    PathUtil pathUtil;
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    CourseTableMapper courseTableMapper;

    @RequestMapping("/add")
    @CrossOrigin
    public Object add(MultipartFile[] file,String name) throws IOException {
        CourseTable courseTable=new CourseTable();
        courseTable.setDeptId(getDeptByName(name).getId());

        QueryWrapper<CourseTable> queryWrapper = new QueryWrapper<CourseTable>();
        queryWrapper.eq("dept_id",getDeptByName(name).getId());
        if(courseTable.selectOne(queryWrapper)!=null)
        {
            return "所选班级课表已上传";
        }

        String uploadDir = pathUtil.getUploadDir();
        if(file!=null){
            for (MultipartFile multipartFile : file) {
                //multipartFile.transferTo(new File(uploadDir,multipartFile.getOriginalFilename()));
                courseTable.setContent(multipartFile.getBytes());
            }
        }
        courseTable.insert();
        return "课表添加成功！";

    }

    @RequestMapping("/detail")
    public Object courseDetail(MultipartFile[] file,String id) throws IOException {
        Dept dept = deptMapper.selectById(id);
        QueryWrapper<CourseTable> queryWrapper = new QueryWrapper<CourseTable>();
        queryWrapper.eq("dept_id",dept.getId());
        CourseTable courseTable = courseTableMapper.selectOne(queryWrapper);

        Path path=Paths.get(pathUtil.getUploadDir()+"/"+courseTable.getId()+".jpg");
        if(!Files.exists(path)){
            Files.write(path, courseTable.getContent());
        }
        courseTable.setContentUrl(courseTable.getId()+".jpg");
        courseTable.setName(dept.getName());
        courseTable.updateById();
        return new SuccessTip(courseTable);
    }

    @RequestMapping("/getImg")
    public void getImg(String fileName,HttpServletResponse response){
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


    public Dept getDeptByName(String name){
        //构造查询条件
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<Dept>();
        queryWrapper.eq("name",name);
        //查询数据库表
        return deptMapper.selectOne(queryWrapper);
    }
}
