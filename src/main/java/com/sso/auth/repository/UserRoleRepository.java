package com.sso.auth.repository;

import com.sso.auth.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,Integer> {
    @Query(value = "select count(*) \n" +
            "from public.user_role\n" +
            "where role_id = :roleId and user_id= :userId;",nativeQuery = true)
    int findByRoleIdAndUserIdCustom(
            @Param("roleId") int roleId,
            @Param("userId") int userId
    );
}
