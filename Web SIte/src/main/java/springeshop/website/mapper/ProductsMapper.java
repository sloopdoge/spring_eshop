package springeshop.website.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import springeshop.website.domain.Products;
import springeshop.website.dto.ProductDTO;

import java.util.List;

@Mapper
public interface ProductsMapper {
    ProductsMapper MAPPER = Mappers.getMapper(ProductsMapper.class);

    Products toProduct(ProductDTO productDTO);

    @InheritInverseConfiguration
    ProductDTO fromProducts(Products products);

    List<Products> toProductsList(List<Products> products);

    List<ProductDTO> fromProductsList(List<Products> products);
}
