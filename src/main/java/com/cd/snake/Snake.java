package com.cd.snake;

import javafx.scene.paint.Color;

import java.util.*;

/**
 * Created by Steve on 07/12/2016.
 */
public class Snake extends BlockSprite {

    private int length = 3;
    private BlockCoordinates headCoordinates = new BlockCoordinates(3,0);
    final static Color headColor = Color.AQUAMARINE;
    final static Color bodyColor = Color.DARKGREY;
    List<BlockCoordinates> bodyCoordinates = new LinkedList<>();


    public Snake(){
        init();
    }

    private void init(){
        for (int ii = 1; ii < length; ii++){
            bodyCoordinates.add(new BlockCoordinates(headCoordinates.getX() - ii, headCoordinates.getY()));
        }
    }

    public int getLength() {
        return 3;
    }

    public SpriteDirection getCurrentDirection() {
        return SpriteDirection.RIGHT;
    }

    public void eatFood() {
//This is a comment. Or is it??!!
    }

    public void move(SpriteDirection spriteDirection) {
        bodyCoordinates.add(0, headCoordinates);
        headCoordinates = new BlockCoordinates(headCoordinates.getX()+1, headCoordinates.getY());

    }

    @Override
    public Map<BlockCoordinates, Color> getBlocks() {
        Map<BlockCoordinates, Color> blocks = new HashMap<>();
        blocks.put(headCoordinates, headColor);
        while (bodyCoordinates.size() > length - 1){
            bodyCoordinates.remove(bodyCoordinates.size()-1);
        }

        for (BlockCoordinates c : bodyCoordinates){
            blocks.put(c, bodyColor);
        }
        return blocks;
    }
}
