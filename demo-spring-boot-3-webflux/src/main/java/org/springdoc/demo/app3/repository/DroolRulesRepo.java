package org.springdoc.demo.app3.repository;

import org.springdoc.demo.app3.model.Rule;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DroolRulesRepo {

    Map<Integer, Rule> ruleMap = new HashMap<>();

    public List<Rule> findAll() {
        return ruleMap.values().stream().toList();
    }

    public void save(Rule rule) {
        ruleMap.put(rule.getId(), rule);
    }
}
