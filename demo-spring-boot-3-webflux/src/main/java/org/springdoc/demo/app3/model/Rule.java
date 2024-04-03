package org.springdoc.demo.app3.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Rule {

    private Integer id;
    private String ifcondition;
    private String thencondition;
    private int version;
}
