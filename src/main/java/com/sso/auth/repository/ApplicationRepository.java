package com.sso.auth.repository;

import com.sso.auth.model.Application;
import com.sso.auth.payload.application.ApplicationDto;
import com.sso.auth.payload.application.ApplicationList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    Optional<Application> findByAppCode(Integer appCode);

    @Query("SELECT a.appName, a.appUrl, a.appDesc FROM Application a WHERE a.appStatus = 'Y' ORDER BY a.id ASC")
    List<Object[]> findActiveApplications();

    @Query(value = "SELECT DISTINCT ai.id AS app_id, ai.app_code as app_code, ai.app_name AS app_name, ai.app_url AS app_url, ai.app_desc AS app_desc, ai.app_status AS app_status " +
            "FROM application_info ai " +
            "WHERE ai.id IN ( " +
            "    SELECT DISTINCT m.id " +
            "    FROM menu m " +
            "    JOIN menu_role mr ON m.id = mr.menu_id " +
            "    JOIN user_role ur ON mr.role_id = ur.role_id " +
            "    WHERE ur.user_id = :userId" +
            ") " +
            "ORDER BY ai.app_name", nativeQuery = true)
    List<Map<String, Object>> findApplicationsByUserId(@Param("userId") int userId);

}
