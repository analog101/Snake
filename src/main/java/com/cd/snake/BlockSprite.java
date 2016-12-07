package com.cd.snake;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class BlockSprite {

    public abstract Map<BlockCoordinates, Color> getBlocks();

    public final boolean intersectsWith(BlockSprite blockSprite, BlockGrid blockGrid) {
        if (blockSprite == null) return false;
        Map<BlockCoordinates, Color> m1 = this.getBlocks();
        Map<BlockCoordinates, Color> m2 = blockSprite.getBlocks();
        if (m1 == null || m2 == null) return false;
        Set<BlockCoordinates> s1 = new HashSet<>();
        for (BlockCoordinates c : m1.keySet()){
            s1.add(blockGrid.getWrappedCoordinates(c));
        }
        for (BlockCoordinates c : m2.keySet()){
            if (s1.contains(blockGrid.getWrappedCoordinates(c))){
                return true;
            }
        }
        return false;
    }

}
