package com.chaikouski.repository;

import com.chaikouski.model.ad.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}
