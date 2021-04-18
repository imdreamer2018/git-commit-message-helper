package com.fulinlin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @program: git-commit-message-helper
 * @author: fulin
 * @create: 2019-12-06 21:11
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TypeAlias extends DomainObject {
    public String title;
    public String description;

    @Override
    public String toString() {
        return String.format("%s - %s", this.getTitle(), this.getDescription());
    }
}
