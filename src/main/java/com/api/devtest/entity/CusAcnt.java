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
    private String cusAcn;

    private String cusNm;

    @ManyToOne
    @JoinColumn(name = "mngmBrcd")
    private MngmBrn mngmBrn;
}
