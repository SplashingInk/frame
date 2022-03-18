package com.xtl.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.UUID;

/**
 * 处理文件上传和下载
 * @author 31925
 */
@Controller
public class FileController {
    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") List<CommonsMultipartFile> files, HttpServletRequest request) throws IOException {
        //上传路径保存设置
        String path = request.getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()){
            realPath.mkdir();
        }
        //上传文件地址
        System.out.println("上传文件保存地址："+realPath);
        for(CommonsMultipartFile file:files){
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && !originalFilename.equals("")) {
                    // 对文件名做加UUID值处理
                    originalFilename = UUID.randomUUID() + "_" + originalFilename;
                    //通过CommonsMultipartFile的方法直接写文件
                    file.transferTo(new File(realPath +"/"+originalFilename));
            }
        }
        return "redirect:/index.jsp";
    }

    @GetMapping("download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename) throws IOException {
        //下载文件的路径(这里绝对路径)
        String filepath= "E:/Wallpaper/"+filename;
        File file =new File(filepath);
        //创建字节输入流，这里不实用Buffer类
        InputStream in = new FileInputStream(file);
        //available:获取输入流所读取的文件的最大字节数
        byte[] body = new byte[in.available()];
        //把字节读取到数组中
        in.read(body);
        //设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置响应状态
        HttpStatus statusCode = HttpStatus.OK;
        in.close();
        return new ResponseEntity<byte[]>(body, headers, statusCode);
    }
}
