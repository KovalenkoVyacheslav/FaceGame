package VIEWS;

import CONTROLLERS.ChangeVeiew;
import MODELS.ClientState;
import MODELS.ClientType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Control;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class FXMLStartController implements Initializable {

    @FXML
    private JFXButton btnIce;

    @FXML
    private JFXButton btnTea;

    @FXML
    private JFXButton btnCoffe;

    @FXML
    private ImageView imComputer;

    @FXML
    private ImageView imPlayer;

    @FXML
    private Label lblCount;

    @FXML
    private Label lblBalance;

    @FXML
    private Label lblClient;

    @FXML
    private ImageView imClientFace;

    private int counter;
    private int balance = 0;
    private int client = 0;

    private ClientType[] clients = {ClientType.Boy, ClientType.Girl, ClientType.Grandfather, ClientType.Grandmother,
            ClientType.Man, ClientType.Woman};
    private ClientState[] moods = {ClientState.CHEERFUL, ClientState.NEUTRAL, ClientState.SAD, ClientState.ANGRY};

    private ClientType currentClient;
    private ClientState currentMood;
    private int moodIndex;


    @FXML
    void OnActionCoffeClick(ActionEvent event) {
        OpenImages(event);
    }

    @FXML
    void OnActionIceClick(ActionEvent event) {
        OpenImages(event);
    }

    @FXML
    void OnActionTeaClick(ActionEvent event) {
        OpenImages(event);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnIce.setBackground(ChangeVeiew.GetBackGround( getClass().getResource("/food/food-01.png")
                .toExternalForm()));

        btnCoffe.setBackground(ChangeVeiew.GetBackGround( getClass().getResource("/food/food-02.png")
                .toExternalForm()));

        btnTea.setBackground(ChangeVeiew.GetBackGround( getClass().getResource("/food/food-03.png")
                .toExternalForm()));

        lblBalance.setText(String.valueOf(balance));
        lblClient.setText(String.valueOf(client));

        GenerateClient();
    }

    private void OpenImages(ActionEvent event) {
        String id = ((Control)event.getSource()).getId();
        int imagePlayer = -1;
        int imageComputer = (new Random().nextInt(3) + 1);

        if(id.equals(btnIce.getId()))
            imagePlayer = 1;
        else if(id.equals(btnCoffe.getId()))
            imagePlayer = 2;
        else if(id.equals(btnTea.getId()))
            imagePlayer = 3;

        imPlayer.setImage(ChangeVeiew.GetImageByPath("src/main/resources/food/food-0"+imagePlayer+".png"));
        imComputer.setImage(ChangeVeiew.GetImageByPath("src/main/resources/food/food-0"+imageComputer+".png"));

        if(imagePlayer == imageComputer) {
            if (--moodIndex <= -1)
                moodIndex = 0;
        }
        else {
            if (++moodIndex >= 4)
                moodIndex = 3;
        }

        SetCount(--counter);
        SetClientMood();

        if(counter == 0) {
            if(currentMood == ClientState.CHEERFUL)
                balance += 30;
            else if(currentMood == ClientState.NEUTRAL)
                balance += 10;
            else if(currentMood == ClientState.SAD)
                balance += 0;
            else if(currentMood == ClientState.ANGRY)
                balance -= 20;
            else {
                CallAlertMessage(Alert.AlertType.ERROR, "Error with Client state");
            }


            if (balance == 100) {
                CallAlertMessage(Alert.AlertType.INFORMATION, "You win !!!!");
            }
            else if(balance == -200) {
                CallAlertMessage(Alert.AlertType.INFORMATION, "You lost !!!");
            }
            else {
                lblBalance.setText(String.valueOf(balance));
                lblClient.setText(String.valueOf(++client));
                GenerateClient();
            }
        }
    }

    private void GenerateClient() {
        counter = 3;
        SetCount(counter);

        imPlayer.setImage(null);
        imComputer.setImage(null);

        currentClient = clients[new Random().nextInt(6)];
        moodIndex = new Random().nextInt(2) + 1;
        SetClientMood();
    }

    private void SetCount(int c) {
        lblCount.setText(String.valueOf(c));
    }

    private void SetClientMood() {
        currentMood = moods[moodIndex];

        String local = "src/main/resources/"+currentClient.toString()+"/"+currentClient.toString()+"-0"+
                +(currentMood.ordinal()+1)+".png";
        imClientFace.setImage(ChangeVeiew.GetImageByPath(local));
    }

    private void CallAlertMessage(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setHeaderText(null);
        alert.setTitle("Game State");
        alert.setContentText(message);
        alert.showAndWait();
    }
}