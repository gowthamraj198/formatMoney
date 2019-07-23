package formatMoney.controller;

import formatMoney.base.FormatMoney;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static formatMoney.base.Messages.mandatoryMessage;

@RestController
@RequestMapping("/getmoney")
public class FormatMoneyController{

    FormatMoney formatMoney = new FormatMoney();


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