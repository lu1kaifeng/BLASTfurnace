package org.henu.blastfurnace.master.model;


import java.io.Serializable;

public class Gene implements Serializable {
    private Long id;
    private String Numts_ID;
    private String strand;
    private String chr;
    private Long starts;
    private Long ends;
    private String sub;
    private Integer subLen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumts_ID() {
        return Numts_ID;
    }

    public void setNumts_ID(String numts_ID) {
        Numts_ID = numts_ID;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
    }

    public Long getStarts() {
        return starts;
    }

    public void setStarts(Long starts) {
        this.starts = starts;
    }

    public Long getEnds() {
        return ends;
    }

    public void setEnds(Long ends) {
        this.ends = ends;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public Integer getSubLen() {
        return subLen;
    }

    public void setSubLen(Integer subLen) {
        this.subLen = subLen;
    }
}
