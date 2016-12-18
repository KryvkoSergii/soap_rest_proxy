package ua.com.smiddle.proxy.model.json.crm;

import java.io.Serializable;
import java.util.*;

/**
 * Added by A.Osadchuk on 21.07.2016 at 16:57.
 * Project: SmiddleRecording
 */
public class ReporterRequest implements Serializable {
    private Long id;
    private String msSessionId;
    private String crmCallId;
    private String ccid;
    private Date dateFrom;
    private Date dateTo;
    private Long durationFrom;
    private Long durationTo;
    private String userLogin;
    private String userADLogin;
    private String userADName;
    private String lastName;
    private String firstName;
    private String patronymicName;
    private String phone;
    private Integer direction;
    private Integer licensed;
    private Long groupId;
    private Long[] tagIds;
    private Set<Long> groups;
    private TagFilter tagFilter = new TagFilter();
    private boolean showUnmappedCalls;
    private int page;
    private int perPage;


    //Constructors
    public ReporterRequest() {
    }


    //Getters & setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsSessionId() {
        return msSessionId;
    }

    public void setMsSessionId(String msSessionId) {
        this.msSessionId = msSessionId;
    }

    public String getCrmCallId() {
        return crmCallId;
    }

    public void setCrmCallId(String crmCallId) {
        this.crmCallId = crmCallId;
    }

    public String getCcid() {
        return ccid;
    }

    public void setCcid(String ccid) {
        this.ccid = ccid;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Long getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(Long durationFrom) {
        this.durationFrom = durationFrom;
    }

    public Long getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(Long durationTo) {
        this.durationTo = durationTo;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserADLogin() {
        return userADLogin;
    }

    public void setUserADLogin(String userADLogin) {
        this.userADLogin = userADLogin;
    }

    public String getUserADName() {
        return userADName;
    }

    public void setUserADName(String userADName) {
        this.userADName = userADName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymicName() {
        return patronymicName;
    }

    public void setPatronymicName(String patronymicName) {
        this.patronymicName = patronymicName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Integer getLicensed() {
        return licensed;
    }

    public void setLicensed(Integer licensed) {
        this.licensed = licensed;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(Long[] tagIds) {
        this.tagIds = tagIds;
    }

    public Set<Long> getGroups() {
        return groups;
    }

    public void setGroups(Set<Long> groups) {
        this.groups = groups;
    }

    public TagFilter getTagFilter() {
        return tagFilter;
    }

    public void setTagFilter(TagFilter tagFilter) {
        this.tagFilter = tagFilter;
    }

    public boolean isShowUnmappedCalls() {
        return showUnmappedCalls;
    }

    public void setShowUnmappedCalls(boolean showUnmappedCalls) {
        this.showUnmappedCalls = showUnmappedCalls;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }


    //Methods
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ReporterRequest{");
        sb.append(", id=").append(id);
        sb.append(", msSessionId=").append(msSessionId);
        sb.append(", crmCallId=").append(crmCallId);
        sb.append(", ccid=").append(ccid);
        sb.append(", dateFrom=").append(dateFrom);
        sb.append(", dateTo=").append(dateTo);
        sb.append(", durationFrom=").append(durationFrom);
        sb.append(", durationTo=").append(durationTo);
        sb.append(", userLogin=").append(userLogin);
        sb.append(", userADLogin=").append(userADLogin);
        sb.append(", userADName=").append(userADName);
        sb.append(", lastName=").append(lastName);
        sb.append(", firstName=").append(firstName);
        sb.append(", patronymicName=").append(patronymicName);
        sb.append(", phone=").append(phone);
        sb.append(", direction=").append(direction);
        sb.append(", licensed=").append(licensed);
        sb.append(", groupId=").append(groupId);
        sb.append(", tagFilter=").append(tagFilter);
        sb.append(", tagIds=").append(Arrays.deepToString(tagIds));
        sb.append(", groups=").append(groups);
        sb.append(", showUnmappedCalls=").append(showUnmappedCalls);
        sb.append(", page=").append(page);
        sb.append(", perPage=").append(perPage);
        sb.append('}');
        return sb.toString();
    }


    //Inner class
    public class TagFilter implements Serializable {
        private List<Long> tagIds = new ArrayList<>(0);
        private boolean and;


        //Constructors
        public TagFilter() {
        }


        //Getters & setters
        public List<Long> getTagIds() {
            return tagIds;
        }

        public void setTagIds(List<Long> tagIds) {
            this.tagIds = tagIds;
        }

        public boolean isAnd() {
            return and;
        }

        public void setAnd(boolean and) {
            this.and = and;
        }


        //Methods
        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("TagFilter{");
            sb.append("tagIds=").append(tagIds);
            sb.append(", and=").append(and);
            sb.append('}');
            return sb.toString();
        }
    }
}
