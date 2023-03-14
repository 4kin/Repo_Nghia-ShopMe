package com.shopme.admin.user;

import com.shopme.common.entity.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class RoleRepositoryTest {
    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateFirstRole() {
        Role role = new Role("Admin1", "manage evryone");
        Role savedRole = repo.save(role);
        Assertions.assertTrue(savedRole.getId() > 0);
    }

    @Test
    public void testCreateRestRoles() {
        Role roleSalesPerson = new Role("SalesPerson", "manage product price, customes, shiping, orders and sales report");
        Role roleEditor = new Role("Editor", "manage categories");
        Role roleShipper = new Role("Shipper", "viwe product");
        Role roleAssistant = new Role("Assistant", "manage question");

        repo.saveAll(List.of(roleAssistant, roleEditor, roleShipper, roleSalesPerson));


    }
}