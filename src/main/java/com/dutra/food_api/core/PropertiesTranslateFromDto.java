package com.dutra.food_api.core;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;

public class PropertiesTranslateFromDto {

    public static Pageable translateFields(Pageable pageableInput, Map<String, String> fieldsMapping) {
        var orders = pageableInput.getSort()
                .stream()
                .filter(order -> fieldsMapping.containsKey(order.getProperty())) //Ignora prop null/inexistente
                .map(order -> new Sort.Order(order.getDirection(),
                    fieldsMapping.get(order.getProperty())))
                .toList();

        return PageRequest.of(pageableInput.getPageNumber(), pageableInput.getPageSize(), Sort.by(orders));
    }
}
