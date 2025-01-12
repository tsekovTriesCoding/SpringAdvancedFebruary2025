package bg.softuni.mobilelele.web;

import bg.softuni.mobilelele.service.ExRateService;
import bg.softuni.mobilelele.service.exception.ApiObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CurrencyControllerIT {
    @Autowired
    private MockMvc mockMvc;

    // Use only if really needed.
    @MockitoBean
    private ExRateService mockExRateService;

    @Test
    public void testConvert() throws Exception {
        String from = "SUD";
        String to = "ZWD";
        BigDecimal amount = new BigDecimal("100");
        BigDecimal expectedResult = new BigDecimal("50");

        when(this.mockExRateService.convert(from, to, amount)).thenReturn(expectedResult);

        this.mockMvc.perform(get("/api/convert")
                        .param("from", from)
                        .param("to", to)
                        .param("amount", String.valueOf(amount.intValue()))
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.from").value(from))
                .andExpect(jsonPath("$.to").value(to))
                .andExpect(jsonPath("$.amount").value(amount))
                .andExpect(jsonPath("$.result").value(expectedResult));
    }

    @Test
    public void testConversionNotFound() throws Exception {
        String from = "SUD";
        String to = "ZWD";
        BigDecimal amount = new BigDecimal("100");

        when(this.mockExRateService.convert(from, to, amount))
                .thenThrow(new ApiObjectNotFoundException("Test message", "TestId"));

        this.mockMvc.perform(get("/api/convert")
                .param("from", from)
                .param("to", to)
                .param("amount", String.valueOf(amount.intValue()))
        ).andExpect(status().isNotFound());
    }
}
