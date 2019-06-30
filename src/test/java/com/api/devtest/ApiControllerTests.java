package com.api.devtest;

import com.api.devtest.common.DataMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.*;
import static org.hibernate.cache.spi.support.AbstractReadWriteAccess.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiControllerTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Before
    public void before() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void test01getAnnualTopSumAmtCust() throws Exception {
        mockMvc.perform(get("/customer/annual/top"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", iterableWithSize(2)))
                .andExpect(jsonPath("$[0]['year']", equalTo(2018)))
                .andExpect(jsonPath("$[0]['name']", equalTo("테드")))
                .andExpect(jsonPath("$[0]['acctNo']", equalTo("11111114")))
                .andExpect(jsonPath("$[0]['sumAmt']", equalTo(28992000)))
                .andExpect(jsonPath("$[1]['year']", equalTo(2019)))
                .andExpect(jsonPath("$[1]['name']", equalTo("에이스")))
                .andExpect(jsonPath("$[1]['acctNo']", equalTo("11111112")))
                .andExpect(jsonPath("$[1]['sumAmt']", equalTo(40998400)))
        ;
    }

    @Test
    public void test02getAnnualNoTrnCust() throws Exception {
        mockMvc.perform(get("/customer/annual/missing"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", iterableWithSize(4)))
                .andExpect(jsonPath("$[0]['year']", equalTo(2018)))
                .andExpect(jsonPath("$[0]['name']", equalTo("사라")))
                .andExpect(jsonPath("$[0]['acctNo']", equalTo("11111115")))
                .andExpect(jsonPath("$[1]['year']", equalTo(2018)))
                .andExpect(jsonPath("$[1]['name']", equalTo("제임스")))
                .andExpect(jsonPath("$[1]['acctNo']", equalTo("11111118")))
                .andExpect(jsonPath("$[2]['year']", equalTo(2019)))
                .andExpect(jsonPath("$[2]['name']", equalTo("테드")))
                .andExpect(jsonPath("$[2]['acctNo']", equalTo("11111114")))
                .andExpect(jsonPath("$[3]['year']", equalTo(2019)))
                .andExpect(jsonPath("$[3]['name']", equalTo("제임스")))
                .andExpect(jsonPath("$[3]['acctNo']", equalTo("11111118")))
        ;
    }

    @Test
    public void test03getAnnualMngmBrnTrnAmt() throws Exception {
        mockMvc.perform(get("/trnamt/branch/annual"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", iterableWithSize(2)))
                .andExpect(jsonPath("$[0]['year']", equalTo(2018)))
                .andExpect(jsonPath("$[0]['dataList']", iterableWithSize(4)))
                .andExpect(jsonPath("$[0]['dataList'][0]['brName']", equalTo("분당점")))
                .andExpect(jsonPath("$[0]['dataList'][0]['brCode']", equalTo("B")))
                .andExpect(jsonPath("$[0]['dataList'][0]['sumAmt']", equalTo(38500000)))
                .andExpect(jsonPath("$[0]['dataList'][1]['brName']", equalTo("판교점")))
                .andExpect(jsonPath("$[0]['dataList'][1]['brCode']", equalTo("A")))
                .andExpect(jsonPath("$[0]['dataList'][1]['sumAmt']", equalTo(20510000)))
                .andExpect(jsonPath("$[0]['dataList'][2]['brName']", equalTo("강남점")))
                .andExpect(jsonPath("$[0]['dataList'][2]['brCode']", equalTo("C")))
                .andExpect(jsonPath("$[0]['dataList'][2]['sumAmt']", equalTo(20234567)))
                .andExpect(jsonPath("$[0]['dataList'][3]['brName']", equalTo("잠실점")))
                .andExpect(jsonPath("$[0]['dataList'][3]['brCode']", equalTo("D")))
                .andExpect(jsonPath("$[0]['dataList'][3]['sumAmt']", equalTo(14000000)))
                .andExpect(jsonPath("$[1]['year']", equalTo(2019)))
                .andExpect(jsonPath("$[1]['dataList']", iterableWithSize(4)))
                .andExpect(jsonPath("$[1]['dataList'][0]['brName']", equalTo("판교점")))
                .andExpect(jsonPath("$[1]['dataList'][0]['brCode']", equalTo("A")))
                .andExpect(jsonPath("$[1]['dataList'][0]['sumAmt']", equalTo(66800000)))
                .andExpect(jsonPath("$[1]['dataList'][1]['brName']", equalTo("분당점")))
                .andExpect(jsonPath("$[1]['dataList'][1]['brCode']", equalTo("B")))
                .andExpect(jsonPath("$[1]['dataList'][1]['sumAmt']", equalTo(45400000)))
                .andExpect(jsonPath("$[1]['dataList'][2]['brName']", equalTo("강남점")))
                .andExpect(jsonPath("$[1]['dataList'][2]['brCode']", equalTo("C")))
                .andExpect(jsonPath("$[1]['dataList'][2]['sumAmt']", equalTo(19500000)))
                .andExpect(jsonPath("$[1]['dataList'][3]['brName']", equalTo("잠실점")))
                .andExpect(jsonPath("$[1]['dataList'][3]['brCode']", equalTo("D")))
                .andExpect(jsonPath("$[1]['dataList'][3]['sumAmt']", equalTo(6000000)))
        ;
    }

    @Test
    public void test04getMngmBrnTrnAmt() throws Exception {

        mockMvc.perform(put("/transfer/branch")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"brCode\":\"B\", \"afterBrCode\":\"A\"}"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        mockMvc.perform(delete("/transfer/branch")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"brCode\":\"B\"}"))
                .andDo(print())
                .andExpect(status().isOk())
        ;

        mockMvc.perform(get("/trnamt/branch")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"brName\":\"강남점\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$['brName']", equalTo("강남점")))
                .andExpect(jsonPath("$['brCode']", equalTo("C")))
                .andExpect(jsonPath("$['sumAmt']", equalTo(39734567)))
        ;

        mockMvc.perform(get("/trnamt/branch")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"brName\":\"분당점\"}"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$['code']", equalTo("404")))
                .andExpect(jsonPath("$['message']", containsString("brCode")))
        ;
    }

}
