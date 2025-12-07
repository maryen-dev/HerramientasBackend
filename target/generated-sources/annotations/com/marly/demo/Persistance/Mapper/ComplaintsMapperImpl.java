package com.marly.demo.Persistance.Mapper;

import com.marly.demo.Domain.Complaints;
import com.marly.demo.Persistance.Entity.Reclamos;
import com.marly.demo.Persistance.Entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-07T18:39:29-0500",
    comments = "version: 1.6.3, compiler: Eclipse JDT (IDE) 3.44.0.v20251118-1623, environment: Java 21.0.9 (Eclipse Adoptium)"
)
@Component
public class ComplaintsMapperImpl implements ComplaintsMapper {

    @Override
    public Complaints toComplaints(Reclamos reclamos) {
        if ( reclamos == null ) {
            return null;
        }

        Complaints complaints = new Complaints();

        complaints.setIdcomplaints( reclamos.getIdreclamo() );
        complaints.setOrderdate( reclamos.getFechapedido() );
        complaints.setClaimreason( reclamos.getMotivoreclamo() );
        complaints.setDetail( reclamos.getDetalle() );
        if ( reclamos.getEstadoreclamo() != null ) {
            complaints.setClaimstatus( reclamos.getEstadoreclamo().name() );
        }
        complaints.setClaimdate( reclamos.getFechareclamo() );
        complaints.setUserId( reclamosUsuarioId( reclamos ) );

        return complaints;
    }

    @Override
    public List<Complaints> toComplaintsList(List<Reclamos> reclamosList) {
        if ( reclamosList == null ) {
            return null;
        }

        List<Complaints> list = new ArrayList<Complaints>( reclamosList.size() );
        for ( Reclamos reclamos : reclamosList ) {
            list.add( toComplaints( reclamos ) );
        }

        return list;
    }

    @Override
    public Reclamos toReclamos(Complaints complaints) {
        if ( complaints == null ) {
            return null;
        }

        Reclamos reclamos = new Reclamos();

        reclamos.setIdreclamo( complaints.getIdcomplaints() );
        reclamos.setFechapedido( complaints.getOrderdate() );
        reclamos.setMotivoreclamo( complaints.getClaimreason() );
        reclamos.setDetalle( complaints.getDetail() );
        if ( complaints.getClaimstatus() != null ) {
            reclamos.setEstadoreclamo( Enum.valueOf( Reclamos.EstadoReclamo.class, complaints.getClaimstatus() ) );
        }
        reclamos.setFechareclamo( complaints.getClaimdate() );

        return reclamos;
    }

    private Long reclamosUsuarioId(Reclamos reclamos) {
        Usuario usuario = reclamos.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
