/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * 启用 Spring 应用程序上下文的自动配置功能，尝试去猜测和配置您可能需要的 bean。自动配置类通常根据您的类路径和您定义的 bean
 * 来应用。 例如，如果您的classpath中有tomcat-embedded.jar，那您可能就会需要一个{@link TomcatServletWebServerFactory}（除
 * 非您已经定义了自己的{@link ServletWebServerFactory}类）。
 *
 * 当使用{@link SpringBootApplication}注解的时候，上下文的自动配置会自动启用，因此，添加此注释不会产生额外的影响。
 * 自动配置尝试尽可能智能，并且会在您定义更多自己的配置时退出。您始终可以手动{@link #exclude() 排除} 任何您不想应
 * 用的配置（如果您无权访问它们，请使用{@link #excludeName()}）。您还可以通过 spring.autoconfigure.exclude 属性
 * 排除它们。自动配置总是在用户定义的 bean 被注册后应用。
 *
 * 用{@code @EnableAutoConfiguration}注释的类的包（通常通过{@link SpringBootApplication} 使用这个注释），是具有特定的
 * 意义的，通常都使用“默认”配置。例如，它会在扫描被{@code @Entity}注解标记的类时使用。通常建议您将
 * {@code @EnableAutoConfiguration}（如果您不使用{@link SpringBootApplication} 的话）放在根包中，以便可以搜索所有子包和类。
 *
 * 自动配置类都是是常规的Spring {@link @Configuration} bean对象。它们使用{@link SpringFactoriesLoader}机制（key->value的
 * 方式）定位。通常情况下，自动配置的 beans 是 {@link @Conditional}bean 对象（最常使用 {@link @ConditionalOnClass}和
 * {@link @ConditionalOnMissingBean}注解）
 *
 * @author Phillip Webb
 * @author Stephane Nicoll
 * @since 1.0.0
 * @see ConditionalOnBean
 * @see ConditionalOnMissingBean
 * @see ConditionalOnClass
 * @see AutoConfigureAfter
 * @see SpringBootApplication
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

	/**
	 * Environment property that can be used to override when auto-configuration is
	 * enabled.
	 */
	String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

	/**
	 * Exclude specific auto-configuration classes such that they will never be applied.
	 * @return the classes to exclude
	 */
	Class<?>[] exclude() default {};

	/**
	 * Exclude specific auto-configuration class names such that they will never be
	 * applied.
	 * @return the class names to exclude
	 * @since 1.3.0
	 */
	String[] excludeName() default {};

}
