package formatMoney;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/getmoney")
public class FormatMoneyController {

    FormatMoney formatMoney = new FormatMoney();

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.TEXT_PLAIN_VALUE}
    )
    public String formatMoney(@RequestBody FormatMoneyModel formatMoneyModel) {
        try
        {

            if(formatMoneyModel.getMoney()==null || formatMoneyModel.getMoney()=="")
            {
                throw new IllegalArgumentException("Input cannot be empty!");
            }
            else
            {
                return formatMoney.moneyToStringFinal(formatMoneyModel.getMoney());

            }
        }
        catch(IllegalArgumentException e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }

    }

    @ExceptionHandler
    void handleIllegalArgumentException(IllegalArgumentException e, HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}