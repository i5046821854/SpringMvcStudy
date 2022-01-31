package hello.springmvc.basic.requestmapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
    public class MappingController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping(value = {"/hello-basic", "/hello-second"}, method = RequestMethod.GET)
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping(value = {"/hello-basic2", "/hello-second2"})
    public String helloBasic2(){
        log.info("helloBasic");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")  //path variable 사용
    public String MappingPath(@PathVariable("userId") String data){  //변수 이름과 path variable의 이름이 같으면 생략해서 @PathVariable String userId 이런 식으로 사용 가능
        log.info("path var = {}", data);
        return "ok";
    }

    @GetMapping("/mapping/{userId}/orders/{orderId}")  //path variable 사용
    public String MappingPath(@PathVariable("userId") String data, @PathVariable Long orderId){  //변수 이름과 path variable의 이름이 같으면 생략해서 @PathVariable String userId 이런 식으로 사용 가능
        log.info("path var = {}, orderId = {}", data, orderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")  //쿼리 파라미터의 정보를 필터링
    public String MappingParam(){
        log.info("param filtered");
        return "ok";
    }

    @GetMapping(value = "/mapping-header", headers = "mode=debug")  //헤더의 정보로 필터링
    public String MappingHeader(){
        log.info("header filtered");
        return "ok";
    }


    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    @PostMapping(value = "/mapping-consume", consumes = "application/json")  //request 헤더의 content-type 정보로 필터링
    public String Mappingconsumes(){
        log.info("consume filtered");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    @PostMapping(value = "/mapping-produce", consumes = "text/html")  //request 헤더의 accept 정보로 필터링
    public String MappingProduces(){
        log.info("consume filtered");
        return "ok";
    }

}
