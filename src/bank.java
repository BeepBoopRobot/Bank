import java.io.*;
import java.util.Optional;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
import java.util.Date;

public class bank {
    private static Label balanceNum;
    private static int balanceInt = 65535;
    private static Label depositNum;
    private static int depositInt = 0;
    private static Label onHandNum;
    private static int onHandInt = 13126;
    private static ArrayList<Integer> depositArr;
    private static boolean atMax = false;

    public static void main(String[] args) {
        new JFXPanel();
        Platform.runLater(bank::launch);
    }

    private static void close(WindowEvent we) {
        Alert al = new Alert(AlertType.CONFIRMATION);
        al.setTitle("Are you sure?");
        al.setHeaderText("Are you sure you want to close?");
        al.setContentText("Any unsaved data may be lost");
        Optional<ButtonType> op = al.showAndWait();
        if (op.get() == ButtonType.OK) {
            terminate();
        } else {
            we.consume();
        }
    }

    private static void terminate() {
        System.out.println("Closing");
        System.exit(0);
    }

    private static void launch() {
        Stage stage = new Stage();
        stage.setTitle("Balance Enquiries");
        stage.setOnCloseRequest((WindowEvent we) -> close(we));
        stage.setWidth(480);
        stage.setHeight(500);
        stage.setResizable(false);
        stage.show();

        GridPane cen = new GridPane();
        cen.setHgap(10);
        cen.setVgap(10);
        cen.setMinSize(200, 194);
        cen.setMaxHeight(195);
        cen.setStyle("-fx-background-color: #D8BFD8;-fx-background-radius:25px;");

        Button one = new Button("1");
        one.setMaxWidth(Double.MAX_VALUE);
        Button two = new Button("2");
        two.setMaxWidth(Double.MAX_VALUE);
        Button three = new Button("3");
        three.setMaxWidth(Double.MAX_VALUE);
        Button four = new Button("4");
        four.setMaxWidth(Double.MAX_VALUE);
        Button five = new Button("5");
        five.setMaxWidth(Double.MAX_VALUE);
        Button six = new Button("6");
        six.setMaxWidth(Double.MAX_VALUE);
        Button seven = new Button("  7  ");
        seven.setMaxWidth(Double.MAX_VALUE);
        Button eight = new Button("  8  ");
        eight.setMaxWidth(Double.MAX_VALUE);
        Button nine = new Button("  9  ");
        nine.setMaxWidth(Double.MAX_VALUE);
        Button zero = new Button("0");
        zero.setMaxWidth(Double.MAX_VALUE);
        Button clear = new Button("C");
        clear.setMaxWidth(Double.MAX_VALUE);
        Button del = new Button("<<");
        del.setMaxWidth(Double.MAX_VALUE);

        VBox sv = new VBox();
        sv.setSpacing(10);
        sv.setPadding(new Insets(10, 10, 10, 10));
        Button save = new Button("Save Data");
        save.setOnAction((ActionEvent se) -> {
            saveData();
        });
        Button confirm = new Button("Confirm Deposit");
        confirm.setOnAction((ActionEvent se) -> {
            confirmDep();
        });
        sv.getChildren().addAll(save, confirm);

        depositArr = new ArrayList<>();

        VBox vb = new VBox();
        VBox vb2 = new VBox();
        VBox vb3 = new VBox();
        vb.setSpacing(10);
        vb2.setSpacing(10);
        vb3.setSpacing(10);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb2.setPadding(new Insets(10, 15, 10, 15));
        vb3.setPadding(new Insets(10, 10, 10, 10));
        vb.getChildren().addAll(seven, four, one, clear);
        vb.setAlignment(Pos.CENTER_LEFT);
        vb2.getChildren().addAll(eight, five, two, zero);
        vb2.setAlignment(Pos.CENTER);
        vb3.getChildren().addAll(nine, six, three, del);
        vb3.setAlignment(Pos.TOP_RIGHT);
        cen.add(vb, 0, 0);
        cen.add(vb2, 1, 0);
        cen.add(vb3, 2, 0);

        Pane top = new Pane();
        top.setStyle("-fx-border-style:solid;-fx-border-width:1px;-fx-border-insets:10,10,0,10;-fx-border-radius:25px;");
        Label balance = new Label("Balance");
        balance.setStyle("-fx-font-size:28;");
        balance.setLayoutX(25);
        balance.setLayoutY(105);
        top.getChildren().add(balance);

        balanceNum = new Label(String.valueOf(balanceInt));
        balanceNum.setStyle("-fx-font-size:25;");
        balanceNum.setLayoutX(210);
        balanceNum.setLayoutY(105);
        top.getChildren().add(balanceNum);

        Label deposit = new Label("Deposit");
        deposit.setStyle("-fx-font-size:28;");
        deposit.setLayoutX(25);
        deposit.setLayoutY(55);
        top.getChildren().add(deposit);

        depositNum = new Label(String.valueOf(depositInt));
        depositNum.setStyle("-fx-font-size:25;");
        depositNum.setLayoutX(210);
        depositNum.setLayoutY(55);
        top.getChildren().add(depositNum);

        Label onHand = new Label("On Hand");
        onHand.setStyle("-fx-font-size:28;");
        onHand.setLayoutX(25);
        onHand.setLayoutY(15);
        top.getChildren().add(onHand);

        onHandNum = new Label(String.valueOf(onHandInt));
        onHandNum.setStyle("-fx-font-size:25;");
        onHandNum.setLayoutX(210);
        onHandNum.setLayoutY(15);
        top.getChildren().add(onHandNum);

        Line line = new Line(20, 100, 450, 100);
        top.getChildren().add(line);
        Line line2 = new Line(200, 90, 430, 90);
        top.getChildren().add(line2);
        Line line3 = new Line(200, 50, 430, 50);
        top.getChildren().add(line3);
        Line line4 = new Line(200, 140, 430, 140);
        top.getChildren().add(line4);

        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane);
        BorderPane.setAlignment(cen, Pos.CENTER);
        BorderPane.setAlignment(top, Pos.TOP_RIGHT);
        BorderPane.setMargin(cen, new Insets(0, 140, 0, 140));
        pane.setCenter(cen);
        pane.setTop(top);
        pane.setBottom(sv);
        stage.setScene(scene);
        //each button will have a thing along these lines
        one.setOnAction((ActionEvent se) -> {
            depositArr.add(1);
            updateLabels();
        });
        two.setOnAction((ActionEvent se) -> {
            depositArr.add(2);
            updateLabels();
        });
        three.setOnAction((ActionEvent se) -> {
            depositArr.add(3);
            updateLabels();
        });
        four.setOnAction((ActionEvent se) -> {
            depositArr.add(4);
            updateLabels();
        });
        five.setOnAction((ActionEvent se) -> {
            depositArr.add(5);
            updateLabels();
        });
        six.setOnAction((ActionEvent se) -> {
            depositArr.add(6);
            updateLabels();
        });
        seven.setOnAction((ActionEvent se) -> {
            depositArr.add(7);
            updateLabels();
        });
        eight.setOnAction((ActionEvent se) -> {
            depositArr.add(8);
            updateLabels();
        });
        nine.setOnAction((ActionEvent se) -> {
            depositArr.add(9);
            updateLabels();
        });
        zero.setOnAction((ActionEvent se) -> {
            depositArr.add(0);
            updateLabels();
        });
        clear.setOnAction((ActionEvent se) -> {
            depositArr.clear();
            updateLabels();
        });
        del.setOnAction((ActionEvent se) -> deleteOne());
    }

    private static void updateLabels() {
        if (!atMax) {
            StringBuilder temp = new StringBuilder();
            if (depositInt > onHandInt && !atMax) {
                setMax();
                updateLabels();
            }
            for (Object aDepositArr : depositArr) temp.append(aDepositArr);
            if (depositArr.size() == 0) temp = new StringBuilder("0");
            depositNum.setText(temp.toString());
            depositInt = Integer.valueOf(temp.toString());
            onHandNum.setText(String.valueOf(onHandInt - depositInt));
            balanceNum.setText(String.valueOf(balanceInt + Integer.valueOf(temp.toString())));
            if (Integer.valueOf(onHandNum.getText()) < 0) {
                setMax();
                System.out.println("no");
                updateLabels();
            }
        }
    }

    private static void deleteOne() {
        if (depositArr.size() == 0) {
            depositNum.setText("0");
        } else {
            depositArr.remove(depositArr.size() - 1);
            atMax = false;

        }
        updateLabels();
    }

    private static void setMax() {
        StringBuilder temp = new StringBuilder();
        String a = String.valueOf(onHandInt);
        depositArr.clear();
        atMax = true;
        for (int j = 0; j < a.length(); j++) depositArr.add((int) a.charAt(j));
        System.out.println(depositArr);
        onHandNum.setText("0");
        for (Object aDepositArr : depositArr) temp.append(aDepositArr);
        depositNum.setText(temp.toString());
        balanceNum.setText(String.valueOf(balanceInt + onHandInt));
    }

    private static void saveData() {
        try {
            Date da = new Date();
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Programming\\Files\\Text Files\\bankInfo.txt", true));
            bw.write(da.toString() + ", on hand: " + onHandInt + ", balance: " + balanceInt);
            bw.newLine();
            System.out.println("Saved!");
            bw.close();
        } catch (IOException e) {
            System.out.println("broke");
        }
    }

    private static void confirmDep() {
        onHandInt = Integer.valueOf(onHandNum.getText());
        balanceInt += depositInt;
        depositArr.clear();
        updateLabels();
    }
}
