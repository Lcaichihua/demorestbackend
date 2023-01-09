package pe.com.caichihua.backrest.demorestbackend.controller.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoREST {

    @GetMapping
    public String demo() {
        return "Demo ok";
    }

}
