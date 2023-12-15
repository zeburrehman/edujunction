package com.kust.edujunction.Instructor;

import com.kust.edujunction.common.BaseTest;
import com.kust.edujunction.repositories.InstructorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InstructorRepositoryTest extends BaseTest {
    @Autowired
    InstructorRepository instructorRepo;

    @Test
    void connectionEstablished() {
        Assertions.assertThat(postgres.isCreated()).isTrue();
        Assertions.assertThat(postgres.isRunning()).isTrue();
        var instructors = instructorRepo.findAll();
        instructors.isEmpty();
    }
}
