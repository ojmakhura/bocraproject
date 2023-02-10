package bw.org.bocra.portal.keycloak.smtp;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class RealmSmtpConfigId implements Serializable {

    @Column(name = "realm_id", unique = true, nullable = false, insertable = true, updatable = true)
    private String realmId;
    private String name;

    public RealmSmtpConfigId() {
    }

    
    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "{ realmId=" + realmId + ", name=" + name + " }";
    }

}
