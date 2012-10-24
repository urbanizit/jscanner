package org.urbanizit.jscanner.back.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@ContextConfiguration(locations = {"classpath:spring/jscannerContext.xml"})
public abstract class BaseSpringTestCase extends AbstractJUnit4SpringContextTests {
    
    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionStatus transaction;

    protected void beginTransaction() {
        TransactionDefinition params = new DefaultTransactionDefinition();
        transaction = transactionManager.getTransaction(params);
    }

    protected void commitTransaction() {
        transactionManager.commit(transaction);
    }
}
