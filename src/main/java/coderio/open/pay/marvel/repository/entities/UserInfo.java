package coderio.open.pay.marvel.repository.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo {

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String userName;

    private String password;

    @PrePersist
    private void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }
    }

    public UserInfo() {

    }

}
