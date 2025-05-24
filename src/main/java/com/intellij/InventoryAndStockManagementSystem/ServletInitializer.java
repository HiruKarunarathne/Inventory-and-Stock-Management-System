package com.intellij.InventoryAndStockManagementSystem;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// Configures the application when deployed as a WAR file
public class ServletInitializer extends SpringBootServletInitializer {
	// Configures the application sources for the SpringApplicationBuilder
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InventoryAndStockManagementSystemApplication.class);
	}
}