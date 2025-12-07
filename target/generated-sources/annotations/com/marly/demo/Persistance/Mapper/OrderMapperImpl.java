package com.marly.demo.Persistance.Mapper;

import com.marly.demo.Domain.Order;
import com.marly.demo.Domain.OrderItem;
import com.marly.demo.Persistance.Entity.Pedido;
import com.marly.demo.Persistance.Entity.Pedido_Item;
import com.marly.demo.Persistance.Entity.Usuario;
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
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public Order toOrder(Pedido pedido) {
        if ( pedido == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( pedido.getIdPedido() );
        order.setDate( pedido.getFecha() );
        if ( pedido.getEstado() != null ) {
            order.setStatus( pedido.getEstado().name() );
        }
        order.setTotal( pedido.getTotal() );
        Long id = pedidoUsuarioId( pedido );
        if ( id != null ) {
            order.setUserID( id.intValue() );
        }
        order.setItems( orderItemMapper.toOrderItems( pedido.getItems() ) );

        return order;
    }

    @Override
    public List<Order> toOrders(List<Pedido> pedidos) {
        if ( pedidos == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( pedidos.size() );
        for ( Pedido pedido : pedidos ) {
            list.add( toOrder( pedido ) );
        }

        return list;
    }

    @Override
    public Pedido toPedido(Order order) {
        if ( order == null ) {
            return null;
        }

        Pedido pedido = new Pedido();

        pedido.setIdPedido( order.getOrderId() );
        pedido.setFecha( order.getDate() );
        if ( order.getStatus() != null ) {
            pedido.setEstado( Enum.valueOf( Pedido.EstadoPedido.class, order.getStatus() ) );
        }
        pedido.setTotal( order.getTotal() );
        pedido.setItems( orderItemListToPedido_ItemList( order.getItems() ) );

        return pedido;
    }

    private Long pedidoUsuarioId(Pedido pedido) {
        Usuario usuario = pedido.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }

    protected List<Pedido_Item> orderItemListToPedido_ItemList(List<OrderItem> list) {
        if ( list == null ) {
            return null;
        }

        List<Pedido_Item> list1 = new ArrayList<Pedido_Item>( list.size() );
        for ( OrderItem orderItem : list ) {
            list1.add( orderItemMapper.toPedidoItem( orderItem ) );
        }

        return list1;
    }
}
