package VIEWS;

import CONTROLLERS.ChangeVeiew;
import MODELS.ClientState;
import MODELS.ClientType;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    int counter = 3;
    int balance = 0;
    int client = 0;

    ClientType[] clients = {ClientType.Boy, ClientType.Girl, ClientType.Grandfather, ClientType.Grandmother,
            ClientType.Man, ClientType.Woman};
    ClientState[] moods = {ClientState.CHEERFUL, ClientState.NEUTRAL, ClientState.SAD, ClientState.ANGRY};

    ClientType currentClient;
    ClientState currentMood;
    int moodIndex;


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

    public void OpenImages(ActionEvent event) {
        String id = ((Control)event.getSource()).getId();
        int imagePlayer = -1;
        int imageComputer = (new Random().nextInt(3) + 1);

        System.out.println(imageComputer);
        if(id.equals(btnIce.getId()))
            imagePlayer = 1;
        else if(id.equals(btnCoffe.getId()))
            imagePlayer = 2;
        else if(id.equals(btnTea.getId()))
            imagePlayer = 3;

        imPlayer.setImage(ChangeVeiew.GetImageByPath("src/main/resources/food/food-0"+imagePlayer+".png"));
        imComputer.setImage(ChangeVeiew.GetImageByPath("src/main/resources/food/food-0"+imageComputer+".png"));

    }

    public void GenerateClient() {
        lblCount.setText(String.valueOf(counter));

        currentClient = clients[new Random().nextInt(6)];
        moodIndex = new Random().nextInt(2) + 1;
        currentMood = moods[moodIndex];

        String local = "src/main/resources/"+currentClient.toString()+"/"+currentClient.toString()+"-0"+
                +(currentMood.ordinal()+1)+".png";
        imClientFace.setImage(ChangeVeiew.GetImageByPath(local));
    }
}
//1-радий
//2-злий
//3-нейтральний
//4-сумний