package com.bai.controller;

import com.bai.entity.Student;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
class Prods {
    private String selfNumber;
    private String chartNo;
    private String company;
    private String invoiceNumber;
    private String unitPrice;
    private String totalPrice;
    private String remarks;
}

// HttpServletRequest HttpServletResponse HttpSession
// Model ModelMap Map

@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("a")
    public String hello(Model model) {
        model.addAttribute("message", "this is a message");

        model.addAttribute("company", "csdner");
        model.addAttribute("title", "出货单");
        model.addAttribute("address", "中国");
        model.addAttribute("cursumerName", "客户");
        model.addAttribute("orderNo", "2432523234234234");
        model.addAttribute("deliveryAddr", "工业园");
        model.addAttribute("actualDeliveryDate", "20210526");
        model.addAttribute("phone", "18888888888");
        model.addAttribute("invoiceNo", "1567894");
        model.addAttribute("machineName", "25661615");

        Prods prods = Prods.builder().
                selfNumber("5555").chartNo("6666").
                company("csdner").invoiceNumber("2222").
                unitPrice("55").totalPrice("555").
                remarks("哈哈哈哈").build();
        List<Prods> list = new ArrayList<>();
        list.add(prods);
        model.addAttribute("prods", list);
        model.addAttribute("invoiceNumber", "22");
        model.addAttribute("totalPrice", "102");

        return "index";
    }

    @GetMapping(value = "b")
    public String eachArray(Model model) {
        Student[] userArray = new Student[10];
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(i);
            student.setName("宋小" + i);
            student.setPhone("1853790565" + i);
            student.setAddress("洛阳市涧西区XX路" + i + "号");
            userArray[i] = student;
        }
        model.addAttribute("userArray", userArray);
        return "array";
    }

    @GetMapping(value = "c")
    public String userList(Model model) {

        List<Student> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(1000 + i);
            student.setName("宋小" + i);
            student.setPhone("1364388173" + i);
            student.setAddress("郑州市金水区XX路" + i + "号");
            userList.add(student);
        }
        model.addAttribute("userList", userList);
        return "list";
    }

    @GetMapping(value = "d")
    public String eachMap(Model model) {
        Map<Integer, Object> userMaps = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setId(1000 + i);
            student.setName("宋小" + i);
            student.setPhone("1310179264" + i);
            student.setAddress("洛阳市涧西区XX路" + i + "号");
            userMaps.put(i, student);
        }
        model.addAttribute("userMaps", userMaps);
        return "map";
    }

    @GetMapping(value = "e")
    public String se(String a, HttpSession session, HttpServletRequest request, ModelAndView modelAndView, Model model, ModelMap map) {
        session.setAttribute("hello", "hello world");
        System.out.println(request.getSession().equals(session)); // true

        System.out.println(a); // null
        System.out.println(session); // org.apache.catalina.session.StandardSessionFacade@36b90eb2
        System.out.println(request); // org.apache.catalina.connector.RequestFacade@45444e19
        System.out.println(modelAndView);  // ModelAndView [view=[null]; model=null]
        System.out.println(model); // {modelAndView=ModelAndView [view=[null]; model=null], org.springframework.validation.BindingResult.modelAndView=org.springframework.validation.BeanPropertyBindingResult: 0 errors}
        System.out.println(map); // {modelAndView=ModelAndView [view=[null]; model=null], org.springframework.validation.BindingResult.modelAndView=org.springframework.validation.BeanPropertyBindingResult: 0 errors}
        System.out.println(model.getClass()); // BindingAwareModelMap
        System.out.println(map.getClass()); // BindingAwareModelMap
        return "session";
        // 其实ModelAndView（包含一个ModelMap属性）是不能注入的，需要在方法里面new出来，调用方法设置一些属性，然后这个方法返回ModelAndView类型的这个对象
        // ModelMap是一个LinkedHashMap，Map有的方法它都有
        // Model是一个接口
        // BindingAwareModelMap实现了Model，同时继承了ModelMap
    }

}
