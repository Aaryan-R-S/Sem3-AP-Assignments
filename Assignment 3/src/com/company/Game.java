package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> _players;
    private Dice _dice;
    private ArrayList<Integer> _points;
    private ArrayList<Boolean> _gamesOver;
    private final int _noOfFloors;
    private ArrayList<Floor> _floors;
    private static Scanner sc = new Scanner(System.in);

    Game(){
        this._players = new ArrayList<Player>();
        this._points = new ArrayList<Integer>();
        this._gamesOver = new ArrayList<Boolean>();
        this._dice = null;
        this._floors = new ArrayList<Floor>();
        // hardcore attributes to run the given testcases
        this._floors.add(new Empty_Floor(0));
        this._floors.add(new Empty_Floor(1));
        this._floors.add(new Elevator_Floor(2, 10));
        this._floors.add(new Empty_Floor(3));
        this._floors.add(new Empty_Floor(4));
        this._floors.add(new Snake_Floor(5, 1));
        this._floors.add(new Empty_Floor(6));
        this._floors.add(new Empty_Floor(7));
        this._floors.add(new Ladder_Floor(8, 12));
        this._floors.add(new Empty_Floor(9));
        this._floors.add(new Empty_Floor(10));
        this._floors.add(new King_Cobra_Floor(11, 3));
        this._floors.add(new Empty_Floor(12));
        this._floors.add(new Empty_Floor(13));
        this._noOfFloors = this._floors.size();
    }

    public void setup(){
        System.out.print("Enter number of players and hit enter: ");
        int np = sc.nextInt(); sc.nextLine();
        for (int i = 0; i < np; i++) {
            System.out.print("Enter "+(i+1)+"th player name and hit enter: "); String name = sc.nextLine();
            this._players.add(new Player(name, -1));
            this._gamesOver.add(false);
            this._points.add(0);
        }

        System.out.print("Enter the number of faces of dice: "); int faces = sc.nextInt(); sc.nextLine();
        if(faces<=1){
            System.out.println("Taking minimum no. of faces = 2 as no. of faces cannot be less than 2");
            faces = 2;
        }
        this._dice = new Dice(faces);

        System.out.println("Game setup is ready");
    }

    private void printPlayerPosition(int idx){
        System.out.println("Player position Floor-"+this._players.get(idx).get_position());
    }

    private void printFloorType(int idx){
        Floor myFloor = _floors.get(this._players.get(idx).get_position());
        if(myFloor.isEmpty()==1){
            System.out.println(this._players.get(idx).get_name() + " has reached an Empty Floor");
        }
        else if(myFloor.isLadder()!=0){
            if(myFloor.isLadder()==1){
                System.out.println(this._players.get(idx).get_name() + " has reached a Ladder Floor");
            }
            else{
                System.out.println(this._players.get(idx).get_name() + " has reached an Elevator Floor");
            }
        }
        else{
            if(myFloor.isSnake()==1){
                System.out.println(this._players.get(idx).get_name() + " has reached a Normal Snake Floor");
            }
            else{
                System.out.println(this._players.get(idx).get_name() + " has reached a King Cobra Snake Floor");
            }
        }
    }

    private void printTotalPoints(int idx){
        System.out.println("Total points "+this._points.get(idx));
    }

    private void autoRun(int idx){
        int newPos;
        while(this._floors.get(this._players.get(idx).get_position()).isEmpty()!=1){
            newPos = this._floors.get(this._players.get(idx).get_position()).get_goTo();
            this._players.get(idx).set_position(newPos);
            this._points.set(idx, this._points.get(idx) + this._floors.get(this._players.get(idx).get_position()).get_points());
            printPlayerPosition(idx);
            printFloorType(idx);
            printTotalPoints(idx);
            if(this._players.get(idx).get_position()==this._noOfFloors-1){
                this._gamesOver.set(idx, true);
                break;
            }
        }
    }

    private void printGameStats(){
        System.out.println("--------------------------------------------");
        System.out.println("Game Stats");
        System.out.println("{Name}    {Points Scored}");
        for (int i = 0; i < this._players.size(); i++) {
            System.out.println(this._players.get(i).get_name() +"      "+this._points.get(i));
        }
        System.out.println("--------------------------------------------");

    }

    public void run() {
        int verdict = 1;
        while (verdict!=0) {
            verdict = 0;
            for (int i = 0; i < this._players.size(); i++) {
                if (this._gamesOver.get(i) != true) {
                    verdict += 1;
                    System.out.println("----------------[" + this._players.get(i).get_name() + "]------------------");

                        System.out.print("Hit enter to roll the dice");
                        sc.nextLine();
                        this._dice.roll();
                        System.out.println(this._dice);
                        if (this._players.get(i).get_position() == -1) {
                            if (this._dice.get_faceValue() == 1) {
                                this._players.get(i).set_position(0);
                                this._points.set(i, 1);
                                printPlayerPosition(i);
                                printFloorType(i);
                                printTotalPoints(i);
                            } else {
                                System.out.println("Game cannot start until you get 1");
                            }
                        }
                        else {
                            if (this._dice.get_faceValue() + this._players.get(i).get_position() >= this._noOfFloors) {
                                System.out.println("Player cannot move");
                            } else {
                                this._players.get(i).set_position(this._players.get(i).get_position() + this._dice.get_faceValue());
                                this._points.set(i, this._points.get(i) + this._floors.get(this._players.get(i).get_position()).get_points());
                                printPlayerPosition(i);
                                printFloorType(i);
                                printTotalPoints(i);
                                if (this._players.get(i).get_position() == this._noOfFloors - 1) {
                                    this._gamesOver.set(i, true);
                                } else if (this._floors.get(this._players.get(i).get_position()).isEmpty() != 1) {
                                    autoRun(i);
                                }
                            }
                        }
                    if(this._gamesOver.get(i)==true) {
                        System.out.println("--------------------------------------------");
                        System.out.println("Game over");
                        System.out.println(this._players.get(i).get_name() + " accumulated " + this._points.get(i) + " points");
                    }
                }
            }
        }
        printGameStats();
    }
}
