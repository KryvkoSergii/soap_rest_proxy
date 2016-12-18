package ua.com.smiddle.proxy.model.json.crm;

import java.io.Serializable;

/**
 * Added by A.Osadchuk on 16.09.2016 at 15:55.
 * Project: SmiddleRecording
 */
public class CRMCallStartReq implements Serializable {
    private String login;
    private String sourceDN;
    private String destinationDN;
    private String ccid;


    //Constructors
    public CRMCallStartReq() {
    }


    //Getters & setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSourceDN() {
        return sourceDN;
    }

    public void setSourceDN(String sourceDN) {
        this.sourceDN = sourceDN;
    }

    public String getDestinationDN() {
        return destinationDN;
    }

    public void setDestinationDN(String destinationDN) {
        this.destinationDN = destinationDN;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CRMCallStartReq{");
        sb.append("login=").append(login);
        sb.append(", sourceDN=").append(sourceDN);
        sb.append(", destinationDN=").append(destinationDN);
        sb.append(", ccid=").append(ccid);
        sb.append('}');
        return sb.toString();
    }
}
