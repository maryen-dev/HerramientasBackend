package com.marly.demo.Persistance.Mapper;

import com.marly.demo.Domain.Role;
import com.marly.demo.Domain.User;
import com.marly.demo.Persistance.Entity.Rol;
import com.marly.demo.Persistance.Entity.Usuario;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T18:39:29-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User toUser(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        User user = new User();

        user.setId( usuario.getId() );
        user.setFirstName( usuario.getNombre() );
        user.setLastName( usuario.getApellidos() );
        user.setEmail( usuario.getCorreo() );
        user.setAddress( usuario.getDireccion() );
        user.setPhone( usuario.getTelefono() );
        user.setPassword( usuario.getContraseña() );
        user.setRoles( rolSetToRoleSet( usuario.getRol() ) );
        user.setDni( usuario.getDni() );

        return user;
    }

    @Override
    public Usuario toUsuario(User user) {
        if ( user == null ) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setId( user.getId() );
        usuario.setNombre( user.getFirstName() );
        usuario.setApellidos( user.getLastName() );
        usuario.setCorreo( user.getEmail() );
        usuario.setDireccion( user.getAddress() );
        usuario.setTelefono( user.getPhone() );
        usuario.setContraseña( user.getPassword() );
        usuario.setRol( roleSetToRolSet( user.getRoles() ) );
        usuario.setDni( user.getDni() );

        return usuario;
    }

    protected Set<Role> rolSetToRoleSet(Set<Rol> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Rol rol : set ) {
            set1.add( roleMapper.toRole( rol ) );
        }

        return set1;
    }

    protected Set<Rol> roleSetToRolSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<Rol> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( Role role : set ) {
            set1.add( roleMapper.toRol( role ) );
        }

        return set1;
    }
}
