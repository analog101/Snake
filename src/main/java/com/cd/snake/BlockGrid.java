package com.cd.snake;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class BlockGrid {

    private int blocksX;
    private int blocksY;
    private double blockSize;
    private final Pane pane = new Pane();

    public BlockGrid(int blocksX, int blocksY, double blockSize) {
        this.blocksX = blocksX;
        this.blocksY = blocksY;
        this.blockSize = blockSize;
        pane.setPrefSize(blocksX*blockSize, blocksY*blockSize);
    }

    private Group getGroup(BlockSprite blockSprite){
        if (blockSprite == null) return null;
        Map<BlockCoordinates, Color> blockMap = blockSprite.getBlocks();
        if (blockMap == null) return null;
        Group group = new Group();
        for (BlockCoordinates c : blockMap.keySet()){
            BlockCoordinates wrappedCoordinates = getWrappedCoordinates(c);
            Rectangle r = new Rectangle(blockSize, blockSize, blockMap.get(c));
            r.setTranslateX(getTranslateX(wrappedCoordinates));
            r.setTranslateY(getTranslateY(wrappedCoordinates));
            group.getChildren().add(r);
        }
        return group;
    }


    public void setSprites(BlockSprite... sprites){
        this.pane.getChildren().clear();
        for (BlockSprite sprite : sprites){
            this.pane.getChildren().add(getGroup(sprite));
        }
    }

    public Pane getPane() {
        return pane;
    }

    public int getBlocksX() {
        return blocksX;
    }

    public int getBlocksY() {
        return blocksY;
    }

    public double getBlockSize() {
        return blockSize;
    }

    public BlockCoordinates getWrappedCoordinates(BlockCoordinates unwrappedCoordinates){
        return new BlockCoordinates(
                unwrappedCoordinates.getX() >= 0
                        ? unwrappedCoordinates.getX() % blocksX : blocksX + (unwrappedCoordinates.getX() % blocksX),
                unwrappedCoordinates.getY() >= 0
                        ? unwrappedCoordinates.getY() % blocksY : blocksY + (unwrappedCoordinates.getY() % blocksY));
    }

    public double getTranslateX(BlockCoordinates blockCoordinates){
        return getWrappedCoordinates(blockCoordinates).getX() * blockSize;
    }

    public double getTranslateY(BlockCoordinates blockCoordinates){
        return getWrappedCoordinates(blockCoordinates).getY() * blockSize;
    }

}
