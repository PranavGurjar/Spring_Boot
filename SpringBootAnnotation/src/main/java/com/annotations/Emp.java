package com.annotations;

import org.springframework.stereotype.Component;

//5
@Component
public class Emp {
    public void whatsYourName(){
        System.out.println("I am Employee");
    }
}
