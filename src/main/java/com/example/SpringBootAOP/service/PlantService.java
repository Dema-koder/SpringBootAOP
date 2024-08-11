package com.example.SpringBootAOP.service;

import com.example.SpringBootAOP.annotation.Asynchronously;
import com.example.SpringBootAOP.annotation.PreInvoke;
import com.example.SpringBootAOP.annotation.SuccessLogging;
import com.example.SpringBootAOP.annotation.Valid;
import com.example.SpringBootAOP.model.Plant;
import com.example.SpringBootAOP.model.RoleType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@SuccessLogging
public class PlantService {
    private final Map<String, Plant>plants = new HashMap<>();

    @Asynchronously
    @PreInvoke(roles = RoleType.ADMIN)
    public void addPlant(@Valid Plant plant) {
        plants.put(plant.getName(), plant);
    }

    @Asynchronously
    @PreInvoke(roles = RoleType.ADMIN)
    public void addPlants(@Valid List<Plant> newPlants) {
        plants.putAll(newPlants.stream().collect(Collectors.toMap(Plant::getName, Function.identity())));
    }

    @PreInvoke(roles = {RoleType.ADMIN, RoleType.USER})
    public List<Plant> getPlantsByType(String type) {
        return plants.values().stream().filter(p -> p.getType().equals(type)).collect(Collectors.toList());
    }

    @PreInvoke(roles = {RoleType.ADMIN, RoleType.USER})
    public Plant getPlantByName(String name) {
        return plants.get(name);
    }
}
