package com.api.devtest.entity;

import lombok.Data;
        import lombok.NoArgsConstructor;

        import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_DEVTEST_CUS_ACNT")
public class CusAcnt {

    @Id
    private String cusAcn;  //고객계좌번호

    private String cusNm;   //고객명

    @ManyToOne
    @JoinColumn(name = "mngmBrcd")
    private MngmBrn mngmBrn;    //관리점코드
}
