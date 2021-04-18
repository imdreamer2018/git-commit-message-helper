package com.fulinlin.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: git-commit-message-helper
 * @author: fulin
 * @create: 2019-12-08 11:36
 **/
@Getter
@Setter
public class CommitTemplate {

    private String authorName;
    private String cardNumber = "N/A";
    private String type;
    private String scope;
    private String subject;
    private String body;
    private String changes;
    private String closes;
}
