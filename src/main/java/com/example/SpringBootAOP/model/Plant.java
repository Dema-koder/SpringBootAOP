package com.example.SpringBootAOP.model;

import com.example.SpringBootAOP.annotation.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Model
public class Plant {

    private String name;
    private String type;
}
