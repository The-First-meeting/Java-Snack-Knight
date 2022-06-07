package util;

import javax.swing.*;

public class Winner {
    public void winner(int score)
    {
        JOptionPane.showMessageDialog(null, "You win the game! Score:"+score, "元气骑士！",JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
