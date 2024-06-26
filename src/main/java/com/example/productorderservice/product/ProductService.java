package com.example.productorderservice.product;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
class ProductService {

	private final ProductPort productPort;

	 ProductService(final ProductPort productPort){
		this.productPort = productPort;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Void> addProduct(@RequestBody final AddProductRequest request){
		final Product product = new Product(request.name(), request.price(), request.discountPolicy());
		productPort.save(product);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
