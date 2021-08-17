package lifestudio.backend.domain.studio.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StudioRepositoryTest {

    @Test
    public void 스튜디오에속한사진들찾기() {
        assertEquals(2,2);
    }

}
