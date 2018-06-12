import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


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
        frame.add(createGrid());
        addLabelToPane(frame.getContentPane());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700, 700);
    }

    //Populates the frame w/ panels/etc
    private JPanel createGrid() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(size, size));

        for (int r = 1; r < size; r++) {
            for (int c = 1; c < size; c++) {
                if (r%2 == 1) {
                    if (c%2 == 0) {
                        makeButton(r, c, panel);
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
                        makeButton(r, c, panel);
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


    public void makeButton(int r, int c, JPanel panel) {
        JButton button = new JButton();
        final int[] temp = new int[2];
        temp[0] = r-1;
        temp[1] = c-1;
        button.addActionListener(e -> {
            addLabelToPane(frame.getContentPane());
            button.setBackground(Color.GRAY);
            grid[temp[0]][temp[1]] = 1;
            int output = checkForSquares(temp[0], temp[1]);
            if (output == 0) {
                logic.advanceTurn();
            } else {
                for (int i = 0; i <= panelList.size()-1; i++) {
                    if (output == 1 || output == 5 && (panelList.get(i).getR() == r + 1 && panelList.get(i).getC() == c)) {
                        panelList.get(i).setBackground(logic.determineColor());
                        //panelList.get(i).add(createLabel());
                        //panel.add(createLabel());
                    } else if (output == 2 || output == 8 && (panelList.get(i).getR() == r - 1 && panelList.get(i).getC() == c)) {
                        panelList.get(i).setBackground(logic.determineColor());
                        //panelList.get(i).add(createLabel());
                        //panel.add(createLabel());
                    } else if (output == 3 || output == 6 && (panelList.get(i).getR() == r && panelList.get(i).getC() == c-1)) {
                        panelList.get(i).setBackground(logic.determineColor());
                        //panelList.get(i).add(createLabel());
                        //panel.add(createLabel());
                    } else if (output == 4 || output == 7 && (panelList.get(i).getR() == r && panelList.get(i).getC() == c+1)) {
                        panelList.get(i).setBackground(logic.determineColor());
                        //panelList.get(i).add(createLabel());
                        //panel.add(createLabel());
                    }
                }
            }
        });
        panel.add(button);
    }

//    public JTextArea createLabel() {
//        //JLabel label = new JLabel();
//        JTextArea textArea = new JTextArea();
//        textArea.setOpaque(false);
//
//        for (int j = 0; j < players.length; j++) {
//            if (logic.getTurn()%players.length == players[j].getId()) {
//                //label.setText("" + players[j].getName().charAt(0));
//                textArea.setText("" + players[j].getName().charAt(0));
//            }
//
//        }
//        return textArea;
//        //return label;
 //   }

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


    public void addLabelToPane(Container pane) {
        String name = "";
        for (int i = 0; i < playerCount; i++) {
            if (logic.getTurn()%playerCount == players[i].getId()) {
                name = players[i].getName();
            }
        }
        JLabel turnLabel = new JLabel("Turn: " + name);
        pane.add(turnLabel, BorderLayout.PAGE_END);
    }
}
