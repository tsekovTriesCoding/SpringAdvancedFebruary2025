package bg.softuni.mobileleleoffers.web;

import bg.softuni.mobileleleoffers.model.entity.Offer;
import bg.softuni.mobileleleoffers.repository.OfferRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static bg.softuni.mobileleleoffers.model.enums.EngineTypeEnum.DIESEL;
import static bg.softuni.mobileleleoffers.model.enums.EngineTypeEnum.HYBRID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "pesho@example.com",
        roles = {"USER", "ADMIN"}
)
public class OfferControllerIT {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetById() throws Exception {
        //Arrange
        var actualEntity = createTestOffer();

        //Act
        ResultActions result = this.mockMvc.perform(get("/offers/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON));

        //Assert
        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) actualEntity.getId())))
                .andExpect(jsonPath("$.description", is(actualEntity.getDescription())))
                .andExpect(jsonPath("$.mileage", is(actualEntity.getMileage())))
                .andExpect(jsonPath("$.price", is((int) actualEntity.getPrice())))
                .andExpect(jsonPath("$.engineType", is(DIESEL.toString())));
    }

    @Test
    public void testCreateOffer() throws Exception {
        MvcResult result = this.mockMvc.perform(post("/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "description": "test",
                                "mileage": 1000,
                                "price": 1200,
                                "engineType": "HYBRID"
                                }"""))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();
        Integer id = JsonPath.read(body, "$.id");

        Optional<Offer> createdOfferOpt = this.offerRepository.findById((long) id);

        Assertions.assertTrue(createdOfferOpt.isPresent());

        Offer createdOffer = createdOfferOpt.get();
        Assertions.assertEquals("test", createdOffer.getDescription());
        Assertions.assertEquals(1000, createdOffer.getMileage());
        Assertions.assertEquals(1200, createdOffer.getPrice());
        Assertions.assertEquals(HYBRID, createdOffer.getEngine());
    }

    @Test
    public void testDeleteOffer() throws Exception {
        var actualEntity = createTestOffer();

        this.mockMvc.perform(delete("/offers/{id}", actualEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Assertions.assertTrue(this.offerRepository.findById(actualEntity.getId()).isEmpty());
    }

    @Test
    public void testOfferNotFound() throws Exception {
        this.mockMvc.perform(get("/offers/{id}", 10000)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    private Offer createTestOffer() {
        return this.offerRepository.save(
                new Offer()
                        .setDescription("test")
                        .setEngine(DIESEL)
                        .setMileage(10000)
                        .setPrice(12000));
    }
}
