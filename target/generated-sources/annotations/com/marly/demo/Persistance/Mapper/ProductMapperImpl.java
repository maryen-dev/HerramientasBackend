package com.marly.demo.Persistance.Mapper;

import com.marly.demo.Domain.Product;
import com.marly.demo.Persistance.Entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T18:39:29-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Product toProduct(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        Product product = new Product();

        if ( producto.getIdProducto() != null ) {
            product.setProductId( producto.getIdProducto() );
        }
        product.setProductName( producto.getNombre() );
        if ( producto.getIdCategoria() != null ) {
            product.setCategoryId( producto.getIdCategoria() );
        }
        product.setProductDescription( producto.getDescripcionproducto() );
        if ( producto.getPreciounitario() != null ) {
            product.setProductPrice( producto.getPreciounitario() );
        }
        if ( producto.getCantidadStock() != null ) {
            product.setProductStock( producto.getCantidadStock() );
        }
        if ( producto.getEstado() != null ) {
            product.setActive( producto.getEstado() );
        }
        product.setProductCategory( categoryMapper.toCategory( producto.getCategoria() ) );
        product.setProductImage( producto.getImagenproducto() );

        return product;
    }

    @Override
    public List<Product> toProducts(List<Producto> productos) {
        if ( productos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( productos.size() );
        for ( Producto producto : productos ) {
            list.add( toProduct( producto ) );
        }

        return list;
    }

    @Override
    public Producto toProductoCreate(Product product) {
        if ( product == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setNombre( product.getProductName() );
        producto.setIdCategoria( product.getCategoryId() );
        producto.setDescripcionproducto( product.getProductDescription() );
        producto.setPreciounitario( product.getProductPrice() );
        producto.setCantidadStock( product.getProductStock() );
        producto.setEstado( product.isActive() );
        producto.setImagenproducto( product.getProductImage() );

        return producto;
    }

    @Override
    public Producto toProductoUpdate(Product product) {
        if ( product == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdProducto( product.getProductId() );
        producto.setNombre( product.getProductName() );
        producto.setIdCategoria( product.getCategoryId() );
        producto.setDescripcionproducto( product.getProductDescription() );
        producto.setPreciounitario( product.getProductPrice() );
        producto.setCantidadStock( product.getProductStock() );
        producto.setEstado( product.isActive() );
        producto.setImagenproducto( product.getProductImage() );

        return producto;
    }
}
