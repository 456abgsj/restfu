package com.itguigu.mvc.controller;

import com.itguigu.mvc.bean.Employee;
import com.itguigu.mvc.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;

@Controller
public class EmployeeController {
    //hello
    //对于requestmapping里面的配置是对应的映射当符合对应映射时返回索引值
    //当启动tomcat服务器时默认访问的是tomcat服务器开始设置的初始值http://localhost/restful_war_exploded/
    //当对tomcat服务器进行路径设置时http://localhost/restful_war_exploded/hah/
    //但是现在我们是"/hah/"http://localhost/restful_war_exploded/hah/hah/这个时候才可以访问成功
    //斜杠表示对映射不做任何的处理就是访问tomcat的原始路径如果对应上了就返回返回值
    //切记/就表示原始路径http://localhost/restful_war_exploded/最后的一个斜杠当你要在requestmapping斜杠后面添加东西的时候只有在原始
    //路径后面加上对应添加的值才可以进行访问
    @RequestMapping("/")
    public String index1(){
        return "index";
    }
     EmployeeDao employeedao=new EmployeeDao();
    @RequestMapping(value = "/message", method= RequestMethod.GET)
    public String mess(Model model){
        Collection<Employee> employeeList = employeedao.getAll();
        model.addAttribute("employeeList",employeeList);
        return "员工信息";
    }
    @RequestMapping(value = "/message/{id}",method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeedao.delete(id);
        return "redirect:/message";
    }
}
