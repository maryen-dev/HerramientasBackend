package com.marly.demo.Persistance;

import com.marly.demo.Domain.Product;
import com.marly.demo.Domain.Repository.ProductRepository;
import com.marly.demo.Persistance.Crud.ProductoCrudRepository;
import com.marly.demo.Persistance.Crud.UsuarioCrudRepository;
import com.marly.demo.Persistance.Entity.Producto;
import com.marly.demo.Persistance.Mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {

    @Autowired
    private ProductoCrudRepository productoCrudRepository;

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private UsuarioCrudRepository usuarioCrudRepository;



    @Override
    public List<Product> getAll() {
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();
        return productMapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(productMapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);
        return productos.map(productMapper::toProducts);
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(productMapper::toProduct);
    }

    @Override
    public Product save(Product product) {
        Producto producto = productMapper.toProductoCreate(product);
        producto = productoCrudRepository.save(producto);
        return productMapper.toProduct(producto);
    }

    @Override
    public void delete(int idProducto) {
        productoCrudRepository.deleteById(idProducto);
    }

    @Override
    public Product update(Product product) {
        Producto producto = productMapper.toProductoUpdate(product);
        producto = productoCrudRepository.save(producto);
        return productMapper.toProduct(producto);
    }
}
