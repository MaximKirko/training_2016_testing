package com.github.maximkirko.testing.services;

import java.lang.reflect.Field;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.maximkirko.testing.datamodel.models.Quiz;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:service-context.xml")
public class QuizServiceTest {

    @Inject
    private QuizService quizService;

    @Test
    public void insertTest() throws IllegalArgumentException, IllegalAccessException {
    	
    	Quiz quiz = quizService.get(2l);
    	
//    	for (Field field : quiz.getClass().getSuperclass().getDeclaredFields()) {
//    		field.setAccessible(true);
//    	    System.out.format("Type: %s%n", field.getType().getSimpleName());
//    	    System.out.format("Name: %s%n", field.getName());
//    	    System.out.println(field.get(quiz));
//    	    
//    	}   
    	
    	//System.out.println(Quiz.class.getAnnotation(DBTable.class).name());
    	
    	//quizService.save(quiz);
    	
//    	List quizzes = quizService.getAll();
//    	for (Object object : quizzes) {
//			System.out.println(object.toString());
//		}
    	
    }

}
