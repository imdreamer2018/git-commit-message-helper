package com.fulinlin.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: git-commit-message-helper
 * @author: fulin
 * @create: 2019-12-05 21:22
 **/
@Getter
@Setter
public class DataSettings {
    private String template;
    private List<TypeAlias> typeAliases;
    private List<ConvertType> convertTypes;

}
