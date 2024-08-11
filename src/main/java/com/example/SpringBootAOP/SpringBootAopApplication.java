package com.example.SpringBootAOP;

import com.example.SpringBootAOP.example.ExampleBean;
import com.example.SpringBootAOP.model.Plant;
import com.example.SpringBootAOP.service.PlantService;
import com.example.SpringBootAOP.utils.ThreadUtils;
import com.example.SpringBootAOP.utils.UserContext;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootAopApplication {

//	private final ExampleBean exampleBean;

	private final PlantService plantService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAopApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReady() {
//		exampleBean.info();

		UserContext.setUsername("admin");

		plantService.addPlant(new Plant("Роза", "Цветок"));

		ThreadUtils.sleep(200);
		System.out.println(plantService.getPlantsByType("Цветок"));
		System.out.println(plantService.getPlantByName("Роза"));

		ThreadUtils.sleep(200);
		plantService.addPlants(List.of(new Plant("Кукуруза1", "Трава")));
	}
}
