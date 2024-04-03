package org.springdoc.demo.app3.service;

import org.drools.template.ObjectDataCompiler;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springdoc.demo.app3.config.DroolConfig;
import org.springdoc.demo.app3.model.Order;
import org.springdoc.demo.app3.model.Rule;
import org.springdoc.demo.app3.repository.DroolRulesRepo;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final KieContainer kieContainer;
    private final DroolRulesRepo rulesRepo;

    public OrderService(KieContainer kieContainer, DroolRulesRepo rulesRepo) {
        this.kieContainer = kieContainer;
        this.rulesRepo = rulesRepo;
    }

    public Order getDiscountForOrder(Order order) {
        KieSession session = kieContainer.newKieSession();
        session.insert(order);
        session.fireAllRules();
        session.dispose();
        return order;
    }

    public Order getDiscountForOrderV2(Order order) throws FileNotFoundException {
        List<Rule> ruleAttributes = new ArrayList<>();
        rulesRepo.findAll().forEach(ruleAttributes::add);

        ObjectDataCompiler compiler = new ObjectDataCompiler();
        String generatedDRL = compiler.compile(ruleAttributes, Thread.currentThread().getContextClassLoader().getResourceAsStream(DroolConfig.RULES_TEMPLATE_FILE));
        KieServices kieServices = KieServices.Factory.get();

        KieHelper kieHelper = new KieHelper();

        //multiple such resoures/rules can be added
        byte[] b1 = generatedDRL.getBytes();
        Resource resource1 = kieServices.getResources().newByteArrayResource(b1);
        kieHelper.addResource(resource1, ResourceType.DRL);

        KieBase kieBase = kieHelper.build();

        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(order);
        int numberOfRulesFired = kieSession.fireAllRules();
        kieSession.dispose();

        return order;
    }
}
