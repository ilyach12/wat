package com.chaikouski.controller;

import com.chaikouski.model.ad.Ad;
import com.chaikouski.model.ad.Model;
import com.chaikouski.model.user.Owner;
import com.chaikouski.service.AdsServiceImpl;
import lombok.Cleanup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

@RestController
@PropertySource("classpath:path.properties")
public class AdsController {

    //@Value("${path}")
    private String path = "src/main/resources/static/images/";
    private AdsServiceImpl service;

    public AdsController(AdsServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/ad/{id}")
    public ResponseEntity<?> getAd(@PathVariable long id) {
        return service.findOne(id) != null
                ? new ResponseEntity<>(service.findOne(id), HttpStatus.OK)
                : new ResponseEntity<>("Ad does not exists", HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/ads/{id}")
    public ResponseEntity<?> getAds(@PathVariable Owner id) {
        return !service.findAds(id).isEmpty()
                ? new ResponseEntity<>(service.findAds(id), HttpStatus.OK)
                : new ResponseEntity<>("Owner does not exists", HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/ads")
    public ResponseEntity<?> getAds() {
        return !service.findAds().isEmpty()
                ? new ResponseEntity<>(service.findAds(), HttpStatus.OK)
                : new ResponseEntity<>("Nothing to show\nCreate ad and try again", HttpStatus.NOT_FOUND);
    }

    @RequestMapping("/ads/model/{model}")
    public ResponseEntity<?> getAds(@PathVariable String model){
         return !service.findAds(model).isEmpty()
                 ? new ResponseEntity<>(service.findAds(model), HttpStatus.OK)
                 : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/ad", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam("file") MultipartFile file, @RequestBody Ad ad) {
        return ad.getOwner() != null && ad.getModel() != null && file.isEmpty()
                ? new ResponseEntity<>(service.create(file, ad), HttpStatus.CREATED)
                : new ResponseEntity<>("Body was empty", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ad", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody Ad ad) {
        return ad.getModel() != null
                ? new ResponseEntity<>(service.update(ad), HttpStatus.OK)
                : new ResponseEntity<>("Request has empty parameters", HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/ad", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestBody Ad ad) {
        if (ad.getId() != null) {
            service.delete(ad);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>("Nothing to delete", HttpStatus.BAD_REQUEST);
    }
}
