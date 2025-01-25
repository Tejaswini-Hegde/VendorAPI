package com.myApp.VendorAPI.service;

import com.myApp.VendorAPI.model.CloudVendor;
import com.myApp.VendorAPI.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    VendorRepository vendorRepository;

    // Created this constructor while writing test case
    public VendorService(VendorRepository vendorRepository) {

        this.vendorRepository = vendorRepository;
    }

    public List<CloudVendor> getAllVendor() {
        return vendorRepository.findAll();
    }

    public ResponseEntity<String> deleteVendorById(int vendorID) {

        vendorRepository.deleteById(vendorID);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }

    public ResponseEntity<Optional<CloudVendor>> getVendorById(int vendorID) {
        return new ResponseEntity<>(vendorRepository.findById(vendorID),HttpStatus.OK);
    }

    public CloudVendor addVendor(CloudVendor cloudVendor) {

        return vendorRepository.save(cloudVendor);
    }


    public CloudVendor getVendorByName(String vendorname) {

        return vendorRepository.findByVendorName(vendorname);
    }

    public CloudVendor updateVendor(CloudVendor cloudVendor) {

        return vendorRepository.save(cloudVendor);
    }
}
