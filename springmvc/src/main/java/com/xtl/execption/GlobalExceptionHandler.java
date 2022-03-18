package com.xtl.execption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局处理异常
 * @author 31925
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        System.out.println("==============");
        request.setAttribute("msg", "文件超过了指定大小，上传失败！");
        return "redirect:/params.jsp";
    }
}
