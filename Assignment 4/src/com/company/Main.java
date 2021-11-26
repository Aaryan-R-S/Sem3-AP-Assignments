package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	    System.out.print("Hit enter to initialize the game");
        sc.nextLine();
        Game myGame = new Game();
        System.out.println("Game is ready");
        myGame.run();
    }
}
