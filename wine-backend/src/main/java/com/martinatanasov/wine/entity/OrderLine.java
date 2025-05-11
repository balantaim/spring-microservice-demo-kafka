package com.martinatanasov.wine.entity;


import com.martinatanasov.parent.model.WineOrderLineStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false )
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date", nullable = false)
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }

    @ManyToOne
    @JoinColumn(name = "wine_id")
    private Wine wine;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Min(value = 1, message = "Quantity On Hand must be greater than 0")
    @Column(name = "order_quantity")
    private Integer orderQuantity = 1;

    @Column(name = "quantity_allocated")
    private Integer quantityAllocated = 0;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private WineOrderLineStatus orderLineStatus = WineOrderLineStatus.NEW;

}
