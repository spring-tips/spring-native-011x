package com.example.extensible;

import lombok.extern.log4j.Log4j2;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;
import org.springframework.nativex.AotOptions;
import org.springframework.nativex.hint.JdkProxyHint;
import org.springframework.nativex.type.NativeConfiguration;

@JdkProxyHint(types = {
	com.example.extensible.CustomerService.class,
	org.springframework.aop.SpringProxy.class,
	org.springframework.aop.framework.Advised.class,
	org.springframework.core.DecoratingProxy.class
})
@Log4j2
public class ExtensibleNativeConfiguration implements NativeConfiguration {

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
