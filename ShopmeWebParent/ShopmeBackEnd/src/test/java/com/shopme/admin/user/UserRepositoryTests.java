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

    @Test
    void testCreateNewUserWithTwoRoles() {
        User userRavi = new User(
                "revi@r.ru",
                "ravi2020",
                "Ravi",
                "Kumar"
        );

        userRavi.addRole(new Role(3)); // Editor
        userRavi.addRole(new Role(5)); // Assistant

        User savedUser = userRepository.save(userRavi);
        assertThat(savedUser.getId()).isGreaterThan(0);
    }

    @Test
    void testListAllUser() {
        Iterable<User> listUser = userRepository.findAll();
        listUser.forEach(System.out::println);
        assertThat(listUser);
    }

    @Test
    void testGetUserByID() {
        User userNam = userRepository.findById(1).get();
        System.out.println(userNam);
        assertThat(userNam).isNotNull();
    }

    @Test
    void testUpdateUserDetails() {
        User userNam = userRepository.findById(1).get();
        userNam.setEnabled(true);
		userNam.setEmail("namjavaprogrammer@gmail.com");

        userRepository.save(userNam);
    }

    @Test
    void testUpdateUserRoles() {
        User userRavi = userRepository.findById(2).get();
        userRavi.getRoles().remove(new Role(3)); // remove role Editor
        userRavi.getRoles().add(new Role(2)); // add role Sales


    }

    @Test
    void testDeleteUser() {
        Integer userID = 2;
        userRepository.deleteById(userID);
    }
}
