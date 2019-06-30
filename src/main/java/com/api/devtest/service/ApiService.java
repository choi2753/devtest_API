package com.api.devtest.service;

import com.api.devtest.common.ApiException;
import com.api.devtest.common.DataMap;
import com.api.devtest.entity.CusAcnt;
import com.api.devtest.mapper.ApiMapper;
import com.api.devtest.repository.CusAcntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ApiService {

    @Autowired
    CusAcntRepository cusAcntRepository;

    @Autowired
    ApiMapper apiMapper;

    /*
        연도별 연간 합계 금액이 가장 많은 고객 조회
    */
    public List<DataMap> getAnnualTopSumAmtCust(){

        return apiMapper.getAnnualTopSumAmtCust();
    }

    /*
        연도별 연간 거래가 없는 고객 조회
     */
    public List<DataMap> getAnnualNoTrnCust(){

        return apiMapper.getAnnualNoTrnCust();
    }

    /*
        연도별 관리점별 거래금액 조회
     */
    public List<DataMap> getAnnualMngmBrnTrnAmt(){
        //거래년도 조회
        List<DataMap> trnYearList = apiMapper.getTrnYear();

        for(DataMap map : trnYearList){
            //거래년도 별로 관리점별 거래금액 조회
            List<DataMap> mngmBrnList = apiMapper.getMngmBrnTrnAmtYear(map);

            //결과맵에 담기
            map.put("dataList", mngmBrnList);
        }

        return trnYearList;
    }

    /*
        관리점 거래금액 조회
     */
    public DataMap getMngmBrnTrnAmt(DataMap paramMap) throws ApiException {

        DataMap map = apiMapper.getMngmBrnTrnAmt(paramMap);

        if(map == null) {
            //조회결과 없을 경우 code와 message 전달
            throw new ApiException("404", "brCode not found Error");
        }
        return apiMapper.getMngmBrnTrnAmt(paramMap);
    }

    /*
        관리점 이관
     */
    public void putBranchTransfer(DataMap paramMap){

        int cnt = apiMapper.putBranchTransfer(paramMap);
    }

    /*
        관리점 삭제
     */
    public void deleteBranch(DataMap paramMap){

        int cnt = apiMapper.deleteBranch(paramMap);
    }

    /*
        Jpa 단건 조회 테스트
     */
    public Optional<CusAcnt> get(String id){
        return cusAcntRepository.findById(id);
    }

    /*
        Jpa 다건 조회 테스트
     */
    public List<CusAcnt> get(){
        return cusAcntRepository.findAll();
    }
}
