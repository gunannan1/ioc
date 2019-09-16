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

        Class<?> father=Class.forName("com.publicgroup.entity.Father");
        Field[] fields=father.getFields();
        component com= father.getAnnotation(component.class);
        if(com==null){
            System.out.println("component is null");
        }


     
        AutowireApplicationContext default1 = new AutowireApplicationContext(
                AppTest.class.getResource("/test.xml").getFile()
        );

        Father father2= (Father) default1.getBean("father");
        Father father3= (Father) default1.getBean("father");

        Son son=(Son)default1.getBean("son");
//        System.out.println("father's name is "+father.getName());
        Assert.assertEquals(father2.getName(),"大王");
        Assert.assertEquals(son.getName(),"小王");
        Assert.assertEquals(father2,father3);

        Assert.assertEquals(son,father2.getSon());

    }
}
