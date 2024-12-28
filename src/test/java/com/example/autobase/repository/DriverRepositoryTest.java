package com.example.autobase.repository;
import com.example.autobase.model.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class DriverRepositoryTest {
    @Autowired
    private DriverRepository driverRepository;
    @Test
    public void testSaveAndFindDriver() {
        Driver driver = new Driver();
        driver.setName("John Doe");
        driver.setExperience(5);

        driver = driverRepository.save(driver);

        Optional<Driver> foundDriver = driverRepository.findById(driver.getId());
        assertTrue(foundDriver.isPresent());
        assertEquals(driver.getName(), foundDriver.get().getName());
    }
}