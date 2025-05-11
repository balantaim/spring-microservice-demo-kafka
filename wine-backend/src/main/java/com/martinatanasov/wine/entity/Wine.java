package com.martinatanasov.wine.entity;


import com.martinatanasov.parent.model.WineType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "wines")
public class Wine {

    @Id
    @GeneratedValue(generator = "UUID")
    @UuidGenerator
    @Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String wineName;

    @Column(length = 50, nullable = false)
    private String brand;

    @Enumerated(value = EnumType.STRING)
    private WineType wineType;

    @Version
    private Integer version;

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

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedDate;

    public void addCategory(Category category){
        this.categories.add(category);
        category.getWines().add(this);
    }

    public void removeCategory(Category category){
        this.categories.remove(category);
        category.getWines().remove(category);
    }

    @OneToMany(mappedBy = "wine")
    private Set<OrderLine> orderLines = new HashSet<>();

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "wine_category",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

}
