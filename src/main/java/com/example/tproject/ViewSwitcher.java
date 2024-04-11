package com.example.tproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * <p>
 * EÞH - changed to include caching of controllers
 */
public class ViewSwitcher {
    private static final Map<View, Parent> cache = new HashMap<>();
    private static final Map<View, Object> controllers = new HashMap<>();
    private static Scene scene;

    //bætt við
    private static final Deque<View> viewHistory = new ArrayDeque<>();

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }
        try {
            Parent root;
            FXMLLoader loader = null;
            if (cache.containsKey(view)) {
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                System.out.println("Loading from FXML");
                loader = new
                        FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
                root = loader.load();
                cache.put(view, root);
                scene.setRoot(root);
                controllers.put(view, loader.getController());
            }
            scene.setRoot(root);
            //bætt við
            pushToHistory(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //bætt við
    public static void goBack() {
        if (viewHistory.size() > 1) {
            viewHistory.pop(); // Remove current view
            View lastView = viewHistory.peek(); // Get the previous view
            switchTo(lastView); // Switch to the previous view
        } else {
            System.out.println("No previous view to return to.");
        }
    }

    //bætt við, public svo getum notað í applcation
    public static void pushToHistory(View view) {
        if (viewHistory.isEmpty() || viewHistory.peek() != view) {
            viewHistory.push(view);
        }
    }
    public static Object lookup(View v) {
        return controllers.get(v);
    }
}
