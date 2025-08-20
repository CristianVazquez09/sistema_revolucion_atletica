package com.wolfpack.multitenancy.jpa;

import com.wolfpack.model.Gimnasio;
import jakarta.persistence.*;
import lombok.Data;

@MappedSuperclass
@org.hibernate.annotations.FilterDef(
        name = "tenant", parameters = @org.hibernate.annotations.ParamDef(name = "tenantId", type = Long.class)
)
@org.hibernate.annotations.Filter(name = "tenant", condition = "id_gimnasio = :tenantId")
@Data
public abstract class TenantScoped {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_gimnasio", nullable = false, foreignKey = @ForeignKey(name="fk__gimnasio"))
    protected Gimnasio gimnasio;
}
