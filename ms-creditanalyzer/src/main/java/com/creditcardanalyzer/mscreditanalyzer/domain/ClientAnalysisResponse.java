package com.creditcardanalyzer.mscreditanalyzer.domain;

import com.creditcardanalyzer.mscreditanalyzer.domain.model.ApprovedCard;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientAnalysisResponse {

     private List<ApprovedCard> cards;

}
