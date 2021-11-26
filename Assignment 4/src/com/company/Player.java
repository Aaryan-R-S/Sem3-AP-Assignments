package com.company;

public class Player {
    private int _chances;
    private Bucket _bucket;
    private int min_hop;
    private int max_hop;

    Player(int chances, int maxLevel){
        this._chances = chances;
        this._bucket = new Bucket();
        this.min_hop = 1;
        this.max_hop = maxLevel+1;
    }

    public int hop_n_land(){
        return this.min_hop + (int)(Math.random() * ((this.max_hop - this.min_hop) + 1));
    }

    public void printBucket(){
        System.out.println(this._bucket);
    }

    public int get_chances(){
        return this._chances;
    }

    public void add_toy(Toy t){
        this._bucket.addToy(t);
    }
}
