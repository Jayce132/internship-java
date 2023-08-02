package ro.nymphis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "persistent_logins")
public class CookieToken {
    @Column(name = "username")
    String username;
    @Id
    @Column(name = "series")
    String series;
    @Column(name = "token")
    String token;
    @Column(name = "last_used")
    Timestamp lastUsed;

}
