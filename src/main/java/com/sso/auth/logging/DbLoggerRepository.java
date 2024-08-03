package com.sso.auth.logging;

import com.sso.auth.logging.DbLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DbLoggerRepository extends JpaRepository<DbLogger,Integer> {
}
