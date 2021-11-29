package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player _player;
    private Carpet _carpet;
    private GenericCalculator _intCal;
    private GenericCalculator _strCal;
    private int _maxLevel;
    private int _chances;

    Game(){
        this._maxLevel = 20;
        this._chances = 5;
        this._player = new Player(this._chances, this._maxLevel);
        this._intCal = new IntegerCalculator();
        this._strCal = new StringCalculator();
        this._carpet = new Carpet();
        this.set_carpet();
    }

    private void set_carpet(){
        this._carpet.addTile("Teddy Bear", 1);
        this._carpet.addTile("Donald Duck", 2);
        this._carpet.addTile("Scooby Dog", 3);
        this._carpet.addTile("Mickey Mouse", 4);
        this._carpet.addTile("Champ vamp", 5);
        this._carpet.addTile("Iron man", 6);
        this._carpet.addTile("Captain America", 7);
        this._carpet.addTile("Thor", 8);
        this._carpet.addTile("Barbie", 9);
        this._carpet.addTile("Tom", 10);
        this._carpet.addTile("Jerry", 11);
        this._carpet.addTile("Dalton", 12);
        this._carpet.addTile("Power Rangers", 13);
        this._carpet.addTile("Ben Ten", 14);
        this._carpet.addTile("Goofy", 15);
        this._carpet.addTile("Agent X", 16);
        this._carpet.addTile("Coco", 17);
        this._carpet.addTile("Square pants", 18);
        this._carpet.addTile("Sponge bob", 19);
        this._carpet.addTile("Minions", 20);
    }

    private int get_rd_int(){
        Random rd = new Random();
        return rd.nextInt();
    }

    private String get_rd_string(int sz){
        String possible_chars = "abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String rd_str = new String();

        for (int i = 0; i < sz; i++) {
            int idx = (int)(possible_chars.length() * Math.random());
            rd_str += possible_chars.charAt(idx);
        }

        return rd_str;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);

        ArrayList<String> numInWords = new ArrayList<String>();
        numInWords.add("first");numInWords.add("second");numInWords.add("third");numInWords.add("fourth");numInWords.add("fifth");

        int chances = 0;
        while (this._player.get_chances()-chances>0){
            System.out.print("Hit enter for your "+numInWords.get(chances)+" hop");sc.nextLine();

            int tileNum = this._player.hop_n_land();

            if(tileNum>this._maxLevel){
                System.out.println("You are too energetic and zoomed past all the tiles. Muddy Puddle Splash!");
            }
            else if(tileNum%2==0){
                System.out.println("You landed on tile "+tileNum);
                Toy wonToy = null;
                while(true) {
                    try {
                        wonToy = this._carpet.clone_toy(tileNum - 1);
                        this._player.add_toy(wonToy);
                        System.out.println("You won a " + wonToy.get_name() + " soft toy");
                        break;
                    }
                    catch (NullPointerException e){
                        System.out.println("Encountered some error! Trying again...");
                    }
                    finally {
                        wonToy = null;
                    }
                }
            }
            else{
                System.out.println("You landed on tile "+tileNum);
                while(true) {
                    System.out.println("Question answer round. Integer or strings?");
                    String choice = sc.nextLine();

                    if (choice.equalsIgnoreCase("integer")) {
                        int op1 = this.get_rd_int();
                        int op2 = this.get_rd_int();
                        System.out.println("Calculate the result of "+op1+" divided by "+op2+" (Enter 'nd' if result is not defined)");

                        String result  = sc.nextLine();
                        try{
                            int res = 0;
                            int ans = (Integer) this._intCal.operate(op1, op2);
                            try{
                                res = Integer.parseInt(result);
                            }
                            catch (NumberFormatException e){
                                System.out.println("Incorrect answer");
                                System.out.println("You did not win any soft toy");
                                break;
                            }
                            if(res==ans){
                                System.out.println("Correct answer");
                                Toy wonToy = null;
                                while(true) {
                                    try {
                                        wonToy = this._carpet.clone_toy(tileNum - 1);
                                        this._player.add_toy(wonToy);
                                        System.out.println("You won a " + wonToy.get_name() + " soft toy");
                                        break;
                                    }
                                    catch (NullPointerException e){
                                        System.out.println("Encountered some error! Trying again...");
                                    }
                                    finally {
                                        wonToy = null;
                                    }
                                }
                            }
                            else{
                                System.out.println("Incorrect answer");
                                System.out.println("You did not win any soft toy");
                            }
                        }
                        catch (ArithmeticException e){
                            if(result.equals("nd")){
                                System.out.println("Correct answer");
                                Toy wonToy = null;
                                while(true) {
                                    try {
                                        wonToy = this._carpet.clone_toy(tileNum - 1);
                                        this._player.add_toy(wonToy);
                                        System.out.println("You won a " + wonToy.get_name() + " soft toy");
                                        break;
                                    }
                                    catch (NullPointerException ee){
                                        System.out.println("Encountered some error! Trying again...");
                                    }
                                    finally {
                                        wonToy = null;
                                    }
                                }
                            }
                            else {
                                System.out.println("Incorrect answer");
                                System.out.println("You did not win any soft toy");
                            }
                        }
                        break;

                    }

                    else if (choice.equalsIgnoreCase("strings")) {
                        String op1 = get_rd_string(4);
                        String op2 = get_rd_string(4);
                        System.out.println("Calculate the concatenation of strings "+op1+" and "+op2);
                        String result = sc.nextLine();
                        String ans = (String)this._strCal.operate(op1, op2);
                        if(result.equals(ans)){
                            System.out.println("Correct answer");
                            Toy wonToy = null;
                            while(true) {
                                try {
                                    wonToy = this._carpet.clone_toy(tileNum - 1);
                                    this._player.add_toy(wonToy);
                                    System.out.println("You won a " + wonToy.get_name() + " soft toy");
                                    break;
                                }
                                catch (NullPointerException e){
                                    System.out.println("Encountered some error! Trying again...");
                                }
                                finally {
                                    wonToy = null;
                                }
                            }
                        }
                        else{
                            System.out.println("Incorrect answer");
                            System.out.println("You did not win any soft toy");
                        }
                        break;
                    }

                    else {
                        System.out.println("Invalid choice");
                    }
                }
            }
            chances++;
        }

        System.out.println("Game over!");
        System.out.println("Soft toys won by you are:");
        this._player.printBucket();
    }

}
