package com.jb.copunsysbhp2.controllers;

import com.jb.copunsysbhp2.beans.Company;
import com.jb.copunsysbhp2.exceptions.CouponSystemException;
import com.jb.copunsysbhp2.services.AdminService;
import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("companies")
@RequiredArgsConstructor
public class CompanyController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable int id, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(id, company);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanies() {
        return new ResponseEntity<>(adminService.getAllCompanies(), HttpStatus.OK);
    }

}


