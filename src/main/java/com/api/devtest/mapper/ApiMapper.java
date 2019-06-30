package com.api.devtest.mapper;

import com.api.devtest.common.DataMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {

    //연도별 연간 합계 금액이 가장 많은 고객 조회
    List<DataMap> getAnnualTopSumAmtCust();

    //연도별 연간 거래가 없는 고객 조회
    List<DataMap> getAnnualNoTrnCust();

    //거래년도 조회
    List<DataMap> getTrnYear();

    //해당년도 관리점별 거래금액 조회
    List<DataMap> getMngmBrnTrnAmtYear(DataMap map);

    //관리점 거래금액 조회
    DataMap getMngmBrnTrnAmt(DataMap map);

    //관리점 이관
    int putBranchTransfer(DataMap map);

    //관리점 삭제
    int deleteBranch(DataMap map);
}
