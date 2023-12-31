package com.kyrie.order.sercice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kyrie.base.model.PageParams;
import com.kyrie.base.model.PageResult;
import com.kyrie.order.clients.OrderClient;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.mapper.OrderMapper;
import com.kyrie.order.pojo.Order;
import com.kyrie.order.pojo.User;
import com.kyrie.order.sercice.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:05 周日
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderClient orderClient;

    @Override
    public PageResult<Order> queryOrderList(PageParams pageParams, QueryOrderParamsDto queryOrderParamsDto) {
        Page<Order> pageParam = new Page<>(pageParams.getPageNum(),pageParams.getPageSize());
        LambdaQueryWrapper lqw = new LambdaQueryWrapper<>();
        Page<Order> page = orderMapper.selectPage(pageParam, lqw);

        PageResult<Order> pageResult = new PageResult<>();
        pageResult.setCounts(page.getTotal());
        pageResult.setPageNum(page.getCurrent());
        pageResult.setPageSize(page.getSize());

        page.getRecords().stream().forEach(o -> {o.setUser(orderClient.selectById(o.getUserId()));});
        pageResult.setItems(page.getRecords());

//        List<Order> list = new ArrayList<>();
//        List records = page.getRecords();
//        for (Object record : records) {
//            Order order = (Order) record;
//            order.setUser(orderClient.selectById(order.getUserId()));
//            list.add(order);
//        }
//        pageResult.setItems(list);

        return pageResult;
    }

    @Override
    public Order getById(Long id) {
        Order order = orderMapper.selectById(id);
        Long userId = order.getUserId();
        User userById = orderClient.selectById(userId);
        order.setUser(userById);

        return order;
    }
}
