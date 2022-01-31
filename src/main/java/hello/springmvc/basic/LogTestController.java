package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController   //리턴값으로 뷰를 호출하지 않고 바로 스트링을 response로 던져줌
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); //현재 클래스 선택  @slf4j를 쓸 경우 이 코드가 자동적으로 주입되어 생략 가능

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "spring";

        System.out.println("name = " + name);

        log.trace(" trace log = {}", name);
        log.debug(" debug log = {}", name);
        log.info( " info log = {}", name);
        log.warn( " warn log = {}", name);
        log.error(" error log = {}", name);

        return "ok";
    }
}
