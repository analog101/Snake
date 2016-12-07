package com.cd.snake;

import javafx.scene.paint.Color;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Food extends BlockSprite {

    final Color foodColor = Color.RED;

    private BlockCoordinates blockCoordinates;

    private Food(BlockCoordinates blockCoordinates) {
        this.blockCoordinates = blockCoordinates;
    }

    public static Food createRandomlyPlacedFood(BlockGrid blockGrid, Set<BlockCoordinates> excludeCoordinates){
        BlockCoordinates randomCoordinates = blockGrid.getWrappedCoordinates(
                new BlockCoordinates((int)(Math.random()*(double)blockGrid.getBlocksX()),
                        (int)(Math.random()*(double)blockGrid.getBlocksY())));
        if (excludeCoordinates.contains(randomCoordinates)){
            return createRandomlyPlacedFood(blockGrid, excludeCoordinates);
        }
        return new Food(randomCoordinates);
    }

    public void setRandomLocation(BlockGrid blockGrid, Set<BlockCoordinates> excludeCoordinates){
        BlockCoordinates randomCoordinates = blockGrid.getWrappedCoordinates(
                new BlockCoordinates((int)(Math.random()*(double)blockGrid.getBlocksX()),
                        (int)(Math.random()*(double)blockGrid.getBlocksY())));
        if (excludeCoordinates.contains(randomCoordinates)){
            setRandomLocation(blockGrid,excludeCoordinates);
        }
        this.blockCoordinates = randomCoordinates;
    }

    @Override
    public Map<BlockCoordinates, Color> getBlocks() {
        Map<BlockCoordinates, Color> blocks = new HashMap<>();
        blocks.put(blockCoordinates, this.foodColor);
        return Collections.unmodifiableMap(blocks);
    }
}
