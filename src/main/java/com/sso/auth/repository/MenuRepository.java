package com.sso.auth.repository;

import com.sso.auth.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    @Query(value = "WITH RECURSIVE MenuHierarchy AS ( " +
            "SELECT id, app_id, is_child, is_parent, level, name, parent_id, role_id, url, is_root, 1 AS depth " +
            "FROM menu " +
            "WHERE is_root = 'Y' " +
            "UNION ALL " +
            "SELECT m.id, m.app_id, m.is_child, m.is_parent, m.level, m.name, m.parent_id, m.role_id, m.url, m.is_root, mh.depth + 1 AS depth " +
            "FROM menu m " +
            "INNER JOIN MenuHierarchy mh ON m.parent_id = mh.id " +
            ") " +
            "SELECT id, app_id, is_child, is_parent, level, name, parent_id, role_id, url, is_root " +
            "FROM MenuHierarchy " +
            "ORDER BY app_id, depth, parent_id, id",
            nativeQuery = true)
    List<Menu> findAllMenus();
}
