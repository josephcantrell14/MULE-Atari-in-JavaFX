package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable{

    @FXML
    private StackPane p1Stats, p2Stats, p3Stats, p4Stats;
    @FXML
    private static StackPane[] playerStats = new StackPane[4];
    @FXML
    private Text p1Name, p1MoneyText, p1FoodText, p1EnergyText, p1OreText, p1CrystiteText;
    @FXML
    private Text p2Name, p2MoneyText, p2FoodText, p2EnergyText, p2OreText, p2CrystiteText;
    @FXML
    private Text p3Name, p3MoneyText, p3FoodText, p3EnergyText, p3OreText, p3CrystiteText;
    @FXML
    private Text p4Name, p4MoneyText, p4FoodText, p4EnergyText, p4OreText, p4CrystiteText;
    @FXML
    private Text storeFood, storeEnergy, storeOre, storeCrystite, storeMULES;
    @FXML
    private static Text storeStats[] = new Text[5];
    @FXML
    private static Text[][] pText = new Text[4][6];
    @FXML
    private AnchorPane mapPane;
    @FXML
    private HBox townPane;
    @FXML
    private VBox pub;
    @FXML
    private static BorderPane playerRankBP;
    @FXML
    private Text timerText;
    @FXML
    private Text roundText;
    @FXML
    private Button passTurn;
    @FXML
    private Button saveButton;
    @FXML
    private StackPane currentPlayerID;

    @FXML
    private GridPane storePane;


    @FXML
    private Button tileButton23;

    @FXML
    private Circle owner1, owner2, owner3, owner4, owner5, owner6, owner7, owner8, owner9, owner10,
            owner11, owner12, owner13, owner14, owner15, owner16, owner17, owner18, owner19, owner20,
            owner21, owner22, owner24, owner25, owner26, owner27, owner28, owner29, owner30,
            owner31, owner32, owner33, owner34, owner35, owner36, owner37, owner38, owner39, owner40,
            owner41, owner42, owner43, owner44, owner45;

    @FXML
    private ImageView mule1, mule2, mule3, mule4, mule5, mule6, mule7, mule8, mule9, mule10,
            mule11, mule12, mule13, mule14, mule15, mule16, mule17, mule18, mule19, mule20,
            mule21, mule22, mule23, mule24, mule25, mule26, mule27, mule28, mule29, mule30,
            mule31, mule32, mule33, mule34, mule35, mule36, mule37, mule38, mule39, mule40,
            mule41, mule42, mule43, mule44, mule45;


    @FXML
    private ImageView p1MULEIcon;
    @FXML
    private ImageView p2MULEIcon;
    @FXML
    private ImageView p3MULEIcon;
    @FXML
    private ImageView p4MULEIcon;
    @FXML
    private static ImageView[] muleIcons = new ImageView[4];

    @FXML
    private static Circle[] owners = new Circle[45];
    @FXML
    private static ImageView[] mules = new ImageView[45];
    @FXML
    private static Button[] tiles = new Button[45];
    @FXML
    private static Text[] time = new Text[2];
    @FXML
    private static StackPane[] currentPlayerIndicator = new StackPane[1];
    /*
    @FXML
    private static Text[] orderedPlayerNames = new Text[4];
    @FXML
    private static Text[] p1ScoreInfo = new Text[5];
    @FXML
    private static Text[] p2ScoreInfo = new Text[5];
    @FXML
    private static Text[] p3ScoreInfo = new Text[5];
    @FXML
    private static Text[] p4ScoreInfo = new Text[5];
    */

    private SimpleObservableInteger lastTileClicked = new SimpleObservableInteger();
    private GameController gc;



    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        setUpOwners();
        setUpPText();
        setUpMules();
        //setUpScoreScreen();

        tiles[0] = tileButton23;
        tiles[1] = passTurn;
        tiles[2] = saveButton;
        time[0] = timerText;
        time[1] = roundText;
        muleIcons[0] = p1MULEIcon;
        muleIcons[1] = p2MULEIcon;
        muleIcons[2] = p3MULEIcon;
        muleIcons[3] = p4MULEIcon;
        playerStats[0] = p1Stats;
        playerStats[1] = p2Stats;
        playerStats[2] = p3Stats;
        playerStats[3] = p4Stats;
        currentPlayerIndicator[0] = currentPlayerID;
        storeStats[0] = storeFood;
        storeStats[1] = storeEnergy;
        storeStats[2] = storeOre;
        storeStats[3] = storeCrystite;
        storeStats[4] = storeMULES;
        gc = new GameController();
        lastTileClicked = new SimpleObservableInteger();
        lastTileClicked.addListener(gc);
        final int twoPlayers = 2;
        final int threePlayers = 3;
        if (Controller.getNumOfPlayers() == twoPlayers) {
            p2Stats.setVisible(true);
            p3Stats.setVisible(false);
            p4Stats.setVisible(false);
        } else if (Controller.getNumOfPlayers() == threePlayers) {
            p4Stats.setVisible(false);

        }
    }

    /**
     * populates array of owner circles
     */
    private void setUpOwners() {
        owners[0] = owner1;
        owners[1] = owner2;
        owners[2] = owner3;
        owners[3] = owner4;
        owners[4] = owner5;
        owners[5] = owner6;
        owners[6] = owner7;
        owners[7] = owner8;
        owners[8] = owner9;
        owners[9] = owner10;
        owners[10] = owner11;
        owners[11] = owner12;
        owners[12] = owner13;
        owners[13] = owner14;
        owners[14] = owner15;
        owners[15] = owner16;
        owners[16] = owner17;
        owners[17] = owner18;
        owners[18] = owner19;
        owners[19] = owner20;
        owners[20] = owner21;
        owners[21] = owner22;
        owners[23] = owner24;
        owners[24] = owner25;
        owners[25] = owner26;
        owners[26] = owner27;
        owners[27] = owner28;
        owners[28] = owner29;
        owners[29] = owner30;
        owners[30] = owner31;
        owners[31] = owner32;
        owners[32] = owner33;
        owners[33] = owner34;
        owners[34] = owner35;
        owners[35] = owner36;
        owners[36] = owner37;
        owners[37] = owner38;
        owners[38] = owner39;
        owners[39] = owner40;
        owners[40] = owner41;
        owners[41] = owner42;
        owners[42] = owner43;
        owners[43] = owner44;
        owners[44] = owner45;

    }
    /**
     * populates array of MULE images to be on each tile.
     */
    private void setUpMules() {
        mules[0] = mule1;
        mules[1] = mule2;
        mules[2] = mule3;
        mules[3] = mule4;
        mules[4] = mule5;
        mules[5] = mule6;
        mules[6] = mule7;
        mules[7] = mule8;
        mules[8] = mule9;
        mules[9] = mule10;
        mules[10] = mule11;
        mules[11] = mule12;
        mules[12] = mule13;
        mules[13] = mule14;
        mules[14] = mule15;
        mules[15] = mule16;
        mules[16] = mule17;
        mules[17] = mule18;
        mules[18] = mule19;
        mules[19] = mule20;
        mules[20] = mule21;
        mules[21] = mule22;
        mules[22] = mule23;
        mules[23] = mule24;
        mules[24] = mule25;
        mules[25] = mule26;
        mules[26] = mule27;
        mules[27] = mule28;
        mules[28] = mule29;
        mules[29] = mule30;
        mules[30] = mule31;
        mules[31] = mule32;
        mules[32] = mule33;
        mules[33] = mule34;
        mules[34] = mule35;
        mules[35] = mule36;
        mules[36] = mule37;
        mules[37] = mule38;
        mules[38] = mule39;
        mules[39] = mule40;
        mules[40] = mule41;
        mules[41] = mule42;
        mules[42] = mule43;
        mules[43] = mule44;
        mules[44] = mule45;
    }
    /**
     * populates array of player text fields
     */
    private void setUpPText() {
        //private Text p1Name, p1MoneyText, p1FoodText, p1EnergyText, p1OreText, p1CrystiteText;
        pText[0][0] = p1Name;
        pText[0][1] = p1MoneyText;
        pText[0][2] = p1FoodText;
        pText[0][3] = p1EnergyText;
        pText[0][4] = p1OreText;
        pText[0][5] = p1CrystiteText;
        pText[1][0] = p2Name;
        pText[1][1] = p2MoneyText;
        pText[1][2] = p2FoodText;
        pText[1][3] = p2EnergyText;
        pText[1][4] = p2OreText;
        pText[1][5] = p2CrystiteText;
        pText[2][0] = p3Name;
        pText[2][1] = p3MoneyText;
        pText[2][2] = p3FoodText;
        pText[2][3] = p3EnergyText;
        pText[2][4] = p3OreText;
        pText[2][5] = p3CrystiteText;
        pText[3][0] = p4Name;
        pText[3][1] = p4MoneyText;
        pText[3][2] = p4FoodText;
        pText[3][3] = p4EnergyText;
        pText[3][4] = p4OreText;
        pText[3][5] = p4CrystiteText;
    }

    /**
     * Set up Score Screen Text into arrays
     */
    /*public void setUpScoreScreen() {
        p1ScoreInfo[0] = p1ScoreName;
        p1ScoreInfo[1] = p1MoneyText;
        p1ScoreInfo[2] = p1PlotScore;
        p1ScoreInfo[3] = p1AssetsScore;
        p1ScoreInfo[4] = p1TotalScore;
        p1ScoreInfo[5] = p1MoneyText;

        p2ScoreInfo[0] = p2ScoreName;
        p2ScoreInfo[1] = p2MoneyText;
        p2ScoreInfo[2] = p2PlotScore;
        p2ScoreInfo[3] = p2AssetsScore;
        p2ScoreInfo[4] = p2TotalScore;
        p2ScoreInfo[5] = p2MoneyText;

        p3ScoreInfo[0] = p3ScoreName;
        p3ScoreInfo[1] = p3MoneyText;
        p3ScoreInfo[2] = p3PlotScore;
        p3ScoreInfo[3] = p3AssetsScore;
        p3ScoreInfo[4] = p3TotalScore;
        p3ScoreInfo[5] = p3MoneyText;

        p4ScoreInfo[0] = p4ScoreName;
        p4ScoreInfo[1] = p4MoneyText;
        p4ScoreInfo[2] = p4PlotScore;
        p4ScoreInfo[3] = p4AssetsScore;
        p4ScoreInfo[4] = p4TotalScore;
        p4ScoreInfo[5] = p4MoneyText;

        //orderedPlayerNames[0] = firstPlayer;
        //orderedPlayerNames[1] = secondPlayer;
        //orderedPlayerNames[2] = thirdPlayer;
        //orderedPlayerNames[3] = fourthPlayer;
    }
*/
    public void handleTileClick(ActionEvent event) {
        int buttonNumIndex = 10;
        lastTileClicked.setValue(Integer.parseInt(((Button) event.getTarget()).getId().substring(buttonNumIndex)));
    }

    /**
     * Method to show the Town scene
     */
    public void showTown() {
        townPane.setVisible(true);
        mapPane.setVisible(false);
        pub.setVisible(false);
        storePane.setVisible(false);
    }

    /**
     * Method to show the Map scene when leaving Town scene
     */
    public void showMap() {
        mapPane.setVisible(true);
    }
    /**
     * Method to show the Pub
     */
    public void showPub() {
        townPane.setVisible(false);
        pub.setVisible(true);
    }
    public void showStore() {
        storePane.setVisible(true);
        townPane.setVisible(false);
    }

    public static void setOwner(String color, int tileNumber, boolean visibility) {
        owners[tileNumber - 1].setFill(Paint.valueOf(color));
        owners[tileNumber - 1].setVisible(visibility);
    }
    /**
     * Method to set the visibility of a MULE on a Tile when placed
     */
    public static void setMULE(int tileNumber, double opacity) {
        mules[tileNumber - 1].setVisible(true);
        mules[tileNumber - 1].setOpacity(opacity);
    }
    /**
     * Method to initialize model.Store panel on Map
     */
    public static void updateStorePanel(String food, String energy, String ore, String crystite, String mulesText) {
        storeStats[0].setText(food);
        storeStats[1].setText(energy);
        storeStats[2].setText(ore);
        storeStats[3].setText(crystite);
        storeStats[4].setText(mulesText);
    }

    public static void updateGUIStats(int playerNumber, String name, int money,
                                      int food, int energy, int ore, int crystite, String color) {
        updateGUIStatsHelper(playerNumber, name, Integer.toString(money), Integer.toString(food),
                Integer.toString(energy), Integer.toString(ore), Integer.toString(crystite), color);
    }

    private static void updateGUIStatsHelper(int playerNumber, String name, String money,
                                      String food, String energy, String ore, String crystite, String color) {
        if (name != null) {
            pText[playerNumber][0].setText(name);
        }
        if (money != null) {
            pText[playerNumber][1].setText(money);
        }
        if (food != null) {
            pText[playerNumber][2].setText(food);
        }
        if (energy != null) {
            pText[playerNumber][3].setText(energy);
        }
        if (ore != null) {
            pText[playerNumber][4].setText(ore);
        }
        if (crystite != null) {
            pText[playerNumber][5].setText(crystite);
        }
        playerStats[playerNumber].setStyle("-fx-background-color: " + color + ";");

    }

    public static void enableTown() {
        tiles[0].setDisable(false);
        tiles[1].setDisable(true);
        tiles[2].setDisable(false);
    }

    public void passTurn() {
        gc.passTurn();
    }

    public static void updateTime(int t) {
        time[0].setText(Integer.toString(t));
    }

    public static void updateRound(int r) {
        time[1].setText(Integer.toString(r));
    }
    /**
     * Calls the controllers.GameController's gamble method
     */
    public void gamble() {
        gc.gamble();
        //gambleMoneyEarned.setText("You have earned $" + gc.gamble());
        // --TODO Implement a pause after gamble to see their amount earned.
        //Return to the map
        mapPane.setVisible(true);
    }
    /**
     * Method to set the Current model.Player indicator to each model.Player's Color
     */
    public static void setCurrentPlayerIndicator(String c) {
        currentPlayerIndicator[0].setStyle("-fx-background-color: " + c + ";");
    }

    public void buyFood() {
        gc.buyFood();
        
    }
    public void buyOre() {
        gc.buyOre();
        
    }
    public void buyEnergy() {
        gc.buyEnergy();
        
    }
    public void buyCrystite() {
        gc.buyCrystite();
        
    }
    public void buyFoodMule() {
        gc.buyFoodMule();
        

    }
    public void buyOreMule() {
        gc.buyOreMule();
        
    }
    public void buyEnergyMule() {
        gc.buyEnergyMule();
        
    }
    public void sellFood() {
        gc.sellFood();
        
    }
    public void sellOre() {
        gc.sellOre();
        
    }
    public void sellEnergy() {
        gc.sellEnergy();
        
    }
    public void sellCrystite() {
        gc.sellCrystite();
        gc.sellCrystite(1);
        
    }
    /**
     * Shows that the model.Player has a MULE by turning on model.Player MULE Icon in their stat box.
     */
    public static void setMULEOn(int currentPlayer) {
        if (muleIcons[currentPlayer] != null) {
            muleIcons[currentPlayer].setVisible(true);
        }
    }
    /**
     * Shows that the model.Player does not have a MULE by turning off the model.Player's MULE Icon in their stat box
     */
    public static void setMULEoff(int currentPlayer) {
        muleIcons[currentPlayer].setVisible(false);
    }

    /**
     * Propagate Player Score screen with info
     */
    public static void setScoreScreen(int numOfPlayers) {
        playerRankBP.setVisible(true);
        for (int i = 0; i < numOfPlayers; i++) {

        }
    }
    public static void showScoreScreen() {
        playerRankBP.setVisible(true);
    }
    public void saveGame() {
        gc.saveGame();
    }
}