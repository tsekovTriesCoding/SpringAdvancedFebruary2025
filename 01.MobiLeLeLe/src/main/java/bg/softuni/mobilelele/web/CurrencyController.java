package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.model.dto.ConversionResultDTO;
import bg.softuni.mobilelele.service.ExRateService;
import bg.softuni.mobilelele.service.exception.ApiObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CurrencyController {
    private final ExRateService exRateService;

    public CurrencyController(ExRateService exRateService) {
        this.exRateService = exRateService;
    }

    @GetMapping("/api/convert")
    public ResponseEntity<ConversionResultDTO> convert(
            @RequestParam("from") String from,
            @RequestParam("to") String to,
            @RequestParam("amount") BigDecimal amount) {

        BigDecimal result = this.exRateService.convert(from, to, amount);

        return ResponseEntity.ok(new ConversionResultDTO(from, to, amount, result));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ApiObjectNotFoundException.class)
    @ResponseBody
    public NotFoundErrorErrorInfo handleApiObjectNotFoundException(ApiObjectNotFoundException e) {
        return new NotFoundErrorErrorInfo("NOT_FOUND", e.getId());
    }

    public record NotFoundErrorErrorInfo(String code, Object id) {

    }
}
