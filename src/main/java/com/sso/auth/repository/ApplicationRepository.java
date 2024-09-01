package com.sso.auth.repository;

import com.sso.auth.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findByAppCode(Integer appCode);
    @Query("SELECT a.appName, a.appUrl, a.appDesc FROM Application a WHERE a.appStatus = 'Y' ORDER BY a.id ASC")
    List<Object[]> findActiveApplications();
}
