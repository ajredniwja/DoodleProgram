package view;

import javafx.application.Application;
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
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DoodleView extends Application
{
    public static final int WIN_WIDTH = 1000;
    public static final int WIN_HEIGHT = 600;
    public static final int SHAPE_ICON_SIZE = 20;
    public static final int MAX_STROKE = 20;
    public static final int MIN_STROKE = 1;

    //drawing on the canvas
    Canvas canvas = new Canvas(Screen.getPrimary().getVisualBounds().getWidth(),Screen.getPrimary().getVisualBounds().getHeight());//give the width
    private Pair<Double, Double> startPoint;
    GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
    BorderPane mainPanel = new BorderPane();
    List<Line> linesList = new ArrayList<>();
    List<Ellipse> ovalsList = new ArrayList<>();

    String buttonText = "Line";

    //selecting shapes
    private ToggleGroup shapeGroup;

    //shape settings
    private ColorPicker fillColorPicker = new ColorPicker();
    private ColorPicker strokeColorPicker = new ColorPicker();
    private Slider strokeSlider;
    private CheckBox filledCheckbox;

    @Override
    public void start(Stage stage)
    {
        stage.setTitle("Doodle Program");
        stage.setScene(getPrimaryScene());
        stage.show();
    }

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
        ToggleButton[] buttons = new ToggleButton[shapes.length];
        shapeGroup = new ToggleGroup();

        for (int i = 0; i < shapes.length; i++) {
            buttons[i] = getImageToggleButton(shapes[i]);
            int finalI = i;
            buttons[i].setOnMousePressed(e-> buttonText = shapes[finalI]);
        }

        buttons[0].setSelected(true);
        shapeGroup.getToggles().addAll(buttons);
        shapesPanel.getChildren().addAll(buttons);

        return shapesPanel;
    }

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
        editPanel.getChildren().addAll(buttons);

        return editPanel;
    }

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

        graphicsContext.setFill(Color.GREEN);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);

        drawOnCanvas();

        box.getChildren().add(canvas);

        return box;
    }

    private void drawOnCanvas()
    {
        canvas.setOnMousePressed(
                e-> startPoint = new Pair<>(e.getX(),e.getY())
        );
        canvas.setOnMouseDragged(
                e->{
                    if (buttonText.equals("Line"))
                    {
                        drawLine(e.getX(),e.getY());
                    }
                    if (buttonText.equals("Oval"))
                    {
                        drawOval(e.getX(),e.getY());
                    }
                }
        );
        canvas.setOnMouseReleased(
                e->{
                    if (buttonText.equals("Line"))
                    {
                        linesList.add(new Line(startPoint.getKey(), startPoint.getValue(), e.getX(), e.getY()));
                        graphicsContext.strokeLine(startPoint.getKey(), startPoint.getValue(), e.getX(), e.getY());
                    }
                    if (buttonText.equals("Oval"))
                    {
                        ovalsList.add(new Ellipse(startPoint.getKey(), startPoint.getValue(), e.getX(), e.getY()));
                        graphicsContext.strokeOval(startPoint.getKey(), startPoint.getValue(), e.getX(), e.getY());                    }
                }
        );
    }

    private void drawLine(double pointX, double pointY)
    {
        graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        addAllOvals();
        addAllLines();
        graphicsContext.strokeLine(startPoint.getKey(), startPoint.getValue(), pointX, pointY);
    }

    private void drawOval(double pointX, double pointY)
    {
        graphicsContext.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        addAllLines();
        addAllOvals();
        graphicsContext.strokeOval(startPoint.getKey(), startPoint.getValue(), pointX, pointY);
    }

    private void addAllLines()
    {
        for (Line lines : linesList)
        {
            graphicsContext.strokeLine(lines.getStartX(),lines.getStartY(),lines.getEndX(),lines.getEndY());
        }
    }

    private void addAllOvals()
    {
        for (Ellipse ovals : ovalsList)
        {
            graphicsContext.strokeOval(ovals.getCenterX(),ovals.getCenterY(),ovals.getRadiusX(),ovals.getRadiusY());
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
    }

    private void editMenu(Menu edit)
    {
        MenuItem[] items = {new MenuItem("Undo"), new MenuItem("Redo")};
        edit.getItems().addAll(items);
    }

    private void drawMenu(Menu draw)
    {
        Menu shapesMenu = new Menu("Shape Tools");
        MenuItem[] shapes = {new MenuItem("Line"), new MenuItem("Oval"),
                             new MenuItem("Rectangle"), new MenuItem("Squiggle")};
        shapesMenu.getItems().addAll(shapes);
        draw.getItems().add(shapesMenu);

        MenuItem clear = new MenuItem("Clear Shapes");
        draw.getItems().add(clear);
    }

    private void help(Menu about)
    {
        MenuItem[] items = {new MenuItem("About")};
        about.getItems().addAll(items);
    }
}
