package ro.nymphis.model.order;

import lombok.Data;
import ro.nymphis.model.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "order_user_details")
public class OrderUserDetails extends BaseModel<Long> {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
