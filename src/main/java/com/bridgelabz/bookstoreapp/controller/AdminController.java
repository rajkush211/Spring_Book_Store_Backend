package com.bridgelabz.bookstoreapp.controller;

import com.bridgelabz.bookstoreapp.dto.AdminDto;
import com.bridgelabz.bookstoreapp.dto.BookDto;
import com.bridgelabz.bookstoreapp.service.IAdminService;
import com.bridgelabz.bookstoreapp.service.IBookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/home/admin")
public class AdminController {

    @Autowired
    private IBookStoreService iBookStoreService;

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/loadcsv")
    public String loadCSVData() {
        iBookStoreService.loadBookData();
        return "CSV loaded successfully";
    }

    @PostMapping("/addbook")
    public ResponseEntity addNewBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity(iBookStoreService.addNewBook(bookDto), HttpStatus.CREATED);
    }

    @PostMapping("/uploadcsv")
    public String uploadCsvData(@RequestParam("multipartFile") MultipartFile multipartFile) {
        return iBookStoreService.fetchBookData(multipartFile);
    }

    @PostMapping("/register")
    public ResponseEntity<AdminDto> registerAdmin(@RequestBody AdminDto adminDto) {
        return new ResponseEntity(iAdminService.register(adminDto), HttpStatus.CREATED);
    }
}
