package com.debugmafia.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@SpringBootApplication
@RestController
public class DemoApplication {

	@RequestMapping("/api/test")
	public Map<String, String> hello() {
		HashMap<String, String> map = new HashMap<>();
		map.put("test", "value");
		return map;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
