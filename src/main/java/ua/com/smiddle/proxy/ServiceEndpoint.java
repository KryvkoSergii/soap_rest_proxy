package ua.com.smiddle.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.com.smiddle.proxy.soap.*;

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
    public RecStartResponse RecStart(@RequestPayload RecStartRequest req) {
//        String resp = sender.RecStart(req.getCucmCallID(), req.getUserLogin(), req.getPhoneDN(), req.getDestinationDN());
        RecStartResponse recStartResp = new RecStartResponse();
        recStartResp.setSessionId(String.valueOf(10));
        return recStartResp;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecGetInfoRequest")
    @ResponsePayload
    public RecGetInfoResponse RecGetInfo(@RequestPayload RecGetInfoRequest req) {
//        String resp = sender.RecStart(req.getCucmCallID(), req.getUserLogin(), req.getPhoneDN(), req.getDestinationDN());
        RecGetInfoResponse recGetInfoResponse = new RecGetInfoResponse();
        SessionInfo.Track t = new SessionInfo.Track();
        t.setTrackInfoVO(new TrackInfoVO());
        SessionInfo si = new SessionInfo();
        si.setBaseURL("url");
        si.setTracksCount(1);
        si.getTrack().add(t);
        recGetInfoResponse.setSessionInfo(si);
        return recGetInfoResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecSearchRequest")
    @ResponsePayload
    public RecSearchResponse RecSearch(@RequestPayload RecSearchRequest req) {
//        String resp = sender.RecStart(req.getCucmCallID(), req.getUserLogin(), req.getPhoneDN(), req.getDestinationDN());
        RecSearchResponse rec = new RecSearchResponse();

        SessionInfo.Track t = new SessionInfo.Track();
        t.setTrackInfoVO(new TrackInfoVO());
        SessionInfo si = new SessionInfo();
        si.setBaseURL("url");
        si.setTracksCount(1);
        si.getTrack().add(t);

        SessionInfoList l = new SessionInfoList();
        l.setSessionInfo(si);
        rec.getSessionInfoList().add(l);
        return rec;
    }


}
