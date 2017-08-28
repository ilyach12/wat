package com.chaikouski.service;

import com.chaikouski.model.ad.Ad;
import com.chaikouski.model.user.Owner;
import com.chaikouski.repository.AdsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class AdsServiceImpl {

    private AdsRepository ads;
    private static final String path = "src/main/resources/static/images/";

    public AdsServiceImpl(AdsRepository ads){
        this.ads = ads;
    }

    public Ad findOne(long id){
        return ads.findById(id);
    }

    public List<Ad> findAds() {
        deleteByDate();
        return ads.findAll();
    }

    public List<Ad> findAds(Owner id){
        return ads.findByOwnerId(id);
    }

    public List<Ad> findAds(String model){
        return ads.findAdByModel_Name(model);
    }

    public String upload(MultipartFile file, long id) {
        try {
            byte[] bytes = file.getBytes();
            String name = file.getOriginalFilename();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File(path + name.replace(".", String.valueOf(
                            new Random().nextInt(1000) + id + ".")))));
            stream.write(bytes);
            stream.close();
            return path + name;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Ad create(MultipartFile file, Ad ad){
        ad.setDate(LocalDate.now().plusMonths(1).toString());
        ad.setImage(upload(file, ad.getId()));
        return ads.saveAndFlush(ad);
    }

    public Ad update(Ad ad){
        ad.setDate(ads.getOne(ad.getId()).getDate());
        return ads.saveAndFlush(ad);
    }

    public void delete(Ad ad){
        ads.delete(ad);
    }

    public void deleteByDate(){
        ads.deleteByDate(LocalDate.now().toString());
    }
}
