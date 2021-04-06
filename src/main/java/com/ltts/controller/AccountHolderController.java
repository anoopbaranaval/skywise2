package com.ltts.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltts.exception.ResourceNotFoundException;
import com.ltts.model.AccountHolder;
import com.ltts.repository.AccountHolderRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class AccountHolderController {

    @Autowired
    private AccountHolderRepository accountholderRepository;

    // get all accountholder
    @GetMapping("/accountholders")
    public List < AccountHolder > getAllAccountholders() {
        return accountholderRepository.findAll();
    }

    // create accountholder rest api
    @PostMapping("/accountholders")
    public AccountHolder createAccountholder(@RequestBody AccountHolder accountholder) {
        return accountholderRepository.save(accountholder);
    }

    // get accountholder by id rest api
    @GetMapping("/accountholders/{id}")
    public ResponseEntity < AccountHolder > getAccountholderById(@PathVariable Long id) {
        AccountHolder accountholder = accountholderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Accountholder not exist with id :" + id));
        return ResponseEntity.ok(accountholder);
    }

    // update accountholder rest api

    @PutMapping("/accountholders/{id}")
    public ResponseEntity < AccountHolder > updateAccountholder(@PathVariable Long id, @RequestBody AccountHolder accountholderDetails) {
        AccountHolder accountholder = accountholderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Accountholder not exist with id :" + id));

        accountholder.setFirstName(accountholderDetails.getFirstName());
        accountholder.setLastName(accountholderDetails.getLastName());
        accountholder.setEmailId(accountholderDetails.getEmailId());

        AccountHolder updatedAccountholder= accountholderRepository.save(accountholder);
        return ResponseEntity.ok(updatedAccountholder);
    }

    // delete accountholder rest api
    @DeleteMapping("/accountholders/{id}")
    public ResponseEntity < Map < String, Boolean >> deleteAccountholder(@PathVariable Long id) {
        AccountHolder accountholder = accountholderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Accountholder not exist with id :" + id));

        accountholderRepository.delete(accountholder);
        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}