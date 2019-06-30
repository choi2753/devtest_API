package com.api.devtest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_DEVTEST_TRN_LST")
public class TrnLst implements Serializable {

    @Id
    private Date trnYmd;

    @Id
    @ManyToOne
    @JoinColumn(name = "cusAcn")
    private CusAcnt cusAcnt;

    @Id
    private int trnNo;

    private long trnAmt;

    private long feeAmt;

    private char cnclYn;
}
