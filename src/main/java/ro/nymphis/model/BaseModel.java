package ro.nymphis.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@MappedSuperclass
public abstract class BaseModel<I extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected I id;
}
