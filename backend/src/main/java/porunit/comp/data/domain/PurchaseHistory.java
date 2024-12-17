package porunit.comp.data.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "purchasehistory")
@Data
public class PurchaseHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchase_history_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_userdata", nullable = false)
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "id_purchase", nullable = false)
    private Purchase purchase;

}