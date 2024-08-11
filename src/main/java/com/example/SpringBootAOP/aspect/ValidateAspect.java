package com.example.SpringBootAOP.aspect;

import com.example.SpringBootAOP.exception.ApplicationException;
import com.example.SpringBootAOP.model.Plant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(3)
@Slf4j
public class ValidateAspect {

    private static final Map<String, List<String>>PLANTS = new HashMap<>();

    static {
        PLANTS.put("Цветок", List.of("Роза", "Тюльпан", "Маргаритка"));
        PLANTS.put("Дерево", List.of("Дуб", "Сосна", "Береза"));
        PLANTS.put("Трава", List.of("Пшеница", "Кукуруза", "Рожь"));
    }

    @Pointcut("execution(public void add*(@com.example.SpringBootAOP.annotation.Valid (*), ..))")
    public void needValidPointcut() {}

    @Pointcut("@args(com.example.SpringBootAOP.annotation.Model) && execution(public void add*(..))")
    public void needValidPointcutWithArgs() {}

    @Pointcut("needValidPointcut() && args(plant)")
    public void validatePlantPointcut(Plant plant) {}

    @Pointcut("needValidPointcut() && args(plants)")
    public void validatePlantListPointcut(List<Plant> plants) {}

    @Before(value = "validatePlantListPointcut(plants)", argNames = "plants")
    public void validatePlantList(List<Plant> plants) {
        log.info("Валидация растения перед вызовом метода");
        plants.forEach(this::validate);
    }

    @Before(value = "validatePlantPointcut(plant)", argNames = "plant")
    public void validateSinglePlant(Plant plant) {
        log.info("Валидация растения перед вызовом метода");
        validate(plant);
    }

    private void validate(Plant plant) {
        if (!PLANTS.containsKey(plant.getType()) ||
                PLANTS.get(plant.getType()).stream().noneMatch(item -> item.equals(plant.getName()))) {
            throw new ApplicationException("Ошибка валидации");
        }
    }
}
