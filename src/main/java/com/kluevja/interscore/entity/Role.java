package com.kluevja.interscore.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlEnum
public enum Role implements GrantedAuthority {
    ADMIN,
    USER,
    INTERVIEWER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
