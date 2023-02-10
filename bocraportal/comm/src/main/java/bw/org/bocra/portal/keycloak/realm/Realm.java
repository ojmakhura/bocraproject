package bw.org.bocra.portal.keycloak.realm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Realm {

    @Id
    private String id;
    private String name;

    public Realm() {
    }

    public Realm(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Realm [id=" + id + ", name=" + name + "]";
    }

}
