package ru.netology.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class DataInfo {
    private final String city;
    private final String name;
    private final String phone;
}
