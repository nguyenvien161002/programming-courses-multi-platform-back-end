package nvv.dev.programingcourses.controller.blog;

import nvv.dev.programingcourses.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class BlogController {
    @GetMapping("/signup")
    public String showFormRegister(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        modelMap.addAttribute("pageTitle", "Register Form");
        return "register";
    }
}
