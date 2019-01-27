package com.georgia.firstaid.ui;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.georgia.firstaid.R;
import com.georgia.firstaid.model.DatabaseHelper;
import com.georgia.firstaid.model.Page;
import com.georgia.firstaid.model.Protocol;

import java.util.Stack;

public class FirstAidActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    //Sets up custom back button
    private Stack<Integer> pageStack = new Stack<Integer>();
    //Set up views
    private Protocol protocol;
    private TextView promptText;
    private Button choice1Button;
    private Button choice2Button;

    private int nextPage;
    private int currentPage;
    private boolean responsive = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_aid);
        //Initialise Views
        promptText = (TextView) findViewById(R.id.promptText);
        choice1Button = (Button) findViewById(R.id.stopButton);
        choice2Button = (Button) findViewById(R.id.playButton);
        Button skipButton = (Button) findViewById(R.id.skipButton);

        skipButton.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  loadPage(currentPage+1);
              }
        });

        Intent intent = getIntent();

        protocol = new Protocol();
        loadPage(0);

    }

    private void loadPage(int pageNumber) {
        //Add page number to stack (custom back button)
        pageStack.push(pageNumber);

        currentPage = pageNumber;
        final Page page = protocol.getPage(pageNumber);

        String pageText = getString(page.getTextId());
        promptText.setText(pageText);


        //If only one button
        if (page.isSingleButton() && pageNumber != 7) {
            singlePageUI();
            choice2Button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View view){
                    startTimer();
                }
                });
        } else if (page.isSingleButton()) {
            singlePageUI();
            headCheckPageUI(page);
        } else { //else set up page as normal
            loadButtons(page);
        }
    }

    private void loadButtons(final Page page) {
        choice1Button.setVisibility(View.VISIBLE);
        choice1Button.setText(page.getChoice1().getTextId());
        //When click choice1Button get next page and load page
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (page.getChoice1().getNextPage() == 4) {
                    responsive = true;
                }
                nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setVisibility(View.VISIBLE);
        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }

    private void startTimer() {
        Intent intent = new Intent(this, TimerActivity.class);
        intent.putExtra("isResponsive", responsive);
        startActivityForResult(intent, 1);
    }

    private void singlePageUI() {
        choice1Button.setVisibility(View.INVISIBLE);
        choice2Button.setText(R.string.done_button_text);
    }

    //Refactor and simplify? Looping page 7 UI
    private void headCheckPageUI(final Page page) {
        final String[] headToeCheck = {"Head", "Body", "Upper Legs", "Lower Legs", "Arms"};
        promptText.setText(getString(page.getTextId(), headToeCheck[0]));
        final int[] i = {1};

        //When click done - display next word in array
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i[0] < headToeCheck.length - 1) {
                    i[0]++;
                    promptText.setText(getString(page.getTextId(), headToeCheck[i[0]]));
                } else {
                    nextPage = page.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            int page = data.getIntExtra("page", 0);
            loadPage(page);
        }
    }

    @Override
    public void onBackPressed() {
        //Removes top page from stack
        pageStack.pop();
        //Will go back to Start page if on first page
        if (pageStack.isEmpty()) {
            super.onBackPressed();
        }
        else {
            loadPage(pageStack.pop());
        }
    }
}