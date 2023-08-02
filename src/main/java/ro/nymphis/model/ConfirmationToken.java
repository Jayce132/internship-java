package ro.nymphis.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ro.nymphis.model.user.User;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="user_token")
public class ConfirmationToken {

    @SequenceGenerator(
            name = "confirmation_token_sequence",
            sequenceName = "confirmation_token_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "confirmation_token_sequence"
    )
    private Long id;

    @Column(nullable = false)
    private String token;


    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(
            nullable = false,
            name = "user_id"
    )
    private User user;

    public ConfirmationToken(User user) {
        this.token = Timestamp.valueOf(LocalDateTime.now()).toString();
        this.user = user;

    }
}