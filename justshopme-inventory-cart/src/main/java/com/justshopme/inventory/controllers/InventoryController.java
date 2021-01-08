package com.justshopme.inventory.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.justshopme.inventory.models.Product;
import com.justshopme.inventory.services.InventoryService;

/**
 * 
 * Inventory cart API service controller that expose product availability and get product
 * @author Bala Kondepudi
 *
 */
@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * API returns all products available for purchase
	 * @return
	 */
	@GetMapping("/products")
	@ResponseBody
	public Optional<List<Product>> availableProducts() {
		return Optional.ofNullable(inventoryService.availableProducts());
	}

	/**
	 * API returns all products available for purchase
	 * @return
	 */
	@GetMapping("/get/{id}")
	@ResponseBody
	public Optional<Product> getProductById(@PathVariable String id) {
		return Optional.ofNullable(inventoryService.getProductById(id));
	}

	
}
