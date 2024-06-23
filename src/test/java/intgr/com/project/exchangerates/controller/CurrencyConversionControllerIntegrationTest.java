package intgr.com.project.exchangerates.controller;

import com.project.exchangerates.CurrencyConversionApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.not;

@SpringBootTest(classes = CurrencyConversionApplication.class)
@AutoConfigureMockMvc
public class CurrencyConversionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenValidCurrency_shouldReturnConversion() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/conversions")
                        .param("amount", "100")
                        .param("from", "EUR")
                        .param("to", "USD")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(not("")));
    }

    @Test
    public void whenInvalidCurrencyCodeFrom_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/conversions")
                        .param("amount", "100")
                        .param("from", "INVALID")
                        .param("to", "EUR")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid currency code from: INVALID"));
    }

    @Test
    public void whenInvalidCurrencyCodeTo_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/conversions")
                        .param("amount", "100")
                        .param("from", "USD")
                        .param("to", "INVALID")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid currency code to: INVALID"));
    }

}

