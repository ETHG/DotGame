import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private int size;
    public int playerCount;
    private Player[] players;
    public int[][] grid;
    private Logic logic;
    private ArrayList<Panel> panelList;

    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
        this.logic = new Logic(this.players);
        this.grid =  new int[size-1][size-1];
        this.panelList = new ArrayList<>();
    }

    //Populates the array of players with player objects
    public void initPlayers() {
        for (int i = 0; i < playerCount; i++) {
            String player = JOptionPane.showInputDialog("What is the name of player " + (1+i) + "? ");
            players[i] = new Player(player, i);
        }
    }

    //Creates the frame;
    JFrame frame;
    public void init() {
        frame = new JFrame("Dot Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        //To be used later to show what players turn it is.
        JLabel turnLabel = addLabelToPane(frame.getContentPane());
        //Populates grid based on method below
        frame.add(createGrid(turnLabel));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //sets size and location.
        frame.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    //Populates the frame w/ panels/etc
    private JPanel createGrid(JLabel turnLabel)  {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size));

        //Creates black corner peices, middle peices, and clickable butons in the correct order
        for (int r = 1; r < size; r++) {
            for (int c = 1; c < size; c++) {
                if (r%2 == 1) {
                    if (c%2 == 0) {
                        makeButton(r, c, panel, turnLabel);
                    } else {
                        JPanel newPanel = new JPanel();
                        panel.add(newPanel);
                        newPanel.setBackground(Color.BLACK);
                        if (r <= grid.length-1 && c <= grid.length-1) {
                            grid[r][c] = 2;
                        }
                    }
                } else {
                    if (c%2 == 1) {
                        makeButton(r, c, panel, turnLabel);
                    } else {
                        Panel newCustomPanel = new Panel(r,c);
                        panel.add(newCustomPanel);
                        panelList.add(newCustomPanel);

                        grid[r][c] = 3;
                    }
                }
            }
        }
        return panel;
    }

    //Makes things happen on button/pannel press
    public void makeButton(int r, int c, JPanel panel, JLabel turnLabel) {
        JButton button = new JButton();
        final int[] temp = new int[2];
        //Below variables because static r and c cant be used in the lambda.
        temp[0] = r-1;
        temp[1] = c-1;
        button.addActionListener(e -> {
            if (grid[temp[0]][temp[1]] != 0) {
                //Does nothing bc if its presed when its already been pressed ignore
            } else {
                //Sets color to gray so people can see, sets array number to one so computer can determine.
                button.setBackground(Color.GRAY);
                grid[temp[0]][temp[1]] = 1;
                //Creates array and then checks for array to determine when to fill a color, and change the array.
                ArrayList<Integer> output = checkForSquares(temp[0], temp[1]);
                if (output.size() == 0) {
                    logic.advanceTurn(turnLabel);
                } else {
                    for (int i = 0; i <= panelList.size()-1; i++) {
                        if (output.contains(1) || output.contains(5)) {
                            if (panelList.get(i).getR() == r + 1 && panelList.get(i).getC() == c) {
                                panelList.get(i).setBackground(logic.determineColor());
                            }
                        }
                        if (output.contains(2) || output.contains(8)) {
                            if (panelList.get(i).getR() == r - 1 && panelList.get(i).getC() == c) {
                                panelList.get(i).setBackground(logic.determineColor());
                            }
                        }
                        if (output.contains(3) || output.contains(6)) {
                            if (panelList.get(i).getR() == r && panelList.get(i).getC() == c-1) {
                                panelList.get(i).setBackground(logic.determineColor());
                            }
                        }
                        if (output.contains(4) || output.contains(7)) {
                            if (panelList.get(i).getR() == r && panelList.get(i).getC() == c+1) {
                                panelList.get(i).setBackground(logic.determineColor());
                            }
                        }
                    }
                    players[logic.getTurn()%playerCount].addPoint();
                }
                //Checks to see if all locations on the board have been clicked, if they had runs endgame method.
                if (isEndGame()) {
                    endGame();
                }
            }
        });
        panel.add(button);
    }

    //Checks for complete squares on button press
    public ArrayList<Integer> checkForSquares(int r, int c) {
        ArrayList<Integer> squaresFound = new ArrayList<>();
        if (r <= 0) {
            //top case
            if (grid[r+1][c-1] == 1 && grid[r+1][c+1] == 1 && grid[r+2][c] == 1) {
                squaresFound.add(1);
            }
        } else if (r == grid.length-1) {
            //bottom case
            if (grid[r-1][c-1] == 1 && grid[r-1][c+1] == 1 && grid[r-2][c] == 1) {
                squaresFound.add(2);
            }
        } else if (c == grid.length-1) {
            //right case
            if (grid[r-1][c-1] == 1 && grid[r+1][c-1] == 1 && grid[r][c-2] == 1) {
                squaresFound.add(3);
            }
        } else if (c == 0) {
            //left case
            if (grid[r + 1][c + 1] == 1 && grid[r - 1][c + 1] == 1 && grid[r][c + 2] == 1) {
                squaresFound.add(4);
            }
        } else {
            if (r % 2 == 0) {
                if (grid[r + 1][c - 1] == 1 && grid[r + 1][c + 1] == 1 && grid[r + 2][c] == 1 && grid[r + 1][c] != 3) {
                    squaresFound.add(5);
                }
                if (grid[r - 1][c - 1] == 1 && grid[r - 1][c + 1] == 1 && grid[r - 2][c] == 1 && grid[r - 1][c] != 3) {
                    squaresFound.add(8);
                }
            } else {
                if (grid[r - 1][c - 1] == 1 && grid[r + 1][c - 1] == 1 && grid[r][c - 2] == 1 && grid[r][c - 1] != 3) {
                    squaresFound.add(6);
                }

                if (grid[r + 1][c + 1] == 1 && grid[r - 1][c + 1] == 1 && grid[r][c + 2] == 1 && grid[r][c + 1] != 3) {
                    squaresFound.add(7);
                }
            }
        }
        return squaresFound;
    }

    //Shows what turn it is
    public JLabel addLabelToPane(Container pane) {
        JLabel turnLabel = new JLabel("It is " + players[0].getName() + "'s turn.");
        turnLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        pane.add(turnLabel, BorderLayout.PAGE_END);
        return  turnLabel;
    }

    //Checks if all squares have been pressed, so shouuld check to see who won
    public boolean isEndGame() {
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] == 0)
                    if (!((r%2==0 && c==0) || (c%2==0 && r==0)))
                        return false;
            }
        return true;
    }

    //Ends the game, tallys points, etc.
    public void endGame() {
        //Checks which player had the most point
        Player max = new Player("random", 100);
        for (int i = 0; i < playerCount; i++) {
            if (max.getPoints() < players[i].getPoints()) {
                max = players[i];
            } else if (max.getPoints() == players[i].getPoints())
                max = new Player("tie", 1000);
        }
        //Displays and a text book which player had the most points
        if (max.getId() == 1000) {
            JOptionPane.showMessageDialog(null, "It's a tie!","Message", JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "Player " + max.getName() + " wins with " + max.getPoints() + " points!","Message", JOptionPane.INFORMATION_MESSAGE);
        //Prompts user asking if they want to play again
        int reply = JOptionPane.showConfirmDialog(null, "Want to play a gain? ", "Play again?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
            try {
                String[] arguments = new String[] {"123"};
                DotGame.main(arguments);
            } catch (UnsupportedLookAndFeelException e) {
            } catch (ClassNotFoundException e) {
            } catch (InstantiationException e) {
            } catch (IllegalAccessException e) {
            }
        } else {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
