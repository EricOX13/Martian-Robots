package com.eric.mr;

public class MartianRobots {

	public static void main(String args[]) {
		int maxX,maxY = 0;
		int x,y = 0;
		
		/*
		 * Direction:
		 * N: 0
		 * E: 1
		 * S: 2
		 * W: 3
		 */
		int direction = 0;
		maxX = 5;
		maxY = 3;
		
		// initial position and direction
		String input = "0 3 W";
		String[] parts = input.split(" ");
		x = Integer.parseInt(parts[0]);
		y = Integer.parseInt(parts[1]);
		direction = convertDirection(parts[2]);
		
		// instruction
		String instructionList = "LLFFFLFLFL";
		boolean isLost = false;
		
		for (char ins:instructionList.toCharArray()) {
			//Direction change 'R', 'L'
			System.out.println(ins);
			if (ins=='R') {
				direction++;
				if (direction>3) {
					direction = 0;
				}
			} else if (ins == 'L') {
				direction--; 
				if (direction==-1) {
					direction = 3;
				}
			} else if (ins == 'F') {
				int tmpX = x;
				int tmpY = y;
				switch (direction) {
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
			System.out.println(x + " " + y + " " + displayDirection(direction));
		}
		if (isLost) {
			System.out.println(x + " " + y + " " + displayDirection(direction) + " LOST");
		} else {
			System.out.println(x + " " + y + " " + displayDirection(direction));
		}
		
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
}
