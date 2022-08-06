package springeshop.website.service;

import springeshop.website.dto.ProductDTO;

import java.util.List;

public interface ProductsService {
    List<ProductDTO> getAll();
    void addToUserCard(Long productId, String userId);
    void addProduct(ProductDTO dto);
    ProductDTO getById(Long id);
}
