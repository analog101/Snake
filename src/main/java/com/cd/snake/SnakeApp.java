package com.cd.snake;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashSet;

public class SnakeApp extends Application {

    private SpriteDirection direction = SpriteDirection.RIGHT;
    private boolean moved = false;
    private boolean running = false;

    private Timeline timeline = new Timeline();

    private Parent createContent(){

        BlockGrid blockGrid = new BlockGrid(20, 15, 20);

        Snake snake = new Snake();
        Food food = Food.createRandomlyPlacedFood(blockGrid, new HashSet<>());
        KeyFrame frame = new KeyFrame(Duration.seconds(0.1), event -> {
            if (!running) return;
            switch(direction) {
                case UP:
                    snake.move(SpriteDirection.UP);
                    break;
                case DOWN:
                    snake.move(SpriteDirection.DOWN);
                    break;
                case LEFT:
                    snake.move(SpriteDirection.LEFT);
                    break;
                case RIGHT:
                    snake.move(SpriteDirection.RIGHT);
                    break;
            }
            moved = true;
            if (snake.intersectsWith(food, blockGrid)){
                snake.eatFood();
                food.setRandomLocation(blockGrid, snake.getBlocks().keySet());
            }

            blockGrid.setSprites(food, snake);

        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);

        return blockGrid.getPane();
    }

    void restartGame(){
        stopGame(); startGame();
    }

    void startGame(){
        direction = SpriteDirection.RIGHT;
        timeline.play();
        running = true;
    }

    void stopGame(){
        running = false;
        timeline.stop();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.setOnKeyPressed(event -> {
            if (moved) {
                switch (event.getCode()){
                    case DOWN: if (direction != SpriteDirection.UP) direction = SpriteDirection.DOWN; break;
                    case UP: if (direction != SpriteDirection.DOWN) direction = SpriteDirection.UP; break;
                    case LEFT: if (direction != SpriteDirection.RIGHT) direction = SpriteDirection.LEFT; break;
                    case RIGHT: if (direction != SpriteDirection.LEFT) direction = SpriteDirection.RIGHT; break;
                }
            }
            moved = false;
        });
        primaryStage.setTitle("Tutorial");
        primaryStage.setScene(scene);
        primaryStage.show();
        startGame();

    }

    public static void main(String[] args){
        launch(args);
    }
}

