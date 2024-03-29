package springeshop.website.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Products {
    private static final String SEQ_NAME = "product_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    private String title;
    private Double price;
    private Long numbers;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    public Products() {
    }

    public Products(String title, Double price, Long numbers) {
        this.title = title;
        this.price = price;
        this.numbers = numbers;
    }
}
