package com.cydeo.Day11;

import org.junit.jupiter.api.*;

public class TestLifeCycleAnnotations {


    @BeforeAll
    public static void init(){
        System.out.println("before all is running");
    }

    @BeforeEach
    public void initEach(){
        System.out.println("before each is running");
    }

    @AfterEach
    public void closeEach(){
        System.out.println("after each is running");
    }


    @Test
    public void test1(){

        System.out.println("test 1 is running");

    }

    @Disabled
    @Test
    public void test2(){
        System.out.println("test 2 is running");
    }

    @AfterAll
    public static void close(){
        System.out.println("after all is running");
    }


}
