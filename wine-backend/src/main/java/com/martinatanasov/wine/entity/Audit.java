package com.martinatanasov.wine.entity;

import com.martinatanasov.parent.model.WineType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "audits")
public class Audit {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID auditId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Integer version;

    @Column(name = "wine_name", length = 100)
    private String wineName;

    @Column(length = 50, nullable = false)
    private String brand;

    @Enumerated(value = EnumType.STRING)
    private WineType wineType;

    @Column(length = 50, nullable = false)
    private String country;

    @Column(name = "product_number")
    private String productNumber;

    @Column(name = "year_of_bottling", nullable = false)
    private Integer yearOfBottling;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "created_date_audit")
    @CreationTimestamp
    private LocalDateTime createdDateAudit;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "event_type")
    private String eventType;

}
