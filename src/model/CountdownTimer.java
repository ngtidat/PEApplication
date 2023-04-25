package model;

import javax.swing.JLabel;
import javax.swing.Timer;

import view.HomePage;
import view.Results;

public class CountdownTimer {
    private static int COUNTDOWN_TIME ; // 90 minutes in milliseconds
    private final int TIMER_DELAY = 1000; // 1 second

    private JLabel label;
    private Timer timer;
    private String remainingTime;
    private static String usedTime;
    int second = 0;

    public CountdownTimer(JLabel label, int defaultTime) {
    	CountdownTimer.setCOUNTDOWN_TIME(defaultTime);
    	
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
            	HomePage p = new HomePage();
    			p.setVisible(true);
    			p.setContentPane(new Results());
    			p.setSize(900,540);
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

	public JLabel getLabel() {
		return label;
	}

	public static String getUsedTime() {
		return usedTime;
	}

	public static int getCOUNTDOWN_TIME() {
		return COUNTDOWN_TIME;
	}

	public static void setCOUNTDOWN_TIME(int cOUNTDOWN_TIME) {
		COUNTDOWN_TIME = cOUNTDOWN_TIME * 60 * 1000;
	}

}