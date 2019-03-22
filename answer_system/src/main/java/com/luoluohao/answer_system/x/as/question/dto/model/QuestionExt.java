package com.luoluohao.answer_system.x.as.question.dto.model;

import com.luoluohao.answer_system.x.as.question.dto.entity.Items;
import com.luoluohao.answer_system.x.as.question.dto.entity.Question;

import java.util.List;

public class QuestionExt extends Question {

    private List<Items> items;

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

}
