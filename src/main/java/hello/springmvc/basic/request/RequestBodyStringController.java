package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
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
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @RequestMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        ServletInputStream is  = req.getInputStream();
        String messageBody = StreamUtils.copyToString(is, StandardCharsets.UTF_8);

        log.info("messagebody = {}", messageBody);

        res.getWriter().write("ok");

    }

    @RequestMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream is, Writer writer) throws ServletException, IOException{

        String messageBody = StreamUtils.copyToString(is, StandardCharsets.UTF_8);
        log.info("messagebody = {}", messageBody);
        writer.write("ok");
    }

    @RequestMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws ServletException, IOException{   //httpEntity를 이용하여 body 에 바이트 코드를 <string>의 형식으로 넣어줌

        String messageBody = httpEntity.getBody();
        log.info("messagebody = {}", messageBody);
        return new HttpEntity<>("ok");
    }

    @ResponseBody
    @RequestMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws ServletException, IOException{   // 리쿼스트의 body 부분을 자동 매핑

        log.info("messagebody = {}", messageBody);
        return "ok";
    }



}
