package com.xianchumo.shop.controller.weixin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xianchumo.shop.entity.Address;
import com.xianchumo.shop.entity.Merchant;
import com.xianchumo.shop.entity.Order;
import com.xianchumo.shop.entity.OrderState;
import com.xianchumo.shop.entity.ShoppingCart;
import com.xianchumo.shop.service.MerchantService;
import com.xianchumo.shop.service.OrderItemService;
import com.xianchumo.shop.service.OrderService;
import com.xianchumo.shop.service.ShoppingCartService;
import com.xianchumo.shop.util.JsonUtil;
import com.xianchumo.shop.util.ShopUtil;

/**
 * 
 * Copyright 2015 xianchumo.com
 * 
 * @Team xianchumo
 * @data：2015年9月20日 @author CGZ
 * @description
 */
@Controller
@RequestMapping(value = "order")
public class OrderController {
	private static final int PAGE_SIZE = 5;
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private OrderItemService orderItemService;

	/**
	 * 下单 对应：去结算
	 */
	@RequestMapping(value = "/createOrder")
	public String createOrder(HttpServletRequest request, HttpSession session) {
		ShoppingCart shoppingCart = ShopUtil.getShoppingCart(request, shoppingCartService);
		// 生成订单
		Order order = orderService.createOrder(shoppingCart);
		session.setAttribute("order", order);
		orderService.add(order);
		return "weixin/payment";
	}

	/**
	 * 付款 liveAreaId 最小区id，用于查找商家
	 * 
	 * @return
	 */
	@RequestMapping(value = "pay")
	public String pay(Long liveAreaId, Long addressId,HttpSession session, HttpServletRequest request) {

		ShoppingCart shoppingCart = ShopUtil.getShoppingCart(request, shoppingCartService);
		// 生成订单
		Order order = orderService.createOrder(shoppingCart);
		//商家 
		Merchant merchant = merchantService.findByAddress(liveAreaId);
		order.setMerchant(merchant);
		
		//地址
		Address address = new Address();
		address.setAid(addressId);
		order.setAddress(address);
		
		// TODO 使用微信支付
		order.setOrderState(OrderState.PAID); 
		orderService.add(order);
		session.setAttribute("order", order);
		//清空购物车
		shoppingCartService.removeAllItem(shoppingCart);
		return "weixin/success-payment";
	}

	/**
	 * 付款页面
	 */
	@RequestMapping(value = "/payment")
	public String payment() {

		return "weixin/payment";
	}

	/**
	 * 取消订单
	 */
	@RequestMapping(value = "/cancelOrder")
	public String cancelOrder(HttpSession session) {
		session.getAttribute("");
		return "index";
	}

	/**
	 * 查看历史订单
	 */
	@RequestMapping(value = "/listOrder")
	public String listOrder(HttpServletRequest request) {
		//TODO paginate
		List<Order> orders = orderService.findAll();
		request.setAttribute("orders", orders);
		return "weixin/all-orders";
	}
	/**
	 * 动态加载更多订单
	 * @param page
	 * @param response
	 */
	@RequestMapping(value = "/listOrderJson")
	public void listOrderJson(Integer page,HttpServletResponse response){
		List<Order> orders = orderService.findPageList("from Order", page, PAGE_SIZE);
		JsonUtil.outPut(response, orders);
	}
	/**
	 * 订单详情
	 */
	@RequestMapping(value = "/orderDetail")
	public String orderDetail() {
		return "index";
	}
}
