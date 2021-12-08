package com.example.extensible;

import lombok.extern.log4j.Log4j2;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;
import org.springframework.nativex.AotOptions;
import org.springframework.nativex.type.NativeConfiguration;


@Log4j2
public class ExtensibleTypeSystemNativeConfiguration implements NativeConfiguration {

	@Override
	public boolean isValid(AotOptions aotOptions) {
		return aotOptions.getMode().equals("native");
	}

	@Override
	public void computeHints(NativeConfigurationRegistry registry, AotOptions aotOptions) {
		log.info(NativeConfiguration.class);
		log.info("the mode is " + aotOptions.getMode());
	}
}
