package ua.com.smiddle.proxy.model.json.crm;

import java.util.List;

/**
 * Added by A.Osadchuk on 15.12.2016 at 15:57.
 * Project: SmiddleRecording
 */
public class Info {
    private String baseURL;
    private int tracksCount;
    private List<Long> track;


    public Info() {
    }

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }

    public int getTracksCount() {
        return tracksCount;
    }

    public void setTracksCount(int tracksCount) {
        this.tracksCount = tracksCount;
    }

    public List<Long> getTrack() {
        return track;
    }

    public void setTrack(List<Long> track) {
        this.track = track;
    }
}
