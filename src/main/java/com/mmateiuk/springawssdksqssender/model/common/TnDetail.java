package com.mmateiuk.springawssdksqssender.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@JsonIgnoreProperties
public class TnDetail implements Serializable {
    @JsonProperty("tn")
    private String tn;

    public TnDetail() {
    }

    public TnDetail(String tn) {
        this.tn = tn;
    }

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TnDetail tnDetail = (TnDetail) o;
        return Objects.equals(tn, tnDetail.tn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tn);
    }

    @Override
    public String toString() {
        return "TnDetail{" +
                "tn='" + tn + '\'' +
                '}';
    }
}
