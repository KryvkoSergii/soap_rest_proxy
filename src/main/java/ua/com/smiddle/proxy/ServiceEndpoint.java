package ua.com.smiddle.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ua.com.smiddle.proxy.model.json.crm.CRMCallStartReq;
import ua.com.smiddle.proxy.model.json.crm.CRMLogout;
import ua.com.smiddle.proxy.model.json.crm.Info;
import ua.com.smiddle.proxy.model.json.crm.ReporterRequest;
import ua.com.smiddle.proxy.soap.*;

import javax.annotation.PostConstruct;
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
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ServiceEndpoint.class);
    private static final String recStart = "RecStart";
    private static final String recGetInfo = "RecGetInfo";
    private static final String recSearch = "RecSearch";
    private static final String recLogout = "RecLogout";
    private static final String getSessionInfo = "getSessionInfo";

    @PostConstruct
    private void init(){
        logger.info("initialized");
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecStartRequest")
    @ResponsePayload
    public RecStartResponse RecStart(@RequestPayload RecStartRequest req) throws Exception {
        logger.info(recStart.concat(": got request=") + req.toString());
        CRMCallStartReq crmCallStartReq = new CRMCallStartReq();
        crmCallStartReq.setLogin(req.getUserLogin());
        crmCallStartReq.setDestinationDN(req.getDestinationDN());
        crmCallStartReq.setSourceDN(req.getPhoneDN());
        crmCallStartReq.setCcid(req.getCucmCallID());
        RecStartResponse recStartResp = new RecStartResponse();
        try {
            recStartResp.setSessionId(sender.RecStart(crmCallStartReq));
        } catch (Exception e) {
            logger.error(recStart.concat(": processed sessionID throw Exception=" + e.getMessage()));
//            recStartResp.setSessionId("");
            throw e;
        }
        logger.debug(recStart.concat(": processing sessionID=" + req.toString()));
        return recStartResp;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecLogoutRequest")
    @ResponsePayload
    public void RecLogoutRequest(@RequestPayload RecLogoutRequest req) throws Exception {
        logger.info(recLogout.concat(": got request=") + req.toString());
        CRMLogout crmLogout = new CRMLogout();
        crmLogout.setPhone(req.getPhoneDN());
        crmLogout.setLoginAD(req.getUserLogin());
        try {
            logger.debug(recLogout.concat(": sending to SR=" + crmLogout.toString()));
            String tmp = sender.RecLogout(crmLogout);
            logger.debug(recLogout.concat(": command state from SR=" + tmp));
        } catch (Exception e) {
            logger.error(recLogout.concat(": processing Info throw Exception=" + e.getMessage()));
            throw e;
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecGetInfoRequest")
    @ResponsePayload
    public RecGetInfoResponse RecGetInfo(@RequestPayload RecGetInfoRequest req) throws Exception {
        logger.info(recGetInfo.concat(": got request=") + req.toString());
        ReporterRequest reporterRequest = new ReporterRequest();
        reporterRequest.setCrmCallId(req.getSessionId());
        Info resp = null;
        try {
            String tmp = sender.RecGetInfo(reporterRequest);
            logger.debug(recGetInfo.concat(": received from SR=" + tmp));
            resp = (Info) JacksonUtil.jsonToObject(tmp, Info.class);
            logger.debug(recGetInfo.concat(": processed Info=" + resp));
        } catch (Exception e) {
            logger.error(recStart.concat(": processing Info throw Exception=" + e.getMessage()));
            throw e;
        }
        RecGetInfoResponse recGetInfoResponse = new RecGetInfoResponse();
        SessionInfo sessionInfo = getSessionInfo(resp);
        recGetInfoResponse.setSessionInfo(sessionInfo);
        logger.debug(recGetInfo.concat(": processed RecGetInfoResponse=" + recGetInfoResponse.toString()));
        return recGetInfoResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "RecSearchRequest")
    @ResponsePayload
    public RecSearchResponse RecSearch(@RequestPayload RecSearchRequest req) throws Exception {
        logger.info(recSearch.concat(": got request=") + req.toString());
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
            logger.debug(recSearch.concat(": processed ReporterRequest=" + reporterRequest.toString()));
            String tmp = sender.RecGetInfo(reporterRequest);
            logger.debug(recSearch.concat(": received from SR=" + tmp));
            resp = (Info) JacksonUtil.jsonToObject(tmp, Info.class);
            logger.debug(recSearch.concat(": processed Info=" + resp));
        } catch (Exception e) {
            logger.error(recSearch.concat(": processing Info throw Exception=" + e.getMessage()));
            throw e;
        }
        RecSearchResponse recSearchResponse = new RecSearchResponse();
        SessionInfoList list = new SessionInfoList();
        list.setSessionInfo(getSessionInfo(resp));
        recSearchResponse.getSessionInfoList().add(list);
        logger.debug(recSearch.concat(": processed RecSearchResponse=" + recSearchResponse.toString()));
        return recSearchResponse;
    }

    private SessionInfo getSessionInfo(Info resp) {
        logger.debug(getSessionInfo.concat(": received Info=" + resp.toString()));
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

            if (sessionInfo.getTrack() == null) {
                t = new SessionInfo.Track();
                trackInfoVO = new TrackInfoVO();
                t.setTrackInfoVO(trackInfoVO);
                sessionInfo.getTrack().add(t);
            }
        }
        logger.debug(getSessionInfo.concat(": processed SessionInfo=" + sessionInfo.toString()));
        return sessionInfo;
    }

}
