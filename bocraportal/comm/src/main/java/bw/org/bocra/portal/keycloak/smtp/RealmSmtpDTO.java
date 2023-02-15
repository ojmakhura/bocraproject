package bw.org.bocra.portal.keycloak.smtp;

public class RealmSmtpDTO {

    public boolean auth;
    public String envelopeForm;
    public String from;
    public String fromDisplayName;
    public String host;
    public String password;
    public int port;
    public String replyTo;
    public String replyToDisplayName;
    public boolean ssl;
    public boolean startTLS;
    public String user;

    public RealmSmtpDTO() {
    }

    public boolean isAuth() {
        return auth;
    }

    public void setAuth(boolean auth) {
        this.auth = auth;
    }

    public String getEnvelopeForm() {
        return envelopeForm;
    }

    public void setEnvelopeForm(String envelopeForm) {
        this.envelopeForm = envelopeForm;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromDisplayName() {
        return fromDisplayName;
    }

    public void setFromDisplayName(String fromDisplayName) {
        this.fromDisplayName = fromDisplayName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getReplyToDisplayName() {
        return replyToDisplayName;
    }

    public void setReplyToDisplayName(String replyToDisplayName) {
        this.replyToDisplayName = replyToDisplayName;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public boolean isStartTLS() {
        return startTLS;
    }

    public void setStartTLS(boolean startTLS) {
        this.startTLS = startTLS;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RealmSmtpDTO [auth=" + auth + ", envelopeForm=" + envelopeForm + ", from=" + from + ", fromDisplayName="
                + fromDisplayName + ", host=" + host + ", password=" + password + ", port=" + port + ", replyTo="
                + replyTo + ", replyToDisplayName=" + replyToDisplayName + ", ssl=" + ssl + ", startTLS=" + startTLS
                + ", user=" + user + "]";
    }

}
