package model;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import view.HomePage;
import view.Results;

public class CountdownTimer {
    private static int COUNTDOWN_TIME = 90 * 60 * 1000; // 90 minutes in milliseconds
    private static final int TIMER_DELAY = 1000; // 1 second

    private JLabel label;
    private Timer timer;
    private String remainingTime;
    private String usedTime;
    int second = 0;

    public CountdownTimer(int idStudent, String idTest,JLabel label, String[] mark) {
        this.label = label;
        this.timer = new Timer(TIMER_DELAY, e -> {
        	second += TIMER_DELAY;
            COUNTDOWN_TIME -= TIMER_DELAY;
            int minutes = COUNTDOWN_TIME / (60 * 1000);
            int seconds = (COUNTDOWN_TIME / 1000) % 60;
            if (seconds < 10) remainingTime = minutes + ":0" + seconds;
            else remainingTime = minutes + ":" + seconds;
            label.setText(remainingTime);
            usedTime = second / (60 * 1000) + ":" + (second / 1000) % 60;
            if (remainingTime.equals("0:00")) {
            	HomePage p = new HomePage(idStudent, "");
    			p.setVisible(true);
    			p.setContentPane(new Results(idStudent,idTest, mark, usedTime));
    			this.stop();
            }
        });
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
    
    public String getRemainingTime() {
    	return this.remainingTime;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Countdown Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("90:00");
        frame.add(label);
        String []mark = new String[1];
        mark[0] = "1";

        CountdownTimer timer = new CountdownTimer(1, "", label, mark );
        timer.start();

        frame.pack();
        frame.setVisible(true);
    }

	public JLabel getLabel() {
		return label;
	}

	public String getUsedTime() {
		return usedTime;
	}
}