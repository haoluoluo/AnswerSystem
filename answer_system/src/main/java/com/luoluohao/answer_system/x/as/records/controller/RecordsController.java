package com.luoluohao.answer_system.x.as.records.controller;

import com.luoluohao.answer_system.util.ViewJSON;
import com.luoluohao.answer_system.x.as.records.dto.entity.Records;
import com.luoluohao.answer_system.x.as.records.dto.entity.RecordsExt;
import com.luoluohao.answer_system.x.as.records.service.IRecordsDetailService;
import com.luoluohao.answer_system.x.as.records.service.IRecordsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
@RequestMapping("/records")
public class RecordsController {
    @Autowired
    private IRecordsService recordsService;

    @Autowired
    private IRecordsDetailService recordsDetailService;

    @ResponseBody
    @RequestMapping(value = "/getRecordInfo", method = RequestMethod.GET)
    public ViewJSON getRecordInfo(
            Integer userno,
            Integer examination

    ){

        List<Records> recordsList = null;
        if(userno == null && examination == null){
            recordsList = recordsService.selectAll();
        }else if(userno != null){
            recordsList = recordsService.selectByUserno(userno);
        }else if(examination != null){
            recordsList = recordsService.selectByExam(examination);
        }
        return new ViewJSON("200","查询成功",recordsList);
    }
    @ResponseBody
    @RequestMapping(value = "/records", method = RequestMethod.POST)
    public ViewJSON addRecords(
            @RequestBody RecordsExt records
            ){
        try{
            Records records1 = new Records();
            BeanUtils.copyProperties(records,records1);
            recordsService.insert(records1);
            recordsDetailService.insertList(records.getLists());
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return new ViewJSON("400", "服务器错误");
        }
        return new ViewJSON<>("200");
    }
    @ResponseBody
    @RequestMapping(value = "/records/{userno}", method = RequestMethod.GET)
    public ViewJSON ecordsByUser(
            @PathVariable("userno") Integer userno
    ){
        Records records = new Records();
        records.setUser(userno);
        List<Records> select = recordsService.select(records);
        return new ViewJSON<>("200","查询成功",select);
    }

    public Records addRecordsWTAns(Records records) {
        Records insert = recordsService.insert(records);
        return insert;
    }
}
