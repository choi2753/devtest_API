package com.api.devtest.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.One;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "TB_DEVTEST_MNGM_BRN")
public class MngmBrn {

    @Id
    private String mngmBrcd;    //관리점코드

    private String mngmBrm;     //관리점명
}
