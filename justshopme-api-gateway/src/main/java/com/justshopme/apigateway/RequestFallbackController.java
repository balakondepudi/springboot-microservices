package com.justshopme.apigateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestFallbackController {

	@GetMapping("/shopServiceAPIFallback")
	public String shopServiceAPIFallback() {
		return "Shop API are unavailable and so cannot proceed with the request.";
	}
	
	@GetMapping("/inventoryServiceAPIFallback")
	public String inventoryServiceAPIFallback() {
		return "Inventory API are unavailable and so cannot proceed with the request.";
	}
}
