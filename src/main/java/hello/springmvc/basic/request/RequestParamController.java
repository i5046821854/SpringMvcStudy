package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody  //얘를 쓰면 뷰를 안 찾고 응답 메시지로 스트링을 넘김
    @RequestMapping("request-param-v2")
    public String requestParamV2(@RequestParam("username") String name, @RequestParam("age") int age)
    {
        log.info("username = {}, age = {}", name, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-v3")
    public String requestParamV3(@RequestParam String username, @RequestParam int age) //requestparam의 이름과 변수명 일치 시 생략 가능
    {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-v4")
    public String requestParamV4(String username, int age) //String, int, integer 같은 경우 @requestparam도 생략 가능 (but 필요한가?)
    {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-required")
    public String requestParamRequired(@RequestParam(required = false) String username, @RequestParam(required = true) Integer age) //파라미터의 필수성 검증
    {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-default")
    public String requestParamDefault(@RequestParam(required = false, defaultValue = "guest") String username, @RequestParam(required = true, defaultValue = "1") int age) //파라미터의 디폴트 값
    {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) //파라미터를 한꺼번에 받음  //겹치는 파라미터가 잇을 경우 (key값이) -> multimap을 사용하면 배열로 반환
    {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) //modelAttribute가 오면 자동적으로 HelloData의 인스턴스 (helloData)를 생성한 다음 쿼리 파라미터로 들어온 것이 HelloData에 있을 경우 setter로 값을 설정해줌
    {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2( HelloData helloData) //modelAttribute 생략 가능 (RequestParam과 헷갈리면 어쩌나? String, INt, Integer와 같은 단순 타입 이외는 다 modelAttribute로 지정된다고 보면 된다 (argument resolver로 지정한 타입은 제외)
    {
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }


}
