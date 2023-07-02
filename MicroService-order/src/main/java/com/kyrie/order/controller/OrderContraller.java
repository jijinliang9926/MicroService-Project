package com.kyrie.order.controller;

import com.kyrie.base.model.PageResult;
import com.kyrie.order.dto.QueryOrderParamsDto;
import com.kyrie.order.pojo.Order;
import com.kyrie.order.sercice.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.PageRanges;

/**
 * @auther: jijin
 * @date: 2023/7/2 0:09 周日
 * @project_name: MicroServiceTest
 * @version: 1.0
 * @description TODO
 */
@RestController
@Api(value = "订单接口 value", tags = "订单接口 tags")
public class OrderContraller {


    @ApiOperation("根据订单号查询订单")
    @GetMapping("/{id}")
    public PageResult<Order> list(PageRanges pageRanges, QueryOrderParamsDto queryOrderParamsDto) {
        return null;
    }
}
