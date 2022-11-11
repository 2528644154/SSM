package com.powernode.controller;

import com.powernode.common.Constants;
import com.powernode.common.UploadBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/*下载*/
@Controller
@RequestMapping("/download")
public class DownloadController {

    @Autowired
    private UploadBean uploadBean;

    /*拷贝默认图片到服务器的指定位置*/
    /*@ModelAttribute主要作用是：将数据添加到模型对象中，用于视图页面显示*/
    /*@ModelAttribute该注解在方法上。被@ModelAttribute注解的方法会在Controller每个方法执行前被调用*/
    @ModelAttribute
    public void transferDefaultImg(HttpServletRequest request){
        try {
            //获取默认图片存放的文件夹目录
            String saveDirPath = uploadBean.getBaseUrl();
            //如果默认图片存在，则不执行拷贝
            File file1 = new File(saveDirPath + "/" + Constants.DEFAULT_HEAD_IMG);
            if(file1.exists()){
                return;
            }
            //如果文件夹不存在则创建
            File file2 = new File(saveDirPath);
            if(!file2.exists()){
                file2.mkdirs();
            }
            /*创建对象*/
            ServletContext application = request.getSession().getServletContext();
            /*使用application.getRealPath()来获取classes文件所在的目录，以此获取文件路径*/
            /*文件上传之后，会在target的resources下面生成一个上传图片所保存的地址*/
            String sourcePath = application.getRealPath("/resources/images/" + Constants.DEFAULT_HEAD_IMG);
            //读（能获取到文件上传的这个图片的地址）
            FileInputStream fis = new FileInputStream(sourcePath);
            //写（下载）
            FileOutputStream fos = new FileOutputStream(saveDirPath + "/" +Constants.DEFAULT_HEAD_IMG);
            byte[] car = new byte[1024];
            int len = -1;
            while ((len = fis.read(car)) != -1) {
                fos.write(car, 0, len);
            }
            fis.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/downloadImg.do")
    public void downloadImg(String imgUrl, HttpServletResponse response) throws IOException {
        if(imgUrl != null){
            String saveDirPath = uploadBean.getBaseUrl();
            FileInputStream fis = new FileInputStream(saveDirPath + "/" + imgUrl);
            int len = -1;
            byte[] car = new byte[1024];
            ServletOutputStream fos = response.getOutputStream();
            while ((len=fis.read(car)) != -1) {
                fos.write(car, 0, len);
            }
            fis.close();
            fos.close();
        }
    }

}
