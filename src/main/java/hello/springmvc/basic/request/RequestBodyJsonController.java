package hello.springmvc.basic.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException{
        ServletInputStream is = req.getInputStream();
        String messagebody = StreamUtils.copyToString(is, StandardCharsets.UTF_8);

        log.info("message body = {}", messagebody);
        HelloData helloData = objectMapper.readValue(messagebody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messagebody) throws JsonProcessingException {

        log.info("message body = {}", messagebody);
        HelloData helloData = objectMapper.readValue(messagebody, HelloData.class);
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }



    @RequestMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {  //httpConverter가 메시지를 우리가 @requestbody에 맞는 형식으로 반환, @requestbody를 생략하면 modelAttribute가 되므로
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @RequestMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) {  //httpEntitiy로 바디를 한번에 받고 형변환
        HelloData helloData = httpEntity.getBody();
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData helloData){  //responseData가 helloData를 json으로 바꾼 뒤 response의 바디에 넣어줌
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return helloData;
    }

}
