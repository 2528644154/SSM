package com.powernode.controller;

import com.powernode.common.Result;
import com.powernode.common.UploadBean;
import com.powernode.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*文件上传*/
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadBean uploadBean;
    @Autowired
    private AccountService accountService;

    @RequestMapping("/uploadFile.do")
    public Result uploadFile(MultipartFile headImg, HttpServletRequest request) throws IOException {
        if(headImg == null){
            return new Result(-1,"文件上传不能为空");
        }
        //获取到文件上传的名字
        String oriName = headImg.getOriginalFilename();
        if("".equals(oriName)){
            return new Result(-1, "文件不能为空");
        }
        //获取文件的后缀名
        //                     截取                         从.开始截取
        String ext = oriName.substring(oriName.lastIndexOf("."));
        //动态生成文件保存的名字
        String uuid = UUID.randomUUID().toString();
        //确认文件最终上传的磁盘目录是存在的
        String saveDirPath = uploadBean.getBaseUrl();
        File file = new File(saveDirPath);
        if(!file.exists()){
            file.mkdirs();
        }
        //开始文件上传
        File finalFile = new File(saveDirPath + "/" + uuid + ext);
        //上传
        headImg.transferTo(finalFile);
        //将图片地址保存到数据库的表中
        HttpSession session = request.getSession();
        Result result = accountService.modifyAccountImgUrl(uuid+ext,session);
        //将文件的地址返回给调用者
        if(result.getCode() == 200){
            result.setData(uuid+ext);
        }
        return result;
    }

}
