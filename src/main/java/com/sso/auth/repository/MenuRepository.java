package com.sso.auth.repository;

import com.sso.auth.model.Menu;
import com.sso.auth.payload.menu.MenuCommon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query(value = "WITH RECURSIVE MenuHierarchy AS ( " +
            "SELECT id, app_id, (SELECT app_name FROM application_info WHERE id = app_id) AS app_name, is_child, is_parent, level, name, parent_id, url, is_root, 1 AS depth " +
            "FROM menu " +
            "WHERE is_root = 'Y' " +
            "UNION ALL " +
            "SELECT m.id, m.app_id, (SELECT app_name FROM application_info WHERE id = m.app_id) AS app_name, m.is_child, m.is_parent, m.level, m.name, m.parent_id, m.url, m.is_root, mh.depth + 1 AS depth " +
            "FROM menu m " +
            "INNER JOIN MenuHierarchy mh ON m.parent_id = mh.id " +
            ") " +
            "SELECT id, app_id, app_name, is_child, is_parent, level, name, parent_id, url, is_root " +
            "FROM MenuHierarchy " +
            "ORDER BY app_id, depth, parent_id, id",
            nativeQuery = true)
    List<Menu> findAllMenus();

    @Query(value = """
            WITH RECURSIVE MenuHierarchy AS (
                SELECT 
                    m.id AS id,
                    m.app_id AS app_id,
                    (SELECT ai.app_name FROM application_info ai WHERE ai.id = m.app_id) AS app_name,
                    m.is_child AS is_child,
                    m.is_parent AS is_parent,
                    m.level AS level,
                    m.name AS name,
                    m.parent_id AS parent_id,
                    m.url AS url,
                    m.is_root AS is_root,
                    1 AS depth
                FROM 
                    menu m
                JOIN 
                    menu_role mr ON m.id = mr.menu_id
                JOIN 
                    user_role ur ON mr.role_id = ur.role_id
                WHERE 
                    m.is_root = 'Y' AND ur.user_id = :userId
                UNION ALL
                SELECT 
                    m.id AS id,
                    m.app_id AS app_id,
                    (SELECT ai.app_name FROM application_info ai WHERE ai.id = m.app_id) AS app_name,
                    m.is_child AS is_child,
                    m.is_parent AS is_parent,
                    m.level AS level,
                    m.name AS name,
                    m.parent_id AS parent_id,
                    m.url AS url,
                    m.is_root AS is_root,
                    mh.depth + 1 AS depth
                FROM 
                    menu m
                JOIN 
                    menu_role mr ON m.id = mr.menu_id
                JOIN 
                    user_role ur ON mr.role_id = ur.role_id
                INNER JOIN 
                    MenuHierarchy mh ON m.parent_id = mh.id
                WHERE 
                    ur.user_id = :userId
            )
            SELECT 
                mh.id AS id,
                mh.app_id AS app_id,
                mh.app_name AS app_name,
                mh.is_child AS is_child,
                mh.is_parent AS is_parent,
                mh.level AS level,
                mh.name AS name,
                mh.parent_id AS parent_id,
                mh.url AS url,
                mh.is_root AS is_root
            FROM 
                MenuHierarchy mh
            ORDER BY 
                mh.app_id, mh.depth, mh.parent_id, mh.id
            """, nativeQuery = true)
    List<Object[]> findMenuByUserId(@Param("userId") int userId);


    @Query(value = """
                WITH RECURSIVE MenuHierarchy AS (
                    -- Base case: Select root menus
                    SELECT 
                        m.id AS id,
                        m.app_id AS app_id,
                        m.name AS name,
                        m.parent_id AS parent_id,
                        m.url AS url,
                        m.is_root AS is_root,
                        m.is_child AS is_child,
                        m.is_parent AS is_parent,
                        m.level AS level
                    FROM 
                        menu m
                    WHERE 
                        m.is_root = 'Y'
                    
                    UNION ALL
                    
                    -- Recursive case: Select child menus
                    SELECT 
                        m.id AS id,
                        m.app_id AS app_id,
                        m.name AS name,
                        m.parent_id AS parent_id,
                        m.url AS url,
                        m.is_root AS is_root,
                        m.is_child AS is_child,
                        m.is_parent AS is_parent,
                        m.level AS level
                    FROM 
                        menu m
                    INNER JOIN 
                        MenuHierarchy mh ON m.parent_id = mh.id
                )
                SELECT 
                    ai.app_code AS app_id,
                    ai.app_name AS app_name,
                    mh.id AS menu_id,
                    mh.name AS menu_name,
                    mh.parent_id AS parent_id,
                    mh.url AS menu_url,
                    mh.is_root AS is_root,
                    mh.is_child AS is_child,
                    mh.is_parent AS is_parent,
                    mh.level AS level
                FROM 
                    application_info ai
                LEFT JOIN 
                    MenuHierarchy mh ON ai.app_code = mh.app_id
                ORDER BY 
                    ai.app_name, mh.parent_id, mh.id
            """, nativeQuery = true)
    List<Object[]> fetchApplicationWithMenus();
}
