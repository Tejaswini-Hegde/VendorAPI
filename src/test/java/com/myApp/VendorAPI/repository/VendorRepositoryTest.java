package com.myApp.VendorAPI.repository;

import com.myApp.VendorAPI.model.CloudVendor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VendorRepositoryTest {

    @Autowired
    private VendorRepository vendorRepository;

    private CloudVendor cloudVendor;

    @BeforeEach
    void setUp() {
        // Create a new CloudVendor instance
        cloudVendor = new CloudVendor("Amazon", "Bangalore", "9353931174");
        vendorRepository.save(cloudVendor);  // Save it to the database
    }

    @AfterEach
    void tearDown() {
        vendorRepository.deleteAll();  // Cleanup the database after each test
    }

    @Test
    void test_FindVendorByName_Found() {
        // Fetch CloudVendor by its name
        CloudVendor foundVendor = vendorRepository.findByVendorName("Amazon");

        // Assert that the vendor was found and has the correct properties
        assertThat(foundVendor).isNotNull();
       // assertThat(foundVendor.getVendorName()).isEqualTo(cloudVendor.getVendorId());
        // Assert that the vendorId is auto-generated and is not null
        assertThat(foundVendor.getVendorId()).isNotNull();
        assertThat(foundVendor.getVendorAddress()).isEqualTo(cloudVendor.getVendorAddress());
        assertThat(foundVendor.getVendorPhone()).isEqualTo(cloudVendor.getVendorPhone());

    }

    // Test case for vendor not found (optional)
    @Test
    void test_FindVendorByName_NotFound() {
        CloudVendor foundVendor = vendorRepository.findByVendorName("GCP");

        // Assert that no vendor was found
        assertThat(foundVendor).isNull();
    }
}
