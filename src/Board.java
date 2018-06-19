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
    //private JPanel[] panelList;


    public Board(int size, int players) {
        this.playerCount = players;
        this.size = size;
        this.players = new Player[players];
        this.logic = new Logic(this.players);
        this.grid =  new int[size-1][size-1];
        this.panelList = new ArrayList<>();
        //this.panelList = new JPanel[size];
    }

    //Default constructor if size/players are left blank
    public Board() {
        playerCount = 2;
        size = 10;
        players = new Player[playerCount];
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
        frame  = new JFrame("Dot Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        JLabel turnLabel = addLabelToPane(frame.getContentPane());
        frame.add(createGrid(turnLabel));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }

    //Populates the frame w/ panels/etc
    private JPanel createGrid(JLabel turnLabel)  {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size));

        for (int r = 1; r < size; r++) {
            for (int c = 1; c < size; c++) {
                if (r%2 == 1) {
//                    if ((r == 1 && (c == 1 || c == 3 || c ==5)) || (c == 1 && (r == 1 || r == 3 || r == 5)))
//                        grid[r-1][c-1] = 3;
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
                        //JPanel newPanel = new JPanel();
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


    public void makeButton(int r, int c, JPanel panel, JLabel turnLabel) {
        JButton button = new JButton();
        final int[] temp = new int[2];
        temp[0] = r-1;
        temp[1] = c-1;
        button.addActionListener(e -> {
            if (grid[temp[0]][temp[1]] != 0) {
                //Does nothing bc if its presed when its already been pressed ignore
            } else {
                button.setBackground(Color.GRAY);
                grid[temp[0]][temp[1]] = 1;
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
                System.out.println("checking");
                if (isEndGame()) {
                    System.out.println("check true");
                    endGame();
                }
//            for (int a = 0; a < grid.length; a++)
//                System.out.println( Arrays.toString(grid[a]));
//            System.out.println("");
            }
        });
        panel.add(button);
    }

    public ArrayList<Integer> checkForSquares(int r, int c) {
        ArrayList<Integer> squaresFound = new ArrayList<>();
        if (r <= 0) {
            //top case
            if (grid[r+1][c-1] == 1 && grid[r+1][c+1] == 1 && grid[r+2][c] == 1) {
                System.out.println("found square! v1");
                squaresFound.add(1);
                //return 1;
            }
        } else if (r == grid.length-1) {
            //bottom case
            if (grid[r-1][c-1] == 1 && grid[r-1][c+1] == 1 && grid[r-2][c] == 1) {
                System.out.println("found square v2");
                squaresFound.add(2);
                //return 2;
            }
        } else if (c == grid.length-1) {
            //right case
            if (grid[r-1][c-1] == 1 && grid[r+1][c-1] == 1 && grid[r][c-2] == 1) {
                System.out.println("found square v3");
                squaresFound.add(3);
                //return 3;
            }
        } else if (c == 0) {
            //left case
            if (grid[r + 1][c + 1] == 1 && grid[r - 1][c + 1] == 1 && grid[r][c + 2] == 1) {
                System.out.println("found square v4");
                squaresFound.add(4);
                //return 4;
            }
        } else {
            if (r % 2 == 0) {
                if (grid[r + 1][c - 1] == 1 && grid[r + 1][c + 1] == 1 && grid[r + 2][c] == 1 && grid[r + 1][c] != 3) {
                    System.out.println("found square! v5");
                    squaresFound.add(5);
                    //return 5;
                }
                if (grid[r - 1][c - 1] == 1 && grid[r - 1][c + 1] == 1 && grid[r - 2][c] == 1 && grid[r - 1][c] != 3) {
                    System.out.println("fs v8");
                    squaresFound.add(8);
                    //return 8;
                }
            } else {
                if (grid[r - 1][c - 1] == 1 && grid[r + 1][c - 1] == 1 && grid[r][c - 2] == 1 && grid[r][c - 1] != 3) {
                    System.out.println("fs v6");
                    squaresFound.add(6);
                    //return 6;
                }

                if (grid[r + 1][c + 1] == 1 && grid[r - 1][c + 1] == 1 && grid[r][c + 2] == 1 && grid[r][c + 1] != 3) {
                    System.out.println("fs v7");
                    squaresFound.add(7);
                    //return 7;
                }
            }
        }
        return squaresFound;
    }

    public JLabel addLabelToPane(Container pane) {
        JLabel turnLabel = new JLabel("It is " + players[0].getName() + "'s turn.");
        turnLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        pane.add(turnLabel, BorderLayout.PAGE_END);
        return  turnLabel;
    }

    public boolean isEndGame() {
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid.length; c++) {
                if (grid[r][c] == 0)
                    if (!((r%2==0 && c==0) || (c%2==0 && r==0)))
                        return false;
            }
        return true;
    }

    public void endGame() {
        Player max = new Player("random", 100);
        for (int i = 0; i < playerCount; i++) {
            if (max.getPoints() < players[i].getPoints()) {
                max = players[i];
                //System.out.println("" + players[i].getName());
            } else if (max.getPoints() == players[i].getPoints()) //Todo tie dosent work quite right bc its not evaluated on completeion
                max = new Player("tie", 1000);
        }
        if (max.getId() == 1000) {
            JOptionPane.showMessageDialog(null, "It's a tie!","Message", JOptionPane.INFORMATION_MESSAGE);
        } else
            JOptionPane.showMessageDialog(null, "Player " + max.getName() + " wins with " + max.getPoints() + " points!","Message", JOptionPane.INFORMATION_MESSAGE);
        int reply = JOptionPane.showConfirmDialog(null, "Want to play a gain? ", "Play again?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION) {
//            String[] arguments = new String[] {"123"};
//            DotGame.main(arguments);  todo handel exceptions thrown by this
        } else {
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
