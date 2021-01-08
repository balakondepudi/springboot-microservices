package com.justshopme.shopping.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.justshopme.shopping.exception.ShopCartException;
import com.justshopme.shopping.models.Product;
import com.justshopme.shopping.models.PurchaseItem;
import com.justshopme.shopping.models.UserCart;
import com.justshopme.shopping.services.ShoppingService;

/**
 * 
 * Shopping cart API service controller which basically handles all shopping cart requests.
 * @author Bala Kondepudi
 *
 */
@Controller
@RequestMapping("/shop")
public class ShoppingController {

	@Autowired
	private ShoppingService shoppingService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * API returns the shopping cart for the given user
	 * @param userId
	 * @return
	 */
	@GetMapping("/viewCart")
	@ResponseBody
	public Optional<UserCart> viewCart(@RequestHeader("user-Id") String userId) {
		Map<String, PurchaseItem> userItems = shoppingService.viewCart(userId);
		Double totalPrice = shoppingService.checkoutPrice(userId, userItems);
		UserCart userCart = new UserCart(totalPrice, userItems);
		return Optional.ofNullable(userCart);
	}
	
	/**
	 * 
	 * API add product into cart for a given productId and userId.
	 * @param id
	 * @param userId
	 * @return
	 * @throws ShopCartException
	 */
	@PostMapping("/addItem/{id}")
	@ResponseBody
	public Optional<Map<String, PurchaseItem>> addItemToCart(@PathVariable String id, @RequestHeader("user-Id") String userId) throws ShopCartException {
		// call inventory microservice to fetch product matching an id
//		Product addProduct = restTemplate.getForObject("http://localhost:8091/inventory/get/" + id, Product.class);
		Product addProduct = restTemplate.getForObject("http://INVENTORY-SERVICE/inventory/get/" + id, Product.class);

		//		Product addProduct = inventoryService.getProductById(id);
		return Optional.ofNullable(shoppingService.addItemToCart(userId, addProduct));
	}
	
	/**
	 * 
	 * API remove product from cart for a given productId and userId.
	 * @param id
	 * @param userId
	 * @return
	 * @throws ShopCartException
	 */
	@DeleteMapping("/removeItem/{id}")
	@ResponseBody
	public Optional<Map<String, PurchaseItem>> removeItemFromCart(@PathVariable String id, @RequestHeader("user-Id") String userId) throws ShopCartException {
		// call inventory microservice to fetch product matching an id
//		Product removeProduct = restTemplate.getForObject("http://localhost:8091/inventory/get/" + id, Product.class);
		Product removeProduct = restTemplate.getForObject("http://INVENTORY-SERVICE/inventory/get/" + id, Product.class);

//		Product removeProduct = inventoryService.getProductById(id);
		return Optional.ofNullable(shoppingService.removeItemFromCart(userId, removeProduct));
	}
	
	/**
	 * API performs checkout for the given userId.
	 * @param userId
	 * @return
	 * @throws ShopCartException
	 */
	@PostMapping("/checkout")
	@ResponseBody
	public String prepareCheckout(@RequestHeader("user-Id") String userId) throws ShopCartException {
		return shoppingService.checkout(userId);
	}
	
	
}
