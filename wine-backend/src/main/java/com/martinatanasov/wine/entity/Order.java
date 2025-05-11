package com.martinatanasov.wine.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    public Order(UUID id,
                 Long version,
                 Timestamp createdDate,
                 Timestamp lastModifiedDate,
                 String customerRef,
                 Customer customer,
                 BigDecimal paymentAmount,
                 Set<OrderLine> orderLines,
                 Shipment shipment) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
        this.customerRef = customerRef;
        this.setCustomer(customer);
        this.setPaymentAmount(paymentAmount);
        this.setOrderLines(orderLines);
        this.setShipment(shipment);
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Timestamp lastModifiedDate;

    public boolean isNew() {
        return this.id == null;
    }

    @Column(name = "customer_ref")
    private String customerRef;

    @Column(name = "payment_amount")
    private BigDecimal paymentAmount;

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customer = customer;
            customer.getOrders().add(this);
        }
    }

    public void setShipment(Shipment shipment) {
        if (shipment != null) {
            this.shipment = shipment;
            shipment.setOrder(this);
        }
    }

    public void setOrderLines(Set<OrderLine> orderLines) {
        if (orderLines != null) {
            this.orderLines = orderLines;
            orderLines.forEach(beerOrderLine -> beerOrderLine.setOrder(this));
        }
    }

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.PERSIST)
    private Shipment shipment;

}
