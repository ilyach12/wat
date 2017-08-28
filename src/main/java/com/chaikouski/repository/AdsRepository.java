package com.chaikouski.repository;

import com.chaikouski.model.ad.Ad;
import com.chaikouski.model.user.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface AdsRepository extends JpaRepository<Ad, Long> {

    @Query("FROM Ad ad " +
            "LEFT JOIN Characteristics char ON char.ad = ad.id " +
            "LEFT JOIN Complectation comp ON comp.ad = ad.id WHERE ad.owner = :id")
    List<Ad> findByOwnerId(@Param("id") Owner id);

    List<Ad> findAdByModel_Name(String model);

    @Query("FROM Ad ad " +
            "LEFT JOIN Characteristics char ON char.ad = ad.id " +
            "LEFT JOIN Complectation comp ON comp.ad = ad.id WHERE ad.id = :id")
    Ad findById(@Param("id") long id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Ad ad WHERE ad.date <= :date")
    void deleteByDate(@Param("date") String date);
}
