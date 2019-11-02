/**
 * 
 */
package com.jeffrey.helloworld.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 *
 */
@RestController
public class TestController {
	@Value("${application.name}") 
	private String name;
	
	@Value("${application.version}")
	private String version;

	@Value("${autor.name}")
	private String autor;
	
	@GetMapping("getProperty")
	public String getProperty() {
		return name+version+autor;
	}
}
