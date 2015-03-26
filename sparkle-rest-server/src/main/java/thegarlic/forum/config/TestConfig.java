package thegarlic.forum.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestConfig extends AbstractConfig {

	@Override
	protected String getMode() {
		return "test";
	}
}

