package com.myApp.VendorAPI.repository;

import com.myApp.VendorAPI.model.CloudVendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<CloudVendor,Integer> {

    CloudVendor findByVendorName(String vendorname);


}
