package com.marly.demo.Persistance.Mapper;

import com.marly.demo.Domain.OrderItem;
import com.marly.demo.Persistance.Entity.Pedido;
import com.marly.demo.Persistance.Entity.Pedido_Item;
import com.marly.demo.Persistance.Entity.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T18:39:28-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class OrderItemMapperImpl implements OrderItemMapper {

    @Override
    public OrderItem toOrderItem(Pedido_Item pedidoItem) {
        if ( pedidoItem == null ) {
            return null;
        }

        OrderItem orderItem = new OrderItem();

        orderItem.setItemId( pedidoItem.getIdItem() );
        orderItem.setOrderId( pedidoItemPedidoIdPedido( pedidoItem ) );
        orderItem.setProductId( pedidoItemProductoIdProducto( pedidoItem ) );
        orderItem.setProductName( pedidoItemProductoNombre( pedidoItem ) );
        orderItem.setQuantity( pedidoItem.getCantidad() );
        orderItem.setUnitPrice( pedidoItem.getPrecioUnitario() );
        orderItem.setTotal( pedidoItem.getTotal() );

        return orderItem;
    }

    @Override
    public List<OrderItem> toOrderItems(List<Pedido_Item> items) {
        if ( items == null ) {
            return null;
        }

        List<OrderItem> list = new ArrayList<OrderItem>( items.size() );
        for ( Pedido_Item pedido_Item : items ) {
            list.add( toOrderItem( pedido_Item ) );
        }

        return list;
    }

    @Override
    public Pedido_Item toPedidoItem(OrderItem orderItem) {
        if ( orderItem == null ) {
            return null;
        }

        Pedido_Item pedido_Item = new Pedido_Item();

        pedido_Item.setIdItem( orderItem.getItemId() );
        pedido_Item.setCantidad( orderItem.getQuantity() );
        pedido_Item.setPrecioUnitario( orderItem.getUnitPrice() );
        pedido_Item.setTotal( orderItem.getTotal() );

        return pedido_Item;
    }

    private Integer pedidoItemPedidoIdPedido(Pedido_Item pedido_Item) {
        Pedido pedido = pedido_Item.getPedido();
        if ( pedido == null ) {
            return null;
        }
        return pedido.getIdPedido();
    }

    private Integer pedidoItemProductoIdProducto(Pedido_Item pedido_Item) {
        Producto producto = pedido_Item.getProducto();
        if ( producto == null ) {
            return null;
        }
        return producto.getIdProducto();
    }

    private String pedidoItemProductoNombre(Pedido_Item pedido_Item) {
        Producto producto = pedido_Item.getProducto();
        if ( producto == null ) {
            return null;
        }
        return producto.getNombre();
    }
}
