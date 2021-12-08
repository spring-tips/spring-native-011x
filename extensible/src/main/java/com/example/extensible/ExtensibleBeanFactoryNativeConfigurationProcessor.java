package com.example.extensible;

import lombok.extern.log4j.Log4j2;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.BeanFactoryNativeConfigurationProcessor;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeConfigurationRegistry;
import org.springframework.aot.context.bootstrap.generator.infrastructure.nativex.NativeProxyEntry;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.nativex.hint.JdkProxyHint;


@Log4j2
public class ExtensibleBeanFactoryNativeConfigurationProcessor
	implements BeanFactoryNativeConfigurationProcessor {

	private final boolean registerProxy = false;

	@Override
	public void process(ConfigurableListableBeanFactory beanFactory, NativeConfigurationRegistry registry) {
		log.info(BeanFactoryNativeConfigurationProcessor.class);
		var beans = beanFactory.getBeansOfType(CustomerService.class);
		if (registerProxy && beans.size() > 0) {
			registry.proxy().add(NativeProxyEntry.ofInterfaces(CustomerService.class,
				org.springframework.aop.SpringProxy.class,
				org.springframework.aop.framework.Advised.class,
				org.springframework.core.DecoratingProxy.class));
		}
	}
}
