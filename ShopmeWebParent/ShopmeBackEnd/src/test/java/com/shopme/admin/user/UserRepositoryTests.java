package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test

    public void testCreateUser() {
        Role adminRole = testEntityManager.find(Role.class, 1);
        User userHamHM = new User(
                "q@q.ru",
                "nam200",
                "Nam",
                "Ha Min");
        userHamHM.addRole(adminRole);

        User savedUser = userRepository.save(userHamHM);

        assertThat(savedUser.getId()).isGreaterThan(0);
    }
}
