package ua.com.smiddle.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.com.smiddle.proxy.soap.RecStartRequest;
import ua.com.smiddle.proxy.soap.RecStartResponse;

/**
 * @author ksa on 14.12.16.
 * @project soap_rest_proxy
 */

@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://proxy.smiddle.com.ua/soap";
    @Autowired
    private SenderREST sender;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecStartRequest")
    @ResponsePayload
    public RecStartResponse RecStart(@RequestPayload RecStartRequest req) throws Exception {
        System.out.println(req.getCucmCallID()+" "+req.getDestinationDN());
//        String resp = sender.RecStart(req.getCucmCallID(), req.getUserLogin(), req.getPhoneDN(), req.getDestinationDN());
        RecStartResponse recStartResp = new RecStartResponse();
        recStartResp.setSessionId(String.valueOf(10));
        return recStartResp;
    }
}
