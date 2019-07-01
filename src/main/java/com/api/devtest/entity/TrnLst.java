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
    private Date trnYmd;    //거래년월일

    @Id
    @ManyToOne
    @JoinColumn(name = "cusAcn")
    private CusAcnt cusAcnt;    //고객계좌번호

    @Id
    private int trnNo;  //거래일련번호

    private long trnAmt;    //거래금액

    private long feeAmt;    //수수료금액

    private char cnclYn;    //취소여부
}
