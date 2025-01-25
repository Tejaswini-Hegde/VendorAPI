package com.myApp.VendorAPI.service;

import com.myApp.VendorAPI.model.CloudVendor;
import com.myApp.VendorAPI.repository.VendorRepository;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VendorServiceTest {

//    It allows you to isolate the code you're testing by replacing dependencies
//    (like database connections, external services, etc.) with mock objects that
//    simulate their behavior.
    @Mock
    private VendorRepository vendorRepository;
    private  VendorService vendorService;
    private CloudVendor cloudVendor;

//    AutoCloseable is an interface in Java that represents a
//    resource that can be closed automatically after its use
    AutoCloseable autoCloseable;


    @BeforeEach
    void setUp() {
//   It provides a clean and consistent way to ensure that mock objects and their
//   resources are properly closed after the test runs, preventing memory leaks or issues with mock
//   state in subsequent tests.
    autoCloseable = MockitoAnnotations.openMocks(this);
    vendorService = new VendorService(vendorRepository);
    cloudVendor = new CloudVendor("Amazon","Bangalore","9353931174");
        cloudVendor.setVendorId(1);

    }

    @AfterEach
    void tearDown() throws Exception {

        autoCloseable.close();
    }


    @Test
    void testGetAllVendor() {

        when(vendorRepository.findAll()).thenReturn(Collections.singletonList(cloudVendor));

        assertThat(vendorService.getAllVendor()).isNotNull();
        assertThat(vendorService.getAllVendor().get(0).getVendorId()).isEqualTo(cloudVendor.getVendorId());
        assertThat(vendorService.getAllVendor().get(0).getVendorName()).isEqualTo(cloudVendor.getVendorName());

    }

    @Test
    void deleteVendorById() {

        mock(VendorRepository.class, Mockito.CALLS_REAL_METHODS);
        doAnswer(Answers.CALLS_REAL_METHODS).when(vendorRepository).deleteById(any());
//        ObjectAssert<ResponseEntity<String>> responseEntityObjectAssert = assertThat(vendorService.deleteVendorById(1));
        ResponseEntity<String> response = vendorService.deleteVendorById(1);

        // Assertions
        assertThat(response).isNotNull(); // Check the response entity is not null
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // Check HTTP status
        assertThat(response.getBody()).isEqualTo("Deleted");
    }

    @Test
    void getVendorById() {
        // Mock the repository call
        when(vendorRepository.findById(1)).thenReturn(Optional.of(cloudVendor));

        // Call the service method
        ResponseEntity<Optional<CloudVendor>> response = vendorService.getVendorById(1);

        // Assertions
        assertThat(response).isNotNull(); // Check the response entity is not null
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); // Check HTTP status

        // Check if the body is present
        Optional<CloudVendor> optionalVendor = response.getBody();
        assertThat(optionalVendor).isPresent(); // Ensure the optional contains a value

        // Access the CloudVendor object
        CloudVendor result = optionalVendor.get();

        // Assertions on the actual object
        assertThat(result.getVendorId()).isEqualTo(1);
        assertThat(result.getVendorName()).isEqualTo("Amazon");
        assertThat(result.getVendorAddress()).isEqualTo("Bangalore");
        assertThat(result.getVendorPhone()).isEqualTo("9353931174");

        // Verify the interaction with the repository
        verify(vendorRepository, times(1)).findById(1);
    }

    @Test
    void testAddVendor() {
        when(vendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(vendorService.addVendor(cloudVendor)).isNotNull();
    }

    @Test
    void testGetVendorByName() {
        // Mock the repository call
        when(vendorRepository.findByVendorName("Amazon")).thenReturn(cloudVendor);

        // Call the service method
        CloudVendor result = vendorService.getVendorByName("Amazon");

        // Assertions
        assertThat(result).isNotNull();
        assertThat(result.getVendorName()).isEqualTo("Amazon");
        assertThat(result.getVendorAddress()).isEqualTo("Bangalore");
        assertThat(result.getVendorPhone()).isEqualTo("9353931174");

        // Verify the repository interaction
        verify(vendorRepository, times(1)).findByVendorName("Amazon");

    }

    @Test
    void testUpdateVendor() {

        when(vendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(vendorService.updateVendor(cloudVendor)).isNotNull();

    }
}