import javax.swing.*;
import java.awt.*;
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
    JFrame frame = new JFrame("Dot Game");
    public void init() {
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
    private JPanel createGrid(JLabel turnLabel) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size));

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


    public void makeButton(int r, int c, JPanel panel, JLabel turnLabel) {
        JButton button = new JButton();
        final int[] temp = new int[2];
        temp[0] = r-1;
        temp[1] = c-1;
        button.addActionListener(e -> {
            button.setBackground(Color.GRAY);
            grid[temp[0]][temp[1]] = 1;
            int output = checkForSquares(temp[0], temp[1]);
            if (output == 0) {
                logic.advanceTurn(turnLabel);
            } else {
                for (int i = 0; i <= panelList.size()-1; i++) {
                    if (output == 1 || output == 5) {
                        if (panelList.get(i).getR() == r + 1 && panelList.get(i).getC() == c) {
                            panelList.get(i).setBackground(logic.determineColor());
                        }
                    } else if (output == 2 || output == 8) {
                        if (panelList.get(i).getR() == r - 1 && panelList.get(i).getC() == c) {
                            panelList.get(i).setBackground(logic.determineColor());
                        }
                    } else if (output == 3 || output == 6) {
                        if (panelList.get(i).getR() == r && panelList.get(i).getC() == c-1) {
                            panelList.get(i).setBackground(logic.determineColor());
                        }
                    } else if (output == 4 || output == 7) {
                        if (panelList.get(i).getR() == r && panelList.get(i).getC() == c+1) {
                            panelList.get(i).setBackground(logic.determineColor());
                        }
                    }
                }
            }
        });
        panel.add(button);
    }

    public int checkForSquares(int r, int c) {
        if (r <= 0) {
            //top case
            if (grid[r+1][c-1] == 1 && grid[r+1][c+1] == 1 && grid[r+2][c] == 1) {
                System.out.println("found square! v1");
                return 1;
            }
        } else if (r == grid.length-1) {
            //bottom case
            if (grid[r-1][c-1] == 1 && grid[r-1][c+1] == 1 && grid[r-2][c] == 1) {
                System.out.println("found square v2");
                return 2;
            }
        } else if (c == grid.length-1) {
            //right case
            if (grid[r-1][c-1] == 1 && grid[r+1][c-1] == 1 && grid[r][c-2] == 1) {
                System.out.println("found square v3");
                return 3;
            }
        } else if (c == 0) {
            //left case
            if (grid[r+1][c+1] == 1 && grid[r-1][c+1] == 1 && grid[r][c+2] == 1) {
                System.out.println("found square v4");
                return 4;
            }
        } else {
            //top middle case
            if (grid[r+1][c-1] == 1 && grid[r+1][c+1] == 1 && grid[r+2][c] == 1 && grid[r+1][c] != 3) {
                System.out.println("found square! v5");
                return 5;
                //right case
            } else if (grid[r-1][c-1] == 1 && grid[r+1][c-1] == 1 && grid[r][c-2] == 1 && grid[r][c-1] != 3) {
                System.out.println("fs v6");
                return 6;
                //left case:
            } else if (grid[r+1][c+1] == 1 && grid[r-1][c+1] == 1 && grid[r][c+2] == 1 && grid[r][c+1] != 3) {
                System.out.println("fs v7");
                return 7;
                //bottom case
            } else if (grid[r-1][c-1] == 1 && grid[r-1][c+1] == 1 && grid[r-2][c] == 1 && grid[r-1][c] != 3) {
                System.out.println("fs v8");
                return 8;
            }
        }
        return 0;
    }


    public JLabel addLabelToPane(Container pane) {
        JLabel turnLabel = new JLabel("It is " + players[0].getName() + "'s turn.");
        turnLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        pane.add(turnLabel, BorderLayout.PAGE_END);
        return  turnLabel;
    }
}
