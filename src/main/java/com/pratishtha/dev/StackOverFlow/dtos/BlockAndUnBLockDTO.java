package com.pratishtha.dev.StackOverFlow.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockAndUnBLockDTO {

    private Long memberIdToBeBlocked;
    private Long memberIdWhoWillBlock;
}
