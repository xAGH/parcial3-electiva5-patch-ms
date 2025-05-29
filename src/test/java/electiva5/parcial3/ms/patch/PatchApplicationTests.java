package electiva5.parcial3.ms.patch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class PatchApplicationTests {

	@Test
	void contextLoads() {
		PatchApplication.main(new String[] {});
	}

}
