package com.api.devtest.controller;

import com.api.devtest.common.ApiException;
import com.api.devtest.common.DataMap;
import com.api.devtest.entity.CusAcnt;
import com.api.devtest.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ApiController {

    @Autowired
    ApiService apiService;

    /*
        연도별 연간 합계 금액이 가장 많은 고객 조회
        @param :
     */
    @RequestMapping(value="/customer/annual/top", method = RequestMethod.GET)
    public List<DataMap> getAnnualTopSumAmtCust() {
        return apiService.getAnnualTopSumAmtCust();
    }

    /*
        연도별 연간 거래가 없는 고객 조회
        @param :
     */
    @RequestMapping(value="/customer/annual/missing", method = RequestMethod.GET)
    public List<DataMap> getAnnualNoTrnCust() {
        return apiService.getAnnualNoTrnCust();
    }

    /*
        연도별 관리점별 거래금액 조회
        @param :
     */
    @RequestMapping(value="/trnamt/branch/annual", method = RequestMethod.GET)
    public List<DataMap> getAnnualMngmBrnTrnAmt() {
        return apiService.getAnnualMngmBrnTrnAmt();
    }

    /*
        관리점 거래금액 조회
        @param : @param : { "brName":"" }
     */
    @RequestMapping(value="/trnamt/branch", method = RequestMethod.GET)
    public DataMap getMngmBrnTrnAmt(@RequestBody DataMap paramMap) throws ApiException {
        return apiService.getMngmBrnTrnAmt(paramMap);
    }

    /*
        관리점 이관
        @param : { "brCode":"", "afterBrCode":"" }
     */
    @RequestMapping(value="/transfer/branch", method = RequestMethod.PUT)
    public void putBranchTransfer(@RequestBody DataMap paramMap) {
        apiService.putBranchTransfer(paramMap);
    }

    /*
        관리점 삭제
        @param : { "brCode":"" }
     */
    @RequestMapping(value="/transfer/branch", method = RequestMethod.DELETE)
    public void deleteBranch(@RequestBody DataMap paramMap) {
        apiService.deleteBranch(paramMap);
    }

    /*
        Jpa 단건 조회 테스트
     */
    @RequestMapping(value="/apiTest/{id}", method = RequestMethod.GET)
    public Optional<CusAcnt> get(@PathVariable String id) {

            return apiService.get(id);
    }

    /*
        Jpa 다건 조회 테스트
     */
    @RequestMapping(value="/apiTest", method = RequestMethod.GET)
    public List<CusAcnt> get() {

            return apiService.get();
    }
}