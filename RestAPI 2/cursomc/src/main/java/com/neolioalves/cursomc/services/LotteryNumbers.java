package com.neolioalves.cursomc.services;

public class LotteryNumbers {
	
	public Integer[] execute() {
		
		// declare and create an Array of 6 integers
		Integer[] numbers = new Integer[6];
		
		int flag = 0;
		
		// initialize 
		for(int i = 0; i < 6; i++) {
			flag = 0;
			
			while(flag == 0) {
				numbers[i] = 1 + (int)(Math.random()*60);
				
				flag = 1;
				
				for(int j = 0; j < i; j++) {
					if(numbers[i] == numbers[j]) {
						flag = 0;
					}
				}
			}
		}
		
		return numbers;
	}
}
