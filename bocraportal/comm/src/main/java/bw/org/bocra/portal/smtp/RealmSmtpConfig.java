package bw.org.bocra.portal.smtp;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class RealmSmtpConfig implements Serializable {

    @EmbeddedId
    private RealmSmtpConfigId id;
    
    private String value;

    public RealmSmtpConfig() {
    }

    public RealmSmtpConfigId getId() {
        return id;
    }

    public void setId(RealmSmtpConfigId id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", value=" + value + "}";
    }
   
}
