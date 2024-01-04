package com.workintech.s19challenge.dto.product;

public record ProductResponse(long id, String name, String description, double price, int stock, double rating) {
}
