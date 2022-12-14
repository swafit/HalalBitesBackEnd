package org.wae.halalbites.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.wae.halalbites.other.exceptions.NotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class NotFoundExceptionTest {
    @Test
    void TestNotFoundEmptyConstructor() {
        assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException();
        });
    }

    @Test
    void TestNotFoundCauseOnlyConstructor() {
        assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException(new Exception());
        });
    }

    @Test
    void TestNotFoundExceptionMessageAndCauseConstructor() {
        assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException("Something went wrong", new Exception());
        });
    }

    @Test
    void TestNotFoundExceptionMessageOnly() {
        assertThrows(NotFoundException.class, () -> {
            throw new NotFoundException("Something went wrong");
        });
    }
}