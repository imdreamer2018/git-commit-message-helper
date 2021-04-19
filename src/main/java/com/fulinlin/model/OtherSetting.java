package com.fulinlin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtherSetting extends DomainObject{
    public String key;
    public String value;

    @Override
    public String toString() {
        return String.format("%s - %s", this.getKey(), this.getValue());
    }
}
