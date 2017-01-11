package ua.com.smiddle.proxy;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
    private static final String module = "SenderREST";
    private static final String recStart = "RecStart";
    private static final String recGetInfo = "RecGetInfo";
    private static final String recLogout = "RecLogout";
    private static final String makeRequest = "makeRequest";
    private static final String successful = "successful";
    private static final Logger logger = Logger.getLogger(SenderREST.class);

    @PostConstruct
    private void init() {
        logger.info("initialized");
    }


    public String RecStart(Object body) throws Exception {
        return makeRequest("server.url.recStart", "server.url.recStart.method", body, recStart, true);
    }

    public String RecGetInfo(Object body) throws Exception {
        String resp = makeRequest("server.url.recGetInfo", "server.url.recGetInfo.method", body, recGetInfo, true);
        return resp;
    }

    public String RecLogout(Object body) throws Exception {
        return makeRequest("server.url.recLogout", "server.url.recLogout.method", body, recLogout, true);
    }

    private String makeRequest(String actionURLCode, String method, Object request, String requestedMethodName,
                               boolean requiredResponse) throws Exception {
        URL url = new URL(buildURL(actionURLCode));
        logger.debug(makeRequest.concat(" got request="));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(env.getProperty(method));
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("Accept", "*/*");
        if (requiredResponse)
            connection.setDoOutput(true);
        connection.setDoInput(true);
        logger.debug(makeRequest.concat(":")
                .concat("requested by=")
                .concat(requestedMethodName)
                .concat(" URL=")
                .concat(connection.getURL().toString())
                .concat(" " + connection.getRequestMethod() + " ")
                .concat(" body=")
                .concat(request.toString()));
        connection.connect();

        String json = JacksonUtil.objectToJson(request);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), Charset.forName("UTF-8")));
        bw.write(json);
        bw.flush();

        BufferedReader reader = null;
        StringBuilder out = null;
        if (requiredResponse) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                out.append(line);
            }
        }

        connection.disconnect();
        bw.close();
        if (requiredResponse) {
            reader.close();
            return out.toString();
        } else
            return successful;
    }

    private String buildURL(String actionURLCode) {
        String url = env.getProperty("server.url.base").concat(env.getProperty(actionURLCode));
        return url;
    }
}
