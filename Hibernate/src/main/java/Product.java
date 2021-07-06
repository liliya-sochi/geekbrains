import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private int cost;

    @ManyToMany
    @JoinTable(
            name = "box",
            joinColumns = @JoinColumn(name = "product-id"),
            inverseJoinColumns = @JoinColumn(name = "user-id")
    )
    private List<User> users;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public Product() {
    }

    public Product(String title, int price) {
        this.title = title;
        this.cost = price;
    }

    @Override
    public String toString(){
        return String.format("Product [id = %d, title = '%s', cost = %d]", id, title, cost);
    }
}
