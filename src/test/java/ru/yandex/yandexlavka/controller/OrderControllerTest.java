package ru.yandex.yandexlavka.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
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
import java.util.ArrayList;
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

    private static List<String> jsonOrders;

    private static URI jsonDirectory;
    private static final String JSON_DIRECTORY_NAME = "jsons";


    @BeforeAll
    public static void setUp() throws URISyntaxException {
        jsonDirectory = OrderControllerTest.class.getClassLoader()
                .getResource(JSON_DIRECTORY_NAME)
                .toURI()
                .resolve(".")
                .resolve(JSON_DIRECTORY_NAME + "/");

        jsonOrders = List.of("order_1.json", "order_with_completed_time.json");
    }


    @Nested
    class GetOrderByIdTest {
        @Test
        public void getOrderById() throws Exception {
            List<String> jsonValues = readJsons(jsonOrders);
            OrderConverter orderConverter = new OrderConverter();
            for (String json : jsonValues) {
                OrderDto orderDto = loadOrder(json);
                Order order = orderConverter.toEntity(orderDto);

                Mockito.when(orderService.getOrderById(order.getId())).thenReturn(Optional.of(order));
                MvcResult result = mockMvc.perform(get("/orders/" + order.getId()))
                        .andExpect(status().isOk())
                        .andReturn();

                String responseJson = result.getResponse().getContentAsString();
                JSONAssert.assertEquals(json, responseJson, false);
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
    }

    @Nested
    class GetOrdersTest{

        @Test
        public void getOrders() throws Exception {
            List<String> jsonValues = readJsons(jsonOrders);
            OrderConverter orderConverter = new OrderConverter();

            List<Order> orders = loadOrders(jsonValues).stream()
                    .map(orderConverter::toEntity)
                    .toList();
            Mockito.when(orderService.getOrders(Mockito.any(),Mockito.any())).thenReturn(orders);
            MvcResult result = mockMvc.perform(get("/orders")
                            .param("offset","0")
                            .param("limit","3"))
                    .andExpect(status().isOk())
                    .andReturn();

            String actualJson = result.getResponse().getContentAsString();
            String expected = "[" + String.join(",", jsonValues) + "]";

            JSONAssert.assertEquals(expected, actualJson, false);
        }

        @Test
        public void emptyList() throws Exception {
            Mockito.when(orderService.getOrders(Mockito.any(),Mockito.any())).thenReturn(List.of());
            MvcResult result = mockMvc.perform(get("/orders"))
                    .andExpect(status().isOk())
                    .andReturn();

            String actualJson = result.getResponse().getContentAsString();
            String expected = "[]";
            JSONAssert.assertEquals(expected, actualJson, false);
        }

        @Test
        public void badParamValue() throws Exception {
            Mockito.when(orderService.getOrders(Mockito.any(),Mockito.any())).thenReturn(List.of());
            mockMvc.perform(get("/orders")
                            .param("limit", "-1")
                            .param("offset","0"))
                    .andExpect(status().isBadRequest());

            mockMvc.perform(get("/orders")
                            .param("limit", "ads"))
                    .andExpect(status().isBadRequest());
        }


    }


//    @Test
//    public void test() throws JSONException {
//        String expected = "[{\"id\":1},{\"id\":2}]";
//        String actual = "[{\"id\":2},{\"id\":1}]";
//        JSONAssert.assertEquals(expected,actual,false);
//    }

    private List<String> readJsons(List<String> jsonNames) throws IOException {
        List<String> res = new ArrayList<>();
        for(String json : jsonNames){
            URI jsonUri = jsonDirectory.resolve(json);
            res.add(readFileToString(jsonUri));
        }
        return res;
    }

    private String readFileToString(URI file) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Files.copy(Path.of(file), out);
        return out.toString(StandardCharsets.UTF_8);
    }

    private List<OrderDto> loadArrayOfOrders(URI file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return Arrays.asList(objectMapper.readValue(new File(file), OrderDto[].class));
    }

    private List<OrderDto> loadOrdersFromFiles(List<String> files) throws IOException {
        List<OrderDto> res = new ArrayList<>();
        for (String json : files) {
            URI file = jsonDirectory.resolve(json);
            res.add(loadOrder(file));
        }
        return res;
    }

    private OrderDto loadOrder(URI file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(file), OrderDto.class);
    }

    private List<OrderDto> loadOrders(List<String> values) throws IOException {
        List<OrderDto> res = new ArrayList<>();
        for(String v : values){
            res.add(loadOrder(v));
        }
        return res;
    }

    private OrderDto loadOrder(String value) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(value, OrderDto.class);
    }


}

