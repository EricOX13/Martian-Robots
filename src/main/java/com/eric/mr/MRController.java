package com.eric.mr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MRController {

   
    @GetMapping
    public ResponseEntity findAllUsers() {
        // Implement
    	return ResponseEntity.ok().build();
    }
    
    @GetMapping("/check/{maxX}/{maxY}/{initalX}/{initalY}/{direction}/{instruction}")
    public ResponseEntity check(@PathVariable(value = "maxX") int maxX,
    		@PathVariable(value = "maxY") int maxY,
    		@PathVariable(value = "initalX") int x,
    		@PathVariable(value = "initalY") int y,
    		@PathVariable(value = "direction") String direction,
    		@PathVariable(value = "instruction") String instruction) {

		ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
		Result result = checkSteps(maxX,maxY,x,y,direction,instruction);
		result.steps = null;
    	return response.ok(result);
    }
    
    @GetMapping("/detailcheck/{maxX}/{maxY}/{initalX}/{initalY}/{direction}/{instruction}")
    public ResponseEntity checkWithSteps(@PathVariable(value = "maxX") int maxX,
    		@PathVariable(value = "maxY") int maxY,
    		@PathVariable(value = "initalX") int x,
    		@PathVariable(value = "initalY") int y,
    		@PathVariable(value = "direction") String direction,
    		@PathVariable(value = "instruction") String instruction) {
        
		ResponseEntity response = new ResponseEntity(HttpStatus.ACCEPTED);
		Result result = checkSteps(maxX,maxY,x,y,direction,instruction);
    	return response.ok(result);
    }
    
    public Result checkSteps(int maxX,int maxY,int x,int y,String direction,String instruction) {
        // Implement
    	System.out.println("maxX : " + maxX);
    	System.out.println("maxY : " + maxY);
    	System.out.println("initalX : " + x);
    	System.out.println("initalY : " + y);
    	System.out.println("direction : " + direction);
    	
    	boolean isLost = false;
    	List<Step> detailStepsList = new ArrayList<Step>();
    	/*
		 * Direction:
		 * N: 0
		 * E: 1
		 * S: 2
		 * W: 3
		 */
		int directionNum = 0;
		
		for (char ins:instruction.toCharArray()) {
			//Direction change 'R', 'L'
			System.out.println(ins);
			if (ins=='R') {
				directionNum++;
				if (directionNum>3) {
					directionNum = 0;
				}
			} else if (ins == 'L') {
				directionNum--; 
				if (directionNum==-1) {
					directionNum = 3;
				}
			} else if (ins == 'F') {
				int tmpX = x;
				int tmpY = y;
				switch (directionNum) {
					case 0: tmpY++;
							break;
		            case 1: tmpX++;
		            		break;
		            case 2: tmpY--;
		            		break;
		            case 3: tmpX--;
		            		break;
				}
				// check out of grid
				if (tmpX<0 || tmpY<0 || tmpY>maxY || tmpX>maxX) {
					isLost = true;
					System.out.println("break");
					//break;
				}
				x = tmpX;
				y = tmpY;
			}
			System.out.println(x + " " + y + " " + displayDirection(directionNum));
			
			detailStepsList.add(new Step(String.valueOf(ins),x + " " + y + " " + displayDirection(directionNum)));
		}
		
		Result result;
		if (isLost) {
			result = new Result(x + " " + y + " " + displayDirection(directionNum) + " LOST", detailStepsList);
		} else {
			result = new Result(x + " " + y + " " + displayDirection(directionNum), detailStepsList);
		}
		
    	return result;
    }
    
	public static String displayDirection(int num) {
		switch (num) {
			case 0: return "N";
	        case 1: return "E";
	        case 2: return "S";
	        case 3: return "W";
		}
		return "";
	}
	
	public static int convertDirection(String direction) {
		switch (direction) {
			case "N": return 0;
	        case "E": return 1;
	        case "S": return 2;
	        case "W": return 3;
		}
		return 0;
	}
	
	class Step {
		String instruction;
		String position;
		public Step (String instruction, String position) {
			this.instruction = instruction;
			this.position = position;
		}
		public String getInstruction() {
			return instruction;
		}
		public String getPosition() {
			return position;
		}
	}
	
	class Result {
		String result;
		List<Step> steps;
		public Result (String result, List<Step> steps) {
			this.result = result;
			this.steps = steps;
		}
		public String getResult() {
			return result;
		}
		public List<Step> getSteps() {
			return steps;
		}
	}

}