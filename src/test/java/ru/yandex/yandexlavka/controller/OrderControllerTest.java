package ru.yandex.yandexlavka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;
import ru.yandex.yandexlavka.entity.converter.OrderConverter;
import ru.yandex.yandexlavka.service.OrderService;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
//@WebMvcTest
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //    @Before
    @MockBean
    private OrderService orderService;

    private static URI jsonDirectory;
    private static final String JSON_DIRECTORY_NAME = "jsons";


    @BeforeAll
    public static void setUp() throws URISyntaxException {
        jsonDirectory = OrderControllerTest.class.getClassLoader()
                .getResource(JSON_DIRECTORY_NAME)
                .toURI()
                .resolve(".")
                .resolve(JSON_DIRECTORY_NAME + "/");
    }

    @Test
    public void getOrderById() throws Exception {
        List<String> jsons = List.of("order_1.json","order_with_completed_time.json");
        for(String json : jsons){
            OrderConverter orderConverter = new OrderConverter();
            URI file = jsonDirectory.resolve(json);
            OrderDto orderDto = loadOrder(file);
            Order order = orderConverter.toEntity(orderDto);

            Mockito.when(orderService.getOrderById(order.getId())).thenReturn(Optional.of(order));
            MvcResult result = mockMvc.perform(get("/orders/" + order.getId()))
                    .andExpect(status().isOk())
                    .andReturn();

            String responseJson = result.getResponse().getContentAsString();
            String jsonExpected = readFileToString(file);
            JSONAssert.assertEquals(jsonExpected, responseJson, false);
        }
    }

    @Test
    public void notFoundOrderById() throws Exception {
        Mockito.when(orderService.getOrderById(Mockito.any())).thenReturn(Optional.empty());
        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void badId() throws Exception {
        mockMvc.perform(get("/orders/asd"))
                .andExpect(status().isBadRequest());
    }


//    @Test
//    public void test() throws JSONException {
//        String expected = "[{\"id\":1},{\"id\":2}]";
//        String actual = "[{\"id\":2},{\"id\":1}]";
//        JSONAssert.assertEquals(expected,actual,false);
//    }



    private List<OrderDto> loadOrders(URI file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(new File(file), OrderDto[].class));
    }

    private OrderDto loadOrder(URI file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(file), OrderDto.class);
    }

    private String readFileToString(URI file) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Files.copy(Path.of(file), out);
        return out.toString(StandardCharsets.UTF_8);
    }


}

