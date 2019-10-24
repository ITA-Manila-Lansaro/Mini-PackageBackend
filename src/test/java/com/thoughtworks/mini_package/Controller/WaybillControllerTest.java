package com.thoughtworks.mini_package.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.is;
import com.thoughtworks.mini_package.Entity.Recipients;
import com.thoughtworks.mini_package.Entity.Waybill;
import com.thoughtworks.mini_package.Service.WaybillService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.sound.midi.Patch;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WaybillController.class)
@ActiveProfiles(profiles = "test")
class WaybillControllerTest {

    @MockBean
    WaybillService waybillService;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    private Waybill waybill = new Waybill();
    private List<Waybill> waybillList = new ArrayList<>();

    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private DateFormat idFormat = new SimpleDateFormat("yyyyMMddHHmmss");

    @BeforeEach
    void setUp() {
        this.waybill.setWayBillNumber(idFormat.format(new Date()));
        this.waybill.setRecipients(new Recipients());
        this.waybill.setStatus("Reservation");
        this.waybill.setTime(dateFormat.format(new Date()));
    }

    @Test
    void should_post_package() throws Exception {

        ResultActions result = mvc.perform(post("/waybill")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(waybill)));

        result.andExpect(status().isOk());
    }

    @Test
    void should_get_all_package () throws Exception {
        waybillList.add(waybill);
        waybillList.add(waybill);
        when(waybillService.getAllWaybill()).thenReturn(waybillList);
        ResultActions result = mvc.perform(get("/waybill"));
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void should_get_all_waybill_by_status() throws Exception{
        waybillList.add(waybill);
        waybillList.add(waybill);
        when(waybillService.getAllWaybillByStatus("Reservation")).thenReturn(waybillList);
        ResultActions result = mvc.perform(get("/waybill/getWayBillByStatus/Reservation"));

        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].status", is("Reservation")));
    }

    @Test
    void should_Book_a_pickup_when_patch () throws Exception {
        when(waybillService.bookAPickup("154551654", waybill)).thenReturn(java.util.Optional.ofNullable(waybill));
        ResultActions result = mvc.perform(patch("/waybill/bookAPickup/154551654")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(waybill)));

        result.andExpect(status().isOk());

    }

}