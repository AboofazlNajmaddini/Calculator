import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;

public class Calculator extends Application {
    private String userName;
    private String userID;

    private String currentInput = "";
    private String temp = "";
    private double answer = 0;
    ArrayList arrayList = new ArrayList<>();
    int arrayListCounter = 0;

    @Override
    public void start(Stage primaryStage) {

        //Border
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Display
        Label display = new Label("0");
        display.setPrefWidth(200);
        gridPane.add(display, 0, 0, 4, 1);

        //TextFields
        Label nameText = new Label("Please enter the User Name :");
        gridPane.add(nameText, 4, 0);

        TextField name = new TextField();
        name.setPromptText("User Name :");
        gridPane.add(name, 4, 1);

        Label idText = new Label("Please enter the User ID : ");
        gridPane.add(idText, 4, 2);
        TextField id = new TextField();
        id.setPromptText("User ID :");
        gridPane.add(id, 4, 3);

        //User Display
        Label user = new Label();
        Button save = new Button("Save");
        gridPane.add(save, 4, 4);
        name.setOnAction(actionEvent -> this.userName = name.getText());
        id.setOnAction(actionEvent -> this.userID = id.getText());
        save.setOnAction(actionEvent -> {
            user.setText("User: " + "\n" + "User Name: " + this.userName + "\n" + "User ID: " + this.userID);
            gridPane.add(user, 4, 5);
        });

        //Buttons
        String[] buttons = {"C", "*", "/", "!", "1", "2", "3", "+", "4", "5", "6", "-", "7", "8", "9", "=", "(", "0", ")"};
        Button[] buttonArray = new Button[buttons.length];
        for (int i = 0; i < buttons.length; i++) {
            Button button = setButtons(display, buttons[i]);
            buttonArray[i] = button;
        }
        for (int i = 0; i < buttonArray.length; i++) {
            gridPane.add(buttonArray[i], i % 4, i / 4 + 1);
        }

        //Screen
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }

    private Button setButtons(Label display, String buttons) {
        //buttonSize
        Button button = new Button(buttons);
        button.setPrefWidth(50);
        button.setPrefHeight(50);

        //buttonAction
        button.setOnAction(e -> {
            String buttonText = button.getText();
            switch (buttonText) {
                case "C":
                    currentInput = "";
                    answer = 0;
                    arrayList.clear();
                    arrayListCounter = 0;
                    break;
                case "=":
                    if (!Objects.equals(temp, "")) {
                        arrayList.addLast(temp);
                        arrayListCounter++;
                    }
                    answer = calculateResult();
                    currentInput = Double.toString(answer);
                    arrayList.clear();
                    arrayList.add(currentInput);
                    arrayListCounter = 1;
                    temp = "";
                    break;
                case ")", "(", "!", "*", "/", "+", "-":
                    if (!Objects.equals(temp, "")) {
                        arrayList.addLast(temp);
                        arrayListCounter++;
                    }
                    currentInput += buttonText;
                    arrayList.addLast(buttonText);
                    arrayListCounter++;
                    temp = "";
                    break;
                default:
                    temp += buttonText;
                    currentInput += buttonText;
            }
            display.setText(currentInput);
        });
        return button;
    }

    private double calculateResult() {
//Finding Parentheses
        for (int i = 0; i < arrayListCounter; i++) {
            if (arrayList.get(i).equals(")")) {
                for (int j = i; j >= 0; j--) {
                    if (arrayList.get(j).equals("(")) {
//Calculate Parentheses
                        for (int k = j; k <= i; k++) {
                            if (arrayList.get(k).toString().equals("!")) {
                                int temp = (int) factorial(Integer.parseInt(arrayList.get(k - 1).toString()));
                                arrayList.remove(k);
                                arrayList.set(k - 1, temp);
                                k = j;
                                i--;
                                arrayListCounter--;
                            }
                        }

                        for (int k = j; k <= i; k++) {
                            if (arrayList.get(k).toString().equals("*")) {
                                Double temp = Double.parseDouble(arrayList.get(k - 1).toString()) * Double.parseDouble(arrayList.get(k + 1).toString());
                                arrayList.remove(k + 1);
                                arrayList.remove(k);
                                arrayList.set(k - 1, temp);
                                k = j;
                                i -= 2;
                                arrayListCounter -= 2;
                            }
                        }

                        for (int k = j; k <= i; k++) {
                            if (arrayList.get(k).toString().equals("/")) {
                                Double temp = Double.parseDouble(arrayList.get(k - 1).toString()) / Double.parseDouble(arrayList.get(k + 1).toString());
                                arrayList.remove(k + 1);
                                arrayList.remove(k);
                                arrayList.set(k - 1, temp);
                                k = j;
                                i -= 2;
                                arrayListCounter -= 2;
                            }
                        }

                        for (int k = j; k <= i; k++) {
                            if (arrayList.get(k).toString().equals("+")) {
                                Double temp = Double.parseDouble(arrayList.get(k - 1).toString()) + Double.parseDouble(arrayList.get(k + 1).toString());
                                arrayList.remove(k + 1);
                                arrayList.remove(k);
                                arrayList.set(k - 1, temp);
                                k = j;
                                i -= 2;
                                arrayListCounter -= 2;
                            }
                        }

                        for (int k = j; k <= i; k++) {
                            if (arrayList.get(k).toString().equals("-")) {
                                Double temp = Double.parseDouble(arrayList.get(k - 1).toString()) - Double.parseDouble(arrayList.get(k + 1).toString());
                                arrayList.remove(k + 1);
                                arrayList.remove(k);
                                arrayList.set(k - 1, temp);
                                k = j;
                                i -= 2;
                                arrayListCounter -= 2;
                            }
                        }
                        arrayList.remove(i);
                        arrayList.remove(j);
                        arrayListCounter -= 2;
                        return calculateResult();
                    }
                }
            }
        }
// Continue Calculation
        for (int i = 0; i < arrayListCounter; i++) {
            if (arrayList.get(i).toString().equals("!")) {
                int temp = (int) factorial(Integer.parseInt(arrayList.get(i - 1).toString()));
                arrayList.remove(i);
                arrayList.set(i - 1, temp);
                i = 0;
                arrayListCounter--;
            }
        }

        for (int i = 0; i < arrayListCounter; i++) {
            if (arrayList.get(i).toString().equals("*")) {
                Double temp = Double.parseDouble(arrayList.get(i - 1).toString()) * Double.parseDouble(arrayList.get(i + 1).toString());
                arrayList.remove(i + 1);
                arrayList.remove(i);
                arrayList.set(i - 1, temp);
                i = 0;
                arrayListCounter -= 2;
            }
        }

        for (int i = 0; i < arrayListCounter; i++) {
            if (arrayList.get(i).toString().equals("/")) {
                Double temp = Double.parseDouble(arrayList.get(i - 1).toString()) / Double.parseDouble(arrayList.get(i + 1).toString());
                arrayList.remove(i + 1);
                arrayList.remove(i);
                arrayList.set(i - 1, temp);
                i = 0;
                arrayListCounter -= 2;
            }
        }
        for (int i = 0; i < arrayListCounter; i++) {
            if (arrayList.get(i).toString().equals("+")) {
                Double temp = Double.parseDouble(arrayList.get(i - 1).toString()) + Double.parseDouble(arrayList.get(i + 1).toString());
                arrayList.remove(i + 1);
                arrayList.remove(i);
                arrayList.set(i - 1, temp);
                i = 0;
                arrayListCounter -= 2;
            }
        }
        for (int i = 0; i < arrayListCounter; i++) {
            if (arrayList.get(i).toString().equals("-")) {
                Double temp = Double.parseDouble(arrayList.get(i - 1).toString()) - Double.parseDouble(arrayList.get(i + 1).toString());
                arrayList.remove(i + 1);
                arrayList.remove(i);
                arrayList.set(i - 1, temp);
                i = 0;
                arrayListCounter -= 2;

            }
        }
        return Double.parseDouble(arrayList.getFirst().toString());
    }

    private double factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
