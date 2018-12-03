//Ajwinder Singh
//DoodleView.java
//11/28/2018

package view;

import controller.PaintController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;
import model.*;

/**
 * This is the class that assempbles all the components of my paint program and shows a user interface to the user.
 *
 * @author ajwinder
 * @version 1.0
 */
public class DoodleView extends Application implements IObserver
{
    public static final int WIN_WIDTH = 1000;
    public static final int WIN_HEIGHT = 600;
    public static final int SHAPE_ICON_SIZE = 20;
    public static final int MAX_STROKE = 20;
    public static final int MIN_STROKE = 1;

    //drawing on the canvas
    private Canvas canvas = new Canvas(Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());//give the width
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    private BorderPane mainPanel = new BorderPane();

    //Instances fields
    private IShape shapeObject;
    private PaintController controller;

    //shape settings
    private ColorPicker fillColorPicker = new ColorPicker();
    private ColorPicker strokeColorPicker = new ColorPicker();
    private Slider strokeSlider;
    private CheckBox filledCheckbox;
    private String buttonText = "Line";
    private double filledCheckBoxMarker = 0;
    private ToggleButton[] buttons;


    /**
     * Constructor gives the instance to observe
     */
    public DoodleView()
    {
        controller = new PaintController(this);
    }

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Doodle Program");
        stage.setScene(getPrimaryScene());
        stage.show();
    }

    //get primary secene
    private Scene getPrimaryScene()
    {
        VBox top = new VBox();
        top.getChildren().addAll(buildMenu(), getToolbar());

        //set the primary regions
        mainPanel.setTop(top);
        mainPanel.setCenter(getCanvas());

        Scene scene = new Scene(mainPanel, WIN_WIDTH, WIN_HEIGHT);
        scene.getStylesheets().add("styles.css");

        return scene;
    }
    //toolbar
    private Parent getToolbar()
    {
        HBox panel = new HBox();
        panel.setId("toolbar-main");
        panel.getChildren().addAll(buildShapeSection(), buildSettings(), buildEdit());

        return panel;
    }

    private HBox buildShapeSection()
    {
        HBox shapesPanel = new HBox();
        shapesPanel.setId("toolbar-shapes");

        String[] shapes = {"Line", "Oval", "Rectangle", "Squiggle"};
        buttons = new ToggleButton[shapes.length];
        //selecting shapes
        ToggleGroup shapeGroup = new ToggleGroup();

        for (int i = 0; i < shapes.length; i++) {
            buttons[i] = getImageToggleButton(shapes[i]);
            int finalI = i;
            //set buttonText whenever clicked to teh kind of shape
            buttons[i].setOnMousePressed(
                    e-> buttonText = shapes[finalI]);
        }

        buttons[0].setSelected(true);
        shapeGroup.getToggles().addAll(buttons);
        shapesPanel.getChildren().addAll(buttons);

        return shapesPanel;
    }

    //settings panel
    private HBox buildSettings()
    {
        HBox settingsPanel = new HBox();
        settingsPanel.setId("toolbar-settings");

        styleColorPicker(fillColorPicker);
        styleColorPicker(strokeColorPicker);

        VBox strokeBox = new VBox();
        Label strokeLabel = new Label("Stroke: 1");
        strokeSlider = new Slider();
        strokeBox.getChildren().addAll(strokeSlider, strokeLabel);

        strokeSlider.setMin(MIN_STROKE);
        strokeSlider.setMax(MAX_STROKE);
        strokeSlider.valueProperty().addListener((observable, oldV, newV) ->
                strokeLabel.setText("Stroke: " + numToInt(newV)));

        filledCheckbox = new CheckBox("Filled");

        settingsPanel.getChildren().addAll(new Label("Fill:"), fillColorPicker,
                new Label("Stroke:"), strokeColorPicker, strokeBox, filledCheckbox);

        return settingsPanel;
    }

    private void styleColorPicker(ColorPicker picker)
    {
        picker.getStyleClass().add(ColorPicker.STYLE_CLASS_BUTTON);
        picker.setValue(Color.BLACK);
    }

    private int numToInt(Number value)
    {
        return (int) Math.floor(value.doubleValue());
    }

    private HBox buildEdit()
    {
        HBox editPanel = new HBox();
        editPanel.setId("toolbar-edits");

        String[] edits = {"undo", "redo"};
        Button[] buttons = new Button[edits.length];

        for (int i = 0; i < edits.length; i++) {
            buttons[i] = getImageButton(edits[i]);
        }

        //undo button
        buttons[0].setOnAction(event -> undo());
        //redo button

        buttons[1].setOnAction(event -> redo());

        editPanel.getChildren().addAll(buttons);

        return editPanel;
    }

    //method to perform redo
    private void redo()
    {
        controller.redo();
        controller.notifyShapes();
    }

    //method to perform undo
    private void undo()
    {
        controller.undo();
        controller.notifyShapes();
    }

    //gets a image for the button
    private ImageView getButtonIcon(String text)
    {
        ImageView image = new ImageView(text + ".png");
        image.setFitHeight(SHAPE_ICON_SIZE);
        image.setFitWidth(SHAPE_ICON_SIZE);
        return image;
    }

    private ToggleButton getImageToggleButton(String text)
    {
        ToggleButton result = new ToggleButton();
        result.setGraphic(getButtonIcon(text));
        return result;
    }

    private Button getImageButton(String text)
    {
        Button result = new Button();
        result.setGraphic(getButtonIcon(text));
        return result;
    }

    private Parent getCanvas()
    {
        VBox box = new VBox();

        //Method that draws on canvas with all the settings
        drawOnCanvas();

        box.getChildren().add(canvas);

        return box;
    }

    //this is a method which has event handlers for drawing with mouse.
    //all drawing is handled in this method
    private void drawOnCanvas()
    {
        canvas.setOnMousePressed(
                e-> {
//                    graphicsContext.setStroke(strokeColorPicker.getValue());
                    //instantiate a class using the Factory Method
                    if (filledCheckbox.isSelected())
                    {
                        //1.0 is true case for fill is selected (using double because to decreaset the arguments for the class)
                        filledCheckBoxMarker = 1.0;
                    }
                    else
                    {
                        //zero indicates not selected fill
                        filledCheckBoxMarker = 0.0;
                    }
                    Pair<Color, Color> colorSeleted = new Pair<>(strokeColorPicker.getValue(),fillColorPicker.getValue());
                    shapeObject = controller.getInstance(buttonText,colorSeleted,
                            e.getX(),e.getY(),
                            0.0,0.0,strokeSlider.getValue(),filledCheckBoxMarker);
                }
        );
        canvas.setOnMouseDragged(
                e->{
                    //draw using the points wherever the user drags the mouse
                    shapeObject.setPoints(e.getX(),e.getY());
                    graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
                    //using observer pattern to check for changes and update the canvas
                    controller.addShape(shapeObject);
                    //strokes to draw to the canvas
                    draw();
                }
        );

        canvas.setOnMouseReleased(
                e->{
                    //store the shape
                    shapeObject.addShape();
                    draw();
                }
        );
    }

    //this is a draw method which uses the drawshape method of the IShape implemented class to stroke specific shape
    private void draw()
    {
        shapeObject.drawShape(graphicsContext);
    }

    //adds all the previously added shapes
    private void addAllShapes()
    {
        graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        for (IShape shapes : controller.getShapes())
        {
            shapes.drawShape(graphicsContext);
        }
    }

    private MenuBar buildMenu()
    {
        MenuBar menuBar = new MenuBar();
        Menu file = new Menu("File");
        Menu edit = new Menu("Edit");
        Menu draw = new Menu("Draw");
        Menu help = new Menu("Help");

        fileMenu(file);
        editMenu(edit);
        drawMenu(draw);
        help(help);

        menuBar.getMenus().addAll(file, edit, draw, help);
        return menuBar;
    }

    private void fileMenu(Menu file)
    {
        MenuItem[] items = {new MenuItem("Quit")};
        file.getItems().addAll(items);
        items[0].setOnAction(event -> Platform.exit());
    }

    private void editMenu(Menu edit)
    {
        MenuItem[] items = {new MenuItem("Undo"), new MenuItem("Redo")};
        items[0].setOnAction(event -> undo());
        items[1].setOnAction(event -> redo());

        edit.getItems().addAll(items);
    }

    private void drawMenu(Menu draw)
    {
        Menu shapesMenu = new Menu("Shape Tools");
        MenuItem[] shapes = {new MenuItem("Line"), new MenuItem("Oval"),
                             new MenuItem("Rectangle"), new MenuItem("Squiggle")};

        for (int i = 0; i < shapes.length; i++)
        {
            int buttonIndex = i;
            shapes[i].setOnAction(event -> {
                //binding menu selectors to button selectors
                buttons[buttonIndex].setSelected(true);
                buttonText = shapes[buttonIndex].getText();
            });
        }
        shapesMenu.getItems().addAll(shapes);
        draw.getItems().add(shapesMenu);


        MenuItem clear = new MenuItem("Clear Shapes");
        clear.setOnAction(event -> clearShapes());
        draw.getItems().add(clear);
    }

    //this method clear all the shapes from the canvas
    private void clearShapes()
    {
        controller.clearShapes();
        controller.notifyShapes();
    }

    private void help(Menu about)
    {
        MenuItem[] items = {new MenuItem("About")};
        about.getItems().addAll(items);
    }

    /**
     * Updates the canvas and adds all the shapes that were previously drawn after there is a new shape is drawn
     * @param observable the object we are observing
     * @param args args
     */
    @Override
    public void update(Observable observable, Object... args)
    {
        addAllShapes();
    }

    /**
     * Tostring
     * @return class name
     */
    @Override
    public String toString()
    {
        return "DoodleView";
    }
}
