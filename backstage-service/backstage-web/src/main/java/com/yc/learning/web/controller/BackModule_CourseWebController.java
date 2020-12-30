package com.yc.learning.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.learning.domain.CourseDomain;
import com.yc.learning.future.BackModule_CourseFuture;
import com.yc.learning.service.AsyncThreadPool;
import com.yc.learning.service.FastefsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/course")
public class BackModule_CourseWebController {

    @Value("${file.path.head:http://120.79.175.237/}")
    private String pathHead;

    @Autowired
    private FastefsClient fastefsClient;

    @Autowired(required = false)
    private BackModule_CourseFuture courseFuture;



    @RequestMapping(value = "findByPage",method = RequestMethod.GET)
    public CompletableFuture<String> findByPage(@RequestParam(value = "page",required = false) Integer page, @RequestParam(value = "pageSize",required = false) Integer pageSize, @RequestParam(value = "coursename",required = false) String coursename) {
        return courseFuture.findByPage(page, pageSize, coursename);
    }

    //查询课程对应的章节名
    @RequestMapping(value = "findCoursenameWithCname",method = RequestMethod.GET)
    public CompletableFuture<String> findCoursenameWithCname() {
        return courseFuture.findCoursenameWithCname();
    }

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public String uploadPic(@RequestParam("pictureFile") MultipartFile multipartFile, String coursename,String descr, HttpServletRequest request, HttpServletResponse response) {
        try {
            String filename = fastefsClient.uplodFile(multipartFile);
            AsyncThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("文件通过nginx访问的路径:" + (pathHead + filename));
                        savePic(multipartFile, pathHead + filename, coursename,descr);  //调用数据库操作
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("pathInfo", pathHead + filename);

            ObjectMapper mapper = new ObjectMapper();
            String ret = mapper.writeValueAsString(data);

            response.setContentType("text/html;charset=utf8");
            response.getOutputStream().write(ret.getBytes());
            response.flushBuffer();
            return pathHead+filename;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 数据库操作
     * @param multipartFile
     * @param filename
     * @param coursename
     * @param descr
     * @return
     * @throws Exception
     */
    private CompletableFuture<String> savePic(MultipartFile multipartFile, String filename, String coursename,String descr) throws Exception {
        BufferedImage image = ImageIO.read(multipartFile.getInputStream());
        CourseDomain courseDomain = new CourseDomain();
        courseDomain.setCoursename(coursename);
        courseDomain.setDescr(descr);
        courseDomain.setPic(filename);
        courseDomain.setStatus(1);
        return courseFuture.create(courseDomain).thenApply(info -> {
            return info;
        });
    }
}
