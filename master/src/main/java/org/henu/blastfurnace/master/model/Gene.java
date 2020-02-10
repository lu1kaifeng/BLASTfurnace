package org.henu.blastfurnace.master.model;

import java.io.Serializable;

public class Gene implements Serializable {
    private Long id;
    private String geneID;
    private String sub;
    private Integer subLen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeneID() {
        return geneID;
    }

    public void setGeneID(String numtsID) {
        this.geneID = numtsID;
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
