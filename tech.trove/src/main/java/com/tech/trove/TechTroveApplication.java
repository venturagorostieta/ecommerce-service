package com.tech.trove;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * The type Tech trove application run spring boot application,
 * Required mongobd conexion and redis cache.
 */
@SpringBootApplication(scanBasePackages = {"com.tech.trove"})
@ComponentScan(basePackages = {"com.tech.trove", "com.tech.trove.common"}, excludeFilters = {

})
@EnableCaching
public class TechTroveApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechTroveApplication.class, args);
	}

}
