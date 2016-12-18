package ua.com.smiddle.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.com.smiddle.proxy.model.json.crm.CRMCallStartReq;
import ua.com.smiddle.proxy.model.json.crm.Info;
import ua.com.smiddle.proxy.model.json.crm.ReporterRequest;
import ua.com.smiddle.proxy.soap.*;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ksa on 14.12.16.
 * @project soap_rest_proxy
 */

@Endpoint
public class ServiceEndpoint {
    private static final String NAMESPACE_URI = "http://proxy.smiddle.com.ua/soap";
    @Autowired
    private SenderREST sender;
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecStartRequest")
    @ResponsePayload
    public RecStartResponse RecStart(@RequestPayload RecStartRequest req) {
        CRMCallStartReq crmCallStartReq = new CRMCallStartReq();
        crmCallStartReq.setLogin(req.getUserLogin());
        crmCallStartReq.setDestinationDN(req.getDestinationDN());
        crmCallStartReq.setSourceDN(req.getPhoneDN());
        crmCallStartReq.setCcid(req.getCucmCallID());
        RecStartResponse recStartResp = new RecStartResponse();
        try {
            recStartResp.setSessionId(sender.RecStart(crmCallStartReq));
        } catch (Exception e) {
            e.printStackTrace();
            recStartResp.setSessionId("");
        }
        System.out.println("session=" + recStartResp.getSessionId());
        return recStartResp;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecGetInfoRequest")
    @ResponsePayload
    public RecGetInfoResponse RecGetInfo(@RequestPayload RecGetInfoRequest req) {
        ReporterRequest reporterRequest = new ReporterRequest();
        reporterRequest.setCrmCallId(req.getSessionId());
        Info resp = null;
        try {
            String tmp = sender.RecGetInfo(reporterRequest);
            resp = (Info) JacksonUtil.jsonToObject(tmp, Info.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RecGetInfoResponse recGetInfoResponse = new RecGetInfoResponse();
        SessionInfo sessionInfo = getSessionInfo(resp);
        recGetInfoResponse.setSessionInfo(sessionInfo);
        return recGetInfoResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecSearchRequest")
    @ResponsePayload
    public RecSearchResponse RecSearch(@RequestPayload RecSearchRequest req) {
        ReporterRequest reporterRequest = new ReporterRequest();
        Info resp = null;
        try {
            Date date;
            if (req.getDateFrom() != null && !req.getDateFrom().isEmpty()) {
                date = formatter.parse(req.getDateFrom());
                reporterRequest.setDateFrom(date);
                if (req.getDateFromDuration() > 0)
                    reporterRequest.setDateTo(new Date(date.getTime() + req.getDateFromDuration()));
            }
            if (req.getDurationFrom() > 0)
                reporterRequest.setDurationFrom(Long.valueOf(req.getDurationFrom()));
            if (req.getDurationTo() > 0)
                reporterRequest.setDurationTo(Long.valueOf(req.getDurationTo()));
            reporterRequest.setPhone(req.getPhoneNumberA());
            reporterRequest.setPhone(req.getPhoneNumberB());
            reporterRequest.setUserADLogin(req.getUserLogin());
            String tmp = sender.RecGetInfo(reporterRequest);
            resp = (Info) JacksonUtil.jsonToObject(tmp, Info.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        RecSearchResponse recSearchResponse = new RecSearchResponse();
        SessionInfoList list = new SessionInfoList();
        list.setSessionInfo(getSessionInfo(resp));
        recSearchResponse.getSessionInfoList().add(list);
        return recSearchResponse;
    }

    private SessionInfo getSessionInfo(Info resp) {
        SessionInfo sessionInfo = new SessionInfo();
        if (resp != null) {
            sessionInfo.setBaseURL(resp.getBaseURL());
            sessionInfo.setTracksCount(resp.getTracksCount());
            SessionInfo.Track t;
            TrackInfoVO trackInfoVO;
            for (Long l : resp.getTrack()) {
                t = new SessionInfo.Track();
                trackInfoVO = new TrackInfoVO();
                trackInfoVO.setTrackNumber(BigInteger.valueOf(l));
                t.setTrackInfoVO(trackInfoVO);
                sessionInfo.getTrack().add(t);
            }

            if(sessionInfo.getTrack()==null){
                t = new SessionInfo.Track();
                trackInfoVO = new TrackInfoVO();
                t.setTrackInfoVO(trackInfoVO);
                sessionInfo.getTrack().add(t);
            }
        }
        return sessionInfo;
    }

}
