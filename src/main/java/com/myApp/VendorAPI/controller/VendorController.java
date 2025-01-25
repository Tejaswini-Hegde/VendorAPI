package com.myApp.VendorAPI.controller;

import com.myApp.VendorAPI.model.CloudVendor;
import com.myApp.VendorAPI.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/CloudVendor")
public class VendorController {

    @Autowired
    VendorService vendorService;

   @GetMapping("/All")
    public List<CloudVendor> getAllVendor(Integer vendorId){
       return vendorService.getAllVendor();

    }

    @GetMapping("/search/{vendorID}")
    public ResponseEntity<Optional<CloudVendor>> getVendorById(@PathVariable(name = "vendorID") int vendorID)
    {
        return vendorService.getVendorById(vendorID);
    }
    @GetMapping("/searchVendor/{vendorname}")
    public CloudVendor getVendorByName(@PathVariable(name = "vendorname") String vendorname){
       return  vendorService.getVendorByName(vendorname);
    }

    @PostMapping("/add")
    public CloudVendor addVendor(@RequestBody CloudVendor cloudVendor)
    {
        return vendorService.addVendor(cloudVendor);
    }
    @PutMapping("/update")
    public CloudVendor updateVendor(@RequestBody CloudVendor cloudVendor)
    {
        return vendorService.updateVendor(cloudVendor);
    }

    @DeleteMapping("/delete/{vendorID}")
    public ResponseEntity<String> deleteVendorById(@PathVariable("vendorID") int vendorID)
    {
        return vendorService.deleteVendorById(vendorID);
    }


}
