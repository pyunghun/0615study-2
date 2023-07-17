package com.ohgiraffers.transactional.section01.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RegistOrderService {

    private MenuMapper menuMapper;

    @Autowired
    public RegistOrderService(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;

    }

    public void registNewOrder(OrderDTO orderInfo) {

        List<Integer> menuCodes = orderInfo.getOrderMenus()
                .stream()
                .map(OrderMenuDTO::getMenuCode)
                .collect(Collectors.toList());

        System.out.println("menuCodes = " + menuCodes);
        
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("menuCodes", menuCodes);
        
        List<Menu> menus = menuMapper.findMenusByMenuCode(map);
        menus.forEach(System.out::println);
    }
}
