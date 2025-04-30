package com.test.pro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.okta.commons.lang.Collections;

@SpringBootApplication
public class CompleteProApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompleteProApplication.class, args);
		
		
		/*Thread t1 = new Thread(new Test());
		t1.start();
		
		Thread t2 = new Thread(new Test());
		t2.start();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i=111;i<150;i++) {
					System.out.println(i);
				}
				
			}
			
		}).start();*/
		
		
		ExecutorService ex = Executors.newFixedThreadPool(3);
		
		for(int x=0;x<6;x++) {
				ex.execute(new Test());
		}
		
	}
	
	

}

class Test implements Runnable{
	
	int count=1;
	
	@Override
	public void run() {
		System.out.println("Thread ID :"+Thread.currentThread().getId()+"Started");
		for(int i=0;i<40;i++) {
			System.out.println(i);
		}
		
		System.out.println("Thread ID :"+Thread.currentThread().getId()+"Ended");
	}
	
	
	
}
