package org.springdoc.demo.app3;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.demo.app3.model.Server;

public class DroolsTest {
    static final Logger LOG = LoggerFactory.getLogger(DroolsTest.class);
    private static final String RULES_DRL = "rules.drl";

    public static void main(String[] args) {
        DroolsTest droolsTest = new DroolsTest();
        droolsTest.test();
    }

    public void test() {
        KieServices kieServices = KieServices.Factory.get();

        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_DRL));

        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        KieModule kieModule = kieBuilder.getKieModule();

        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        KieSession kieSession = kieContainer.newKieSession();
        Server s1 = new Server("rhel7", 2, 1024, 2048);
        kieSession.insert(s1);
        kieSession.fireAllRules();
        System.out.println(s1.isValid());

        Server s2 = new Server("rhel8", 2, 2048, 4096);
        kieSession.insert(s2);
        kieSession.fireAllRules();
        System.out.println(s2.isValid());
        kieSession.dispose();

//        KieContainer kContainer = kieServices.getKieClasspathContainer();
//        LOG.info("Creating kieBase");
//        KieBase kieBase = kContainer.getKieBase();
//        LOG.info("There should be rules: ");
//        for (KiePackage kp : kieBase.getKiePackages()) {
//            for (Rule rule : kp.getRules()) {
//                LOG.info("kp " + kp + " rule " + rule.getName());
//            }
//        }
//        LOG.info("Creating kieSession");
//        KieSession session = kieBase.newKieSession();
//        LOG.info("Now running data");
//        Server s1 = new Server("rhel7", 2, 1024, 2048);
//        session.insert(s1);
//        session.fireAllRules();
//        System.out.println(s1.isValid());
//        Server s2 = new Server("rhel8", 2, 2048, 4096);
//        session.insert(s2);
//        session.fireAllRules();
//        System.out.println(s2.isValid());
    }
}
