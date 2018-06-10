package CONTROLLERS;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;

public class ChangeVeiew {

    public static Background GetBackGround(String path) {
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(path), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(100 ,100 ,false,false, false,false));
        return new Background(backgroundImage);
    }

    public static Image GetImageByPath(String path) {
        File file = new File(path);
        return new Image(file.toURI().toString());
    }
}
