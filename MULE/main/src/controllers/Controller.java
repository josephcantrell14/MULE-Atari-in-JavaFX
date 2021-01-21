package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Player;

import java.io.IOException;
import java.util.logging.Logger;

public class Controller {
    private Logger log = Logger.getLogger(Controller.class.getName());
    @FXML
    private BorderPane mainMenuBorderPane;

    @FXML
    private RadioButton numberOfPlayersRadioButton1;

    @FXML
    private RadioButton numberOfPlayersRadioButton2;

    @FXML
    private Button backButton;

    @FXML
    private Pane mainPane;

    @FXML
    private Pane startPane;

    @FXML
    private AnchorPane playerAnchor1;

    @FXML
    private RadioButton player1RaceFlapper, player1RaceHuman, player1RaceOther;

    @FXML
    private RadioButton player1ColorRed, player1ColorBlue, player1ColorGreen, player1ColorYellow;

    @FXML
    private AnchorPane playerAnchor2;

    @FXML
    private RadioButton player2RaceFlapper, player2RaceHuman, player2RaceOther;
    @FXML
    private RadioButton player2ColorRed, player2ColorBlue, player2ColorGreen, player2ColorYellow;

    @FXML
    private AnchorPane playerAnchor3;

    @FXML
    private RadioButton player3RaceFlapper, player3RaceHuman, player3RaceOther;

    @FXML
    private RadioButton player3ColorRed, player3ColorBlue, player3ColorGreen, player3ColorYellow;

    @FXML
    private AnchorPane playerAnchor4;

    @FXML
    private RadioButton player4RaceFlapper, player4RaceHuman, player4RaceOther;

    @FXML
    private RadioButton player4ColorRed, player4ColorBlue, player4ColorGreen, player4ColorYellow;

    @FXML
    private SplitPane playerConfigPane;

    @FXML
    private Button startGameButton;

    private static final int MAX_NUM_PLAYERS = 4;
    private static int numOfPlayers;
    private static String[] pNames = new String[MAX_NUM_PLAYERS];
    private static Player.Race[] pRaces = new Player.Race[MAX_NUM_PLAYERS];
    private static Player.Color[] pColors = new Player.Color[MAX_NUM_PLAYERS];

    // TODO -- ADD SUPPORT FOR DIFFICULTY. LOOK AT GAME CONTROLLER FOR MORE INFO
    private static GameController.Difficulty gDifficulty;

    /**
     * Display the pane for the specified number of players to input their name, race, and color.
     */
    public void displayPlayerConfig() {
        mainPane.setVisible(false);
        playerConfigPane.setVisible(true);
        if (numberOfPlayersRadioButton1.isSelected()) {
            playerAnchor3.setVisible(false);
            playerAnchor4.setVisible(false);
            numOfPlayers = 2;
        } else if (numberOfPlayersRadioButton2.isSelected()) {
            playerAnchor4.setVisible(false);
            numOfPlayers = MAX_NUM_PLAYERS - 1;
        } else {
            numOfPlayers = MAX_NUM_PLAYERS;
        }
    }

    /**
     * Load and shows the Map screen of the MULE game.
     */
    public void startGame() {
        try {
            setPlayerStats();
            Parent gameScreen = FXMLLoader.load(getClass().getResource("/gui/Map.fxml"));
            Stage stageGameScreen = (Stage) startGameButton.getScene().getWindow();
            stageGameScreen.setScene(new Scene(gameScreen));
            stageGameScreen.show();
        } catch (IOException e) {
            log.info("Map.fxml does not exist where it should.");
        }
    }

    /**
     * Returns to the StartMenu screen if the backbutton is pressed on the config screen.
     */
    public void displayStartScreen() {
        try {
            Parent start = FXMLLoader.load(getClass().getResource("/gui/StartMenu.fxml"));
            Stage stageStart = (Stage) backButton.getScene().getWindow();
            stageStart.setScene(new Scene(start));
            stageStart.show();
        } catch (IOException e) {
            log.info("StartMenu.fxml does not exist where it should.");
        }
    }

    /**
     * Load and show the Start Menu after the SplashScreen.
     * @throws IOException
     */
    public void displayStartScreenMainMenu() throws IOException {
        try {
            Parent start = FXMLLoader.load(getClass().getResource("/gui/StartMenu.fxml"));
            Stage stageStart = (Stage) mainMenuBorderPane.getScene().getWindow();
            stageStart.setScene(new Scene(start));
            stageStart.show();
        } catch (IOException e) {
            log.info("StartMenu.fxml does not exist where it should.");
        }
    }

    /**
     * Load and shows the New Game Screen
     * @throws IOException
     */
    public void newGameDisplay() throws IOException {
        try {
            Parent start = FXMLLoader.load(getClass().getResource("/gui/configScreen.fxml"));
            Stage stageStart = (Stage) startPane.getScene().getWindow();
            stageStart.setScene(new Scene(start));
            stageStart.show();
        } catch (IOException e) {
            log.info("configScreen.fxml does not exist where it should.");
        }
    }

    /**
     * Load and show the Continue Game screen
     * @throws IOException
     */
    public void contGameDisplay() throws IOException {
        try {
            Parent start = FXMLLoader.load(getClass().getResource("/gui/Splash.fxml"));
            Stage stageStart = (Stage) startPane.getScene().getWindow();
            stageStart.setScene(new Scene(start));
            stageStart.show();
        } catch (IOException e) {
            log.info("Splash.fxml does not exist where it should.");
        }

        //TODO -- Implement Continue Screen
    }
    //TODO -- Implement Options Screen
    //TODO -- Implement Credits Screen
    //TODO -- Add Exit Option

    /**
     * Method to set player names, race, and color based on the config screen;
     * Sets variables pNames,pRaces,pColors.
     */
    public void setPlayerStats() {
        AnchorPane[] playerPanes = {playerAnchor1, playerAnchor2, playerAnchor3, playerAnchor4};
        for (int i = 0; i< getNumOfPlayers(); i ++) {
            //Iterate through each node in each player's pane
            //First, the names
            for (Node node : playerPanes[i].getChildren()) {
                if (node instanceof TextField) {
                    pNames[i] = ((TextField)node).getText();
                } else if (node instanceof RadioButton) {
                    //Next, the race
                    switch (i) {
                        case 0:
                            setPlayer1Race(node);
                            setPlayer1Color(node);
                            break;
                        case 1:
                            setPlayer2Race(node);
                            setPlayer2Color(node);
                            break;
                        case 2:
                            setPlayer3Race(node);
                            setPlayer3Color(node);
                            break;
                        default:
                            setPlayer4Race(node);
                            setPlayer4Color(node);
                            break;
                    }
                }
            }
        }
    }
    public void setPlayer1Race(Node node) {
        if (node.equals(player1RaceFlapper) && player1RaceFlapper.isSelected()) {
            pRaces[0] = Player.Race.FLAPPER;
        } else if (node.equals(player1RaceHuman) && player1RaceHuman.isSelected()) {
            pRaces[0] = Player.Race.HUMAN;
        } else if(node.equals(player1RaceOther) && player1RaceOther.isSelected()) {
            pRaces[0] = Player.Race.OTHERS;
        }
    }
    public void setPlayer1Color(Node node) {
        if (node.equals(player1ColorRed) && player1ColorRed.isSelected()) {
            pColors[0] = Player.Color.RED;
        } else if (node.equals(player1ColorBlue) && player1ColorBlue.isSelected()) {
            pColors[0] = Player.Color.BLUE;
        } else if (node.equals(player1ColorGreen) && player1ColorGreen.isSelected()) {
            pColors[0] = Player.Color.GREEN;
        } else if (node.equals(player1ColorYellow) && player1ColorYellow.isSelected()) {
            pColors[0] = Player.Color.YELLOW;
        }
    }
    public void setPlayer2Race(Node node) {
        if (node.equals(player2RaceFlapper) && player2RaceFlapper.isSelected()) {
            pRaces[1] = Player.Race.FLAPPER;
        } else if (node.equals(player2RaceHuman) && player2RaceHuman.isSelected()) {
            pRaces[1] = Player.Race.HUMAN;
        } else if (node.equals(player2RaceOther) && player2RaceOther.isSelected()) {
            pRaces[1] = Player.Race.OTHERS;
        }
    }
    public void setPlayer2Color(Node node) {
        if (node.equals(player2ColorRed) && player2ColorRed.isSelected()) {
            pColors[1] = Player.Color.RED;
        } else if (node.equals(player2ColorBlue) && player2ColorBlue.isSelected()) {
            pColors[1] = Player.Color.BLUE;
        } else if (node.equals(player2ColorGreen) && player2ColorGreen.isSelected()) {
            pColors[1] = Player.Color.GREEN;
        } else if (node.equals(player2ColorYellow) && player2ColorYellow.isSelected()) {
            pColors[1] = Player.Color.YELLOW;
        }
    }
    public void setPlayer3Race(Node node) {
        if (node.equals(player3RaceFlapper) && player3RaceFlapper.isSelected()) {
            pRaces[2] = Player.Race.FLAPPER;
        } else if (node.equals(player3RaceHuman) && player3RaceHuman.isSelected()) {
            pRaces[2] = Player.Race.HUMAN;
        } else if(node.equals(player3RaceOther) && player3RaceOther.isSelected()) {
            pRaces[2] = Player.Race.OTHERS;
        }
    }
    public void setPlayer3Color(Node node) {
        if (node.equals(player3ColorRed) && player3ColorRed.isSelected()) {
            pColors[2] = Player.Color.RED;
        } else if (node.equals(player3ColorBlue) && player3ColorBlue.isSelected()) {
            pColors[2] = Player.Color.BLUE;
        } else if (node.equals(player3ColorGreen) && player3ColorGreen.isSelected()) {
            pColors[2] = Player.Color.GREEN;
        } else if (node.equals(player3ColorYellow) && player3ColorYellow.isSelected()) {
            pColors[2] = Player.Color.YELLOW;
        }
    }
    public void setPlayer4Race(Node node) {
        if (node.equals(player4RaceFlapper) && player4RaceFlapper.isSelected()) {
            pRaces[MAX_NUM_PLAYERS - 1] = Player.Race.FLAPPER;
        } else if (node.equals(player4RaceHuman) && player4RaceHuman.isSelected()) {
            pRaces[MAX_NUM_PLAYERS - 1] = Player.Race.HUMAN;
        } else if(node.equals(player4RaceOther) && player4RaceOther.isSelected()) {
            pRaces[MAX_NUM_PLAYERS - 1] = Player.Race.OTHERS;
        }
    }
    public void setPlayer4Color(Node node) {
        if (node.equals(player4ColorRed) && player4ColorRed.isSelected()) {
            pColors[MAX_NUM_PLAYERS - 1] = Player.Color.RED;
        } else if (node.equals(player4ColorBlue) && player4ColorBlue.isSelected()) {
            pColors[MAX_NUM_PLAYERS - 1] = Player.Color.BLUE;
        } else if (node.equals(player4ColorGreen) && player4ColorGreen.isSelected()) {
            pColors[MAX_NUM_PLAYERS - 1] = Player.Color.GREEN;
        } else if (node.equals(player4ColorYellow) && player4ColorYellow.isSelected()) {
            pColors[MAX_NUM_PLAYERS - 1] = Player.Color.YELLOW;
        }
    }
    /**
     * Method to return the player's names
     */
    public static String[] getpNames() {
        return pNames.clone();
    }
    /**
     * Method to return player's races
     */
    public static Player.Race[] getpRaces() {
        return pRaces.clone();
    }
    /**
     * Method to return player's colors
     */
    public static Player.Color[] getpColors() {
        return pColors.clone();
    }

    public static GameController.Difficulty getgDifficulty() {
        return gDifficulty;
    }
    /**
     * Method to return the number of players from the config screen.
     *
     */
    public static int getNumOfPlayers() {
        return numOfPlayers;
    }

}
