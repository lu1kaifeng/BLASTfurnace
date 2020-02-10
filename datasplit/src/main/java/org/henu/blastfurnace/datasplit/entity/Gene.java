package org.henu.blastfurnace.datasplit.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "nCoV")
public class Gene implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
