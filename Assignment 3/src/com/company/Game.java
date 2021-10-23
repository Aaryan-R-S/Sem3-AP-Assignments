package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Player _player;
    private Dice _dice;
    private final int _noOfFloors;
    private ArrayList<Floor> _floors;
    private int _pointsScored;
    private boolean _gameOver;
    private static Scanner sc = new Scanner(System.in);

    Game(){
        // hardcore attributes to run the given testcases
        this._player = null;
        this._pointsScored = 0;
        this._gameOver = false;
        this._dice = new Dice(2);
        this._floors = new ArrayList<Floor>();
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
        System.out.print("Enter the player name and hit enter: ");
        String name = sc.nextLine();
        this._player = new Player(name, -1);
        System.out.println("Game setup is ready");
    }

    private void printPlayerPosition(){
        System.out.println("Player position Floor-"+this._player.get_position());
    }

    private void printFloorType(){
        Floor myFloor = _floors.get(this._player.get_position());
        if(myFloor.isEmpty()==1){
            System.out.println(this._player.get_name() + " has reached an Empty Floor");
        }
        else if(myFloor.isLadder()!=0){
            if(myFloor.isLadder()==1){
                System.out.println(this._player.get_name() + " has reached a Ladder Floor");
            }
            else{
                System.out.println(this._player.get_name() + " has reached an Elevator Floor");
            }
        }
        else{
            if(myFloor.isSnake()==1){
                System.out.println(this._player.get_name() + " has reached a Normal Snake Floor");
            }
            else{
                System.out.println(this._player.get_name() + " has reached a King Cobra Snake Floor");
            }
        }
    }

    private void printTotalPoints(){
        System.out.println("Total points "+this._pointsScored);
    }

    private void autoRun(){
        int newPos;
        while(this._floors.get(this._player.get_position()).isEmpty()!=1){
            newPos = this._floors.get(this._player.get_position()).get_goTo();
            this._player.set_position(newPos);
            this._pointsScored = this._pointsScored + this._floors.get(this._player.get_position()).get_points();
            printPlayerPosition();
            printFloorType();
            printTotalPoints();
            if(this._player.get_position()==this._noOfFloors-1){
                this._gameOver = true;
                break;
            }
        }
    }

    public void run(){
        System.out.println("--------------------------------------------");
        while(this._gameOver!=true){
            System.out.print("Hit enter to roll the dice");
            sc.nextLine();
            this._dice.roll();
            System.out.println(this._dice);
            if(this._player.get_position() == -1){
                if(this._dice.get_faceValue()==2){
                    System.out.println("Game cannot start until you get 1");
                }
                else{
                    this._player.set_position(0);
                    this._pointsScored = 1;
                    printPlayerPosition();
                    printFloorType();
                    printTotalPoints();
                }
            }
            else{
                if(this._dice.get_faceValue()+this._player.get_position() >= this._noOfFloors){
                    System.out.println("Player cannot move");
                }
                else{
                    this._player.set_position(this._player.get_position() + this._dice.get_faceValue());
                    this._pointsScored = this._pointsScored + this._floors.get(this._player.get_position()).get_points();
                    printPlayerPosition();
                    printFloorType();
                    printTotalPoints();
                    if (this._player.get_position() == this._noOfFloors-1) {
                        this._gameOver = true;
                    }
                    else if(this._floors.get(this._player.get_position()).isEmpty()!=1){
                        autoRun();
                    }
                }
            }
            System.out.println("--------------------------------------------");
        }
        System.out.println("Game over");
        System.out.println(this._player.get_name()+" accumulated "+this._pointsScored+" points");
        System.out.println("--------------------------------------------");
    }
}
