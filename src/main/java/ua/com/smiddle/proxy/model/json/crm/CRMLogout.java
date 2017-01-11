package ua.com.smiddle.proxy.model.json.crm;

import java.io.Serializable;

/**
 * Created by Andrii M. Osadchuk on 10.01.2017.
 * Project: SmiddleRecording
 */
public class CRMLogout implements Serializable {
    private String phone;
    private String loginAD;


    //Constructors
    public CRMLogout() {
    }


    //Getters & setters
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLoginAD() {
        return loginAD;
    }

    public void setLoginAD(String loginAD) {
        this.loginAD = loginAD;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CRMLogout{");
        sb.append("phone='").append(phone).append('\'');
        sb.append(", loginAD='").append(loginAD).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
