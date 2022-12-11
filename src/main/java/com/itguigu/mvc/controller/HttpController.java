package com.itguigu.mvc.controller;

import com.itguigu.mvc.bean.Employee;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class HttpController {
    //返回请求的参数信息
    @RequestMapping("/testRequestmapping")
    public String testRequestmapping(@RequestBody String requestbody){
        System.out.println("requestbody:"+requestbody);
        return "success";
    }
    //这种方法可以返回请求头和请求体
    @RequestMapping("/testRequestEnity")
    public String testRequestEnity(RequestEntity <String >requestEntity){
        System.out.println("请求头:"+requestEntity.getHeaders());
        System.out.println("请求体:"+requestEntity.getBody());
        return "success";
    }
    //向域对象中反映数据
    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse response) throws IOException {
        response.getWriter().println("hello，response");
    }

    @RequestMapping("/testResponseBody1")
    @ResponseBody//当加上这个注解的时候success作为响应体反应到页面
    public String testResponseBody(){
        return "success";
    }

    @RequestMapping("/testResponseBody2")
    public String testResponseBody1(){
        return "success";
    }

    //向域对象中返回实体类数据
    @RequestMapping("/testResponseUser")
    @ResponseBody
    //使用这种方法的前提是先要导入jackson jar包 其次开启  <mvc:annotation-driven />
    public Employee testResponseUser(){
        Employee employee=new Employee(1,"王浩宇","1980726277@qq.com",1);
        return employee;
    }

    @RequestMapping("/testAjax")
    @ResponseBody
    public String testAjax(String username, String password){
        System.out.println("username:"+username+",password:"+password);
        return "hello,ajax";
    }

    //@RestController注解是一个复合注解作用为为类加上Controlller注解并且为每个方法都加上ResponseBody注解

    @RequestMapping("/testdd")
    public String testdd(){
        return "index2";
    }

    @RequestMapping("/testdown")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws
            IOException {
//获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        String realPath = servletContext.getRealPath("/static/img/libai.jpg");
//创建输入流
        InputStream is = new FileInputStream(realPath);
//创建字节数组
        byte[] bytes = new byte[is.available()];
//将流读到字节数组中
        is.read(bytes);
//创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
//设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=libai.jpg");
//设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
//创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers,
                statusCode);
//关闭输入流
        is.close();
        return responseEntity;
    }

    @RequestMapping("/testlanjieqi")
    public String testlanjieqi(){
        return "success";
    }


    //异常处理器
    @RequestMapping("/testerror")
    public String testerror(){
        System.out.println(1/0);
        return "success";
    }

}
