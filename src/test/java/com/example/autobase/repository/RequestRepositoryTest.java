package com.example.autobase.repository;
import com.example.autobase.model.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class RequestRepositoryTest {

    @Autowired
    private RequestRepository requestRepository;

    @Test
    public void testSaveAndFindRequest() {
        Request request = new Request();
        request.setDestination("New York");
        request.setWeight(100);

        request = requestRepository.save(request);

        Optional<Request> foundRequest = requestRepository.findById(request.getId());
        assertTrue(foundRequest.isPresent());
        assertEquals(request.getDestination(), foundRequest.get().getDestination());
    }
}