package com.myApp.VendorAPI.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.myApp.VendorAPI.model.CloudVendor;
import com.myApp.VendorAPI.service.VendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



@WebMvcTest(VendorController.class)
class VendorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VendorService vendorService;
    CloudVendor cloudVendor1;
    CloudVendor cloudVendor2;
    List<CloudVendor> cloudVendorList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        cloudVendor1 = new CloudVendor("Amazon","Bangalore","9353931174");
        cloudVendor1.setVendorId(Integer.parseInt("1"));
        cloudVendor2 = new CloudVendor("GCP","Delhi","9482297233");
        cloudVendor2.setVendorId(Integer.parseInt("2"));
        cloudVendorList.add(cloudVendor1);
        cloudVendorList.add(cloudVendor2);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetAllVendor() throws Exception {

        when(vendorService.getAllVendor()).thenReturn(cloudVendorList);
        this.mockMvc.perform(get("/CloudVendor/All")).andDo(print()).andExpect(status().isOk()).andReturn();
    }

    @Test
    void testGetVendorById() throws Exception {

        // Directly wrap cloudVendor1 in an Optional without reinitializing it
        Optional<CloudVendor> optionalCloudVendor = Optional.of(cloudVendor1);

        // Wrap the Optional in a ResponseEntity
        ResponseEntity<Optional<CloudVendor>> responseEntity = ResponseEntity.ok(optionalCloudVendor);

        // Mock the service method to return the wrapped value
            when(vendorService.getVendorById(1)).thenReturn(responseEntity);
            this.mockMvc.perform(get("/CloudVendor/search/1"))
                    .andDo(print())
                    .andExpect(status().isOk()).andReturn();


    }

    @Test
    void getVendorByName() throws Exception {
        // Mock the service method to return the wrapped value
        when(vendorService.getVendorByName("GCP")).thenReturn(cloudVendor2);
        this.mockMvc.perform(get("/CloudVendor/searchVendor/GCP"))
                .andDo(print())
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    void testAddVendor() throws Exception {
        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow= mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(cloudVendor1);


        when(vendorService.addVendor(cloudVendor1)).thenReturn(cloudVendor1);

        this.mockMvc.perform(MockMvcRequestBuilders.post("/CloudVendor/add")
                        .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                        .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testUpdateVendor() throws Exception {

        ObjectMapper mapper=new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE,false);
        ObjectWriter ow= mapper.writer().withDefaultPrettyPrinter();
        String requestJson = mapper.writeValueAsString(cloudVendor1);


        when(vendorService.updateVendor(cloudVendor1)).thenReturn(cloudVendor1);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/CloudVendor/update")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    void testDeleteVendorById() throws Exception {

        when(vendorService.deleteVendorById(1)).thenReturn(ResponseEntity.ok("Deleted"));
        this.mockMvc.perform(delete("/CloudVendor/delete/1")).
                andDo(print()).andExpect(status().isOk());
    }
}