package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mv = new ModelAndView("response/hello").addObject("data", "hello");   //resources 밑에 있는 경로로 감
        return mv;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello");
        return "response/hello";  //"response/hello"에 해당하는 뷰리졸버 호출
    }

    @RequestMapping("/response/hello")   //컨트롤러의 경로와 리턴 뷰이름이 같을 경우 return 생략 가능 (권장  x)
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello");
    }



}
