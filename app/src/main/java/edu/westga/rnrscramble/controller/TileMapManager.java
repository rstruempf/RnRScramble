package edu.westga.rnrscramble.controller;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.renderscript.Allocation;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import java.util.HashMap;
import java.util.ResourceBundle;

import edu.westga.rnrscramble.R;

/**
 * Created by RyanT on 3/30/2016.
 */
public class TileMapManager {

    private HashMap<Character, Integer> tileMap;

    public TileMapManager() {
        this.tileMap = new HashMap<>();
        this.tileMap.put('A', R.mipmap.a);
        this.tileMap.put('B', R.mipmap.b);
        this.tileMap.put('C', R.mipmap.c);
        this.tileMap.put('D', R.mipmap.d);
        this.tileMap.put('E', R.mipmap.e);
        this.tileMap.put('F', R.mipmap.f);
        this.tileMap.put('G', R.mipmap.g);
        this.tileMap.put('H', R.mipmap.h);
        this.tileMap.put('I', R.mipmap.i);
        this.tileMap.put('J', R.mipmap.j);
        this.tileMap.put('K', R.mipmap.k);
        this.tileMap.put('L', R.mipmap.l);
        this.tileMap.put('M', R.mipmap.m);
        this.tileMap.put('N', R.mipmap.n);
        this.tileMap.put('O', R.mipmap.o);
        this.tileMap.put('P', R.mipmap.p);
        this.tileMap.put('Q', R.mipmap.q);
        this.tileMap.put('R', R.mipmap.r);
        this.tileMap.put('S', R.mipmap.s);
        this.tileMap.put('T', R.mipmap.t);
        this.tileMap.put('U', R.mipmap.u);
        this.tileMap.put('V', R.mipmap.v);
        this.tileMap.put('W', R.mipmap.w);
        this.tileMap.put('X', R.mipmap.x);
        this.tileMap.put('Y', R.mipmap.y);
        this.tileMap.put('Z', R.mipmap.z);
    }

    public int getTile(Character character) {
        return this.tileMap.get(character);
    }

    public boolean contains(Character key) {
        return this.tileMap.containsKey(key);
    }
}
