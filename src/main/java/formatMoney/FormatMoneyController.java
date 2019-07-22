package formatMoney;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static formatMoney.Messages.mandatoryMessage;

@RestController
@RequestMapping("/getmoney")
public class FormatMoneyController{

    FormatMoney formatMoney = new FormatMoney();

    @GetMapping
            (
                    produces = {MediaType.TEXT_PLAIN_VALUE}
            )
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @PostMapping(
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public String formatMoney(@RequestParam(name = "money") String money, Model model) {
        model.addAttribute("money", money);
        try {

            if (money == null || money == "") {
                throw new IllegalArgumentException(mandatoryMessage);
            } else {
                return formatMoney.moneyToStringFinal(money);

            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}