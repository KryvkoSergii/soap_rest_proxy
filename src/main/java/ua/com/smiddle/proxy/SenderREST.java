package ua.com.smiddle.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @author ksa on 14.12.16.
 * @project soap_rest_proxy
 */
@Component
public class SenderREST {
    @Autowired
    private Environment env;


    public String RecStart(String CucmCallID, String UserLogin, String PhoneDN, String DestinationDN) throws Exception {
        return makeRequest("server.url.recStart","server.url.recStart.method",new Object());
    }

    private String makeRequest(String actionURLCode, String method, Object request) throws Exception {
        URL url = new URL(buildURL(actionURLCode));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Accept", "*/*");
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.connect();
        String json = JacksonUtil.objectToJson(request);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Charset.forName("UTF-8")));
        bw.write(json);
        bw.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }

        connection.disconnect();
        bw.close();
        reader.close();
        return out.toString();
    }

    private String buildURL(String actionURLCode) {
        String url = env.getProperty("server.url.base").concat(actionURLCode);
        return url;
    }
}
