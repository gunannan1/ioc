package com.publicgroup;

import static org.junit.Assert.assertTrue;

import com.publicgroup.annotation.component;
import com.publicgroup.context.AutowireApplicationContext;
import com.publicgroup.entity.Father;
import com.publicgroup.entity.Son;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws Exception {

        AutowireApplicationContext default1 = new AutowireApplicationContext(
                AppTest.class.getResource("/test.xml").getFile()
        );

        Father father= (Father) default1.getBean("father");
        Son son=(Son)default1.getBean("son");
        father.say();
        son.say();



    }
}
