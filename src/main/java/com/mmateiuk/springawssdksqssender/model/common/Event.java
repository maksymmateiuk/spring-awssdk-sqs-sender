package com.mmateiuk.springawssdksqssender.model.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Objects;

public class Event {

    @JsonProperty("version")
    private String version;

    @JsonProperty("id")
    private String id;

    @JsonProperty("detail-type")
    private String detailType;

    @JsonProperty("source")
    private String source;

    @JsonProperty("account")
    private String account;

    @JsonProperty("time")
    private String time;

    @JsonProperty("region")
    private String region;

    @JsonProperty("resources")
    private String[] resources;

    @JsonProperty("detail")
    private TnDetail tnDetail;

    public TnDetail getTnDetail() {
        return tnDetail;
    }

    public void setTnDetail(TnDetail tnDetail) {
        this.tnDetail = tnDetail;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetailType() {
        return detailType;
    }

    public void setDetailType(String detailType) {
        this.detailType = detailType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return Objects.equals(version, event.version) && Objects.equals(id, event.id)
                && Objects.equals(detailType, event.detailType) && Objects.equals(source, event.source)
                && Objects.equals(account, event.account) && Objects.equals(time, event.time)
                && Objects.equals(region, event.region) && Arrays.equals(resources, event.resources)
                && Objects.equals(tnDetail, event.tnDetail);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(version, id, detailType, source, account, time, region, tnDetail);
        result = 31 * result + Arrays.hashCode(resources);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "version='" + version + '\'' +
                ", id='" + id + '\'' +
                ", detailType='" + detailType + '\'' +
                ", source='" + source + '\'' +
                ", account='" + account + '\'' +
                ", time='" + time + '\'' +
                ", region='" + region + '\'' +
                ", resources=" + Arrays.toString(resources) +
                ", tnDetail=" + tnDetail +
                '}';
    }
}
