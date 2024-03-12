package com.pratishtha.dev.StackOverFlow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BountyDTO {

    private Long memberId;
    private int bountyReputation;
    private Long receiverId;
}
