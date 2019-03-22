package com.luoluohao.answer_system.x.as.records.dto.entity;

import java.util.List;

public class RecordsExt extends Records {
    public List<RecordsDetail> getLists() {
        return lists;
    }

    public void setLists(List<RecordsDetail> lists) {
        this.lists = lists;
    }

    private List<RecordsDetail> lists;
}
