package ra.web.baitap17bai2;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/calculator")
public class CalculatorController {l
    @GetMapping("/calculator")
    public String index() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam("number1") double number1,
                            @RequestParam("number2") double number2,
                            Model model) {
        double result = number1 + number2;
        model.addAttribute("result", result);
        return "index";
    }


}