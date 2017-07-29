package qa.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ozgur on 7/28/17.
 */

@Controller
public class TestController {

    @RequestMapping("/")
    public String greeting(Model model) {
        model.addAttribute("_name", "xxxxx");
        return "test";
    }

}
