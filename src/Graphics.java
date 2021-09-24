import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;


public class Graphics extends JFrame implements ActionListener{

    private static final int ballsPerRow = 4;
    private static final int round = 9;

    private int width = 900;
    private int height = 1000;

    protected JLabel[] rowComputer = new JLabel[ballsPerRow];
    protected JPanel panelCPU = new JPanel();

    protected JButton[][] rowUser = new JButton[round - 1][ballsPerRow];
    protected JPanel panelUser = new JPanel();
    protected JPanel panelUserInput = new JPanel();

    protected JLabel[][] holeRow = new JLabel[round][ballsPerRow];
    protected JPanel panelEmptyRow = new JPanel();

    protected JLabel[][] checkRow = new JLabel[round][ballsPerRow];
    protected JPanel panelAllCheckRow = new JPanel();

    protected JPanel[] unionCheckAndTable = new JPanel[round];

    protected JPanel finalPanel = new JPanel();

    protected JButton newGame = new JButton("New Game");
    protected JButton check = new JButton("Check");

    protected JTextArea output= new JTextArea(3,20);
    protected JPanel textAreaPanel = new JPanel();

    protected Table game = new Table();
    protected Computer enemy = new Computer();
    protected Player user = new Player();

    Graphics(){
        enemy.drawBalls();
        output.setEditable(false);

        this.add(finalPanel);
        finalPanel.setLayout(new BoxLayout(finalPanel,BoxLayout.Y_AXIS));
        panelCPU.setLayout(new FlowLayout());
        panelUser.setLayout(new GridLayout(round - 1,ballsPerRow));
        panelEmptyRow.setLayout(new BoxLayout(panelEmptyRow,BoxLayout.Y_AXIS));
        panelUserInput.setLayout(new FlowLayout());
        panelAllCheckRow.setLayout(new BoxLayout(panelAllCheckRow,BoxLayout.Y_AXIS));
        textAreaPanel.setLayout(new FlowLayout());

        for (int i = 0; i < round; i++) {
            unionCheckAndTable[i] = new JPanel();
            unionCheckAndTable[i].setLayout(new FlowLayout());
        }

        setSize(width,height);

        textAreaPanel.add(output);
        finalPanel.add(textAreaPanel);

        for(int i = 0; i < ballsPerRow; i++) {
            rowComputer[i] = new JLabel(new ImageIcon("img/mistero.gif"));
            panelCPU.add(rowComputer[i]);
        }

        finalPanel.add(panelCPU, BorderLayout.NORTH);

        for (int i = 0; i < round; i++) {
            JPanel tempHole = new JPanel();
            JPanel tempCheck = new JPanel();
            tempHole.setLayout(new FlowLayout());
            tempCheck.setLayout(new GridLayout(2,2));
            for (int j = 0; j < ballsPerRow; j++) {
                holeRow[i][j] = new JLabel(new ImageIcon("img/empty.jpg"));
                checkRow[i][j] = new JLabel(new ImageIcon(new ImageIcon("img/empty.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                tempHole.add(holeRow[i][j]);
                tempCheck.add(checkRow[i][j]);
            }
            //panelEmptyRow.add(tempHole,BorderLayout.CENTER);
            unionCheckAndTable[i].add(tempHole);
            unionCheckAndTable[i].add(tempCheck);
        }

        for (int i = 0; i < round; i++) {
            finalPanel.add(unionCheckAndTable[i],BorderLayout.NORTH);
        }


        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("...");
                enemy.drawBalls();
                user.resetHand();
                enemy.resetHand();
                game.resetTable();
                for(int i = 0; i < ballsPerRow; i++)
                    rowComputer[i].setIcon(new ImageIcon("img/mistero.gif"));

                for (int i = 0; i < round; i++) {
                    for (int j = 0; j < ballsPerRow; j++) {
                        holeRow[i][j].setIcon(new ImageIcon("img/empty.jpg"));
                        checkRow[i][j].setIcon(new ImageIcon(new ImageIcon("img/empty.jpg").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT)));
                    }
                }
            }


        });

        panelUserInput.add(newGame);

        for (int i = 0,count = 1; i < round - 1; i++,count++) {
            for (int j = 0; j < ballsPerRow; j++) {
                rowUser[i][j] = new JButton(new ImageIcon("img/"+ count +".gif"));
                panelUser.add(rowUser[i][j]);
            }
        }


        panelUserInput.add(panelUser);


        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("...");
                ArrayList<Integer> checkRowNum = game.compareHands(user, enemy);
                if (user.getLen() == 4) {
                    int round = game.getRoundGame();
                    if (round >= 0) {
                        user.resetHand();
                        for (int i = 0; i < 2; i++)
                            for (int j = 0; j < checkRowNum.get(i); j++)
                                checkRow[round][j].setIcon(new ImageIcon("img/-" + i + ".gif"));
                    }

                    if (round <= 0 || checkRowNum.get(1) == 4) {
                        check.setEnabled(false);
                        for (JButton[] row : rowUser)
                            for (JButton button : row)
                                button.setEnabled(false);

                        ArrayList<Integer> hand = enemy.getHand();
                        for (int i = 0; i < ballsPerRow; i++) {
                            rowComputer[i].setIcon(new ImageIcon("img/" + hand.get(i) + ".gif"));
                        }

                        user.resetHand();
                        if (checkRowNum.get(1) == 4)
                            output.setText("Hai vinto!!!");
                        else
                            output.setText("Ha vinto il Computer!!!!");
                    }
                    game.nextRound();
                }else{
                    output.setText("Devi inserire 4 palline!!!!");
                }
            }
        });

        panelUserInput.add(check);

        finalPanel.add(panelUserInput,BorderLayout.SOUTH);

        for (int i = 0; i < round - 1; i++)
            for (int j = 0; j < ballsPerRow; j++) {
                int finalJ = j;
                int finalI = i;
                rowUser[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(!(user.getHand().contains(finalI + 1))) {
                            game.setTable(finalJ, Integer.parseInt(String.valueOf(finalI)));
                            holeRow[game.getRoundGame()][finalJ].setIcon(new ImageIcon("img/" + (finalI + 1) + ".gif"));
                            user.setHand(finalJ, finalI + 1);
                        }else{
                            output.setText("Non mettere palline dello stesso colore più di 1 volta!!");
                        }
                    }
                });
            }
    }


    public void actionPerformed(ActionEvent e) {

    }
}