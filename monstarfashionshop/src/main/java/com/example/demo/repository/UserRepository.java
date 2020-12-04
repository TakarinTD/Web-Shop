package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmailAndEnabledIsTrue (String email);

    User findByEmail(String email);

    @Query (value = "select role from user inner join user_role on (user.id = user_role.user_id) inner join role on (user_role.role_id=role.id) where user.id=?1", nativeQuery = true)
    String findUserRole (long userId);

    User findUserByOrdersId(long orderId);

}
