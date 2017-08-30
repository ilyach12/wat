package com.chaikouski.service;

import com.chaikouski.model.ad.Ad;
import com.chaikouski.model.user.Owner;
import com.chaikouski.repository.AdsRepository;
import com.chaikouski.utils.FilesProcessor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
public class AdsServiceImpl {

    private static final String PATH = "src/main/resources/static/images/";

    private AdsRepository ads;

    public AdsServiceImpl(AdsRepository ads){
        this.ads = ads;
    }

    public Ad findOne(long id){
        return ads.findById(id);
    }

    public List<Ad> findAds() {
        ads.deleteByDate(LocalDate.now().toString());
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
            FilesProcessor.processFile(id, file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File(PATH + FilesProcessor.getName() + FilesProcessor.getExtension())));
            stream.write(bytes);
            stream.close();
            return PATH;
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
}
