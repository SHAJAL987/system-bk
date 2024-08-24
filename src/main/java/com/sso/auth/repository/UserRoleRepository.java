package com.sso.auth.repository;

import com.sso.auth.model.UserRole;
import com.sso.auth.payload.credentials.ParamOne;
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

    @Query(value = "select b.id as role_id,\n" +
            "\t   b.role_name as role_name,\n" +
            "\t   c.mail as mail_id,\n" +
            "\t   c.status as status\n" +
            "from user_role a, role b, user_mst c\n" +
            "where a.role_id = b.id\n" +
            "and a.user_id = c.id\n" +
            "and c.mail = :mail;", nativeQuery = true)
    Object userBasicInfo(
        @Param("mail") String mail
    );
}
