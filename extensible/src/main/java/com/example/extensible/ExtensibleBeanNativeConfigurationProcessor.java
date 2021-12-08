package com.example.extensible;

import lombok.extern.log4j.Log4j2;
import org.springframework.aot.context.bootstrap.generator.bean.descriptor.BeanInstanceDescriptor;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.BeanNativeConfigurationProcessor;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;

@Log4j2
public class ExtensibleBeanNativeConfigurationProcessor
	implements BeanNativeConfigurationProcessor {

	@Override
	public void process(BeanInstanceDescriptor descriptor, NativeConfigurationRegistry registry) {
		log.info(BeanNativeConfigurationProcessor.class);
		log.info("process(" + descriptor.getBeanType().getType() + ")");
	}
}
