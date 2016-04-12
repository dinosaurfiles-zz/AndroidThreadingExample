package co.dinosaurfiles.threadingexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int counter = 0 ;
    public void otherButton(View view){
        TextView otherTextView = (TextView) findViewById(
                R.id.otherText
        );

        otherTextView.setText("Clicks: " + (counter++));
    }

    public void buttonClick(View view) {
        firstThread();
        secondThread();
    }

    public void firstThread() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView firstTextView = (TextView) findViewById(
                                    R.id.firstText
                            );
                            int number = Integer.parseInt(firstTextView.getText().toString());
                            firstTextView.setText("" + (number + 1));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        th.start();
    }

    public void secondThread() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView secondTextView = (TextView) findViewById(
                                    R.id.secondText
                            );
                            int number = Integer.parseInt(secondTextView.getText().toString());
                            secondTextView.setText("" + (number + 5));
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        th.start();
    }
}
