package com.fulinlin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConvertType extends DomainObject{
    public String title;
    public String description;

    @Override
    public String toString() {
        return String.format("%s - %s", this.getTitle(), this.getDescription());
    }
}
