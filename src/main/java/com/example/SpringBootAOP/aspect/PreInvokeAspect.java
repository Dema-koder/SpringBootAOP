package com.example.SpringBootAOP.aspect;

import com.example.SpringBootAOP.annotation.PreInvoke;
import com.example.SpringBootAOP.exception.ApplicationException;
import com.example.SpringBootAOP.model.RoleType;
import com.example.SpringBootAOP.utils.UserContext;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Order(1)
public class PreInvokeAspect {
    private static final Map<String, List<RoleType>>USERS = new HashMap<>();

    static {
        USERS.put("admin", List.of(RoleType.ADMIN, RoleType.USER));
        USERS.put("user", List.of(RoleType.USER));
    }

    @Pointcut("@annotation(preInvoke)")
    public void checkRolePointcut(PreInvoke preInvoke){}

    @Before(value = "checkRolePointcut(preInvoke)", argNames = "preInvoke")
    public void before(PreInvoke preInvoke){
        String currentUser = UserContext.getUsername();

        if (!USERS.containsKey(currentUser)) {
            throw new ApplicationException("Пользователь не найден " + currentUser);
        }

        var roles = Arrays.stream(preInvoke.roles()).toList();
        var userRoles = USERS.get(currentUser);

        if (roles.stream().noneMatch(userRoles::contains)) {
            throw new ApplicationException("Доступ запрещен! Роли: " + userRoles);
        }
    }
}
