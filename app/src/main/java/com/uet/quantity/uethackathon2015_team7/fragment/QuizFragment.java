
package com.uet.quantity.uethackathon2015_team7.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.uet.quantity.uethackathon2015_team7.R;
import com.uet.quantity.uethackathon2015_team7.database.DatabaseHandler;
import com.uet.quantity.uethackathon2015_team7.model.HistoryItem;

import java.util.Random;

public class QuizFragment extends Fragment implements View.OnClickListener{

    ImageView next;
    TextView quizGame;
    TextView txtNumQuest ;
    TextView question ;
    TextView result ;
    Button buttonA ;
    Button buttonB ;
    Button buttonC ;
    Button buttonD ;

    DatabaseHandler db;
    int numQuest = 1;
    boolean statePlay = true;
    int answer = 0;

    public static QuizFragment instance;
    Typeface typeface1,typeface2, typeface3;

    public static QuizFragment newInstance() {
        if(instance == null) {
            instance = new QuizFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        db = new DatabaseHandler(getActivity());



        typeface1 = Typeface.createFromAsset(
                getActivity().getAssets(), "fonts/SudegnakNo2.ttf");
        typeface2 = Typeface.createFromAsset(
                getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        typeface3 = Typeface.createFromAsset(
                getActivity().getAssets(), "fonts/abcd.ttf");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quiz, container, false);
        quizGame = (TextView) v.findViewById(R.id.quiz_game);
        txtNumQuest = (TextView) v.findViewById(R.id.num_question);
        question = (TextView) v.findViewById(R.id.txt_title_question);
        result = (TextView) v.findViewById(R.id.result);
        buttonA = (Button) v.findViewById(R.id.buttonA);
        buttonB = (Button) v.findViewById(R.id.buttonB);
        buttonC = (Button) v.findViewById(R.id.buttonC);
        buttonD = (Button) v.findViewById(R.id.buttonD);
        next = (ImageView) v.findViewById(R.id.next_question);



        quizGame.setTypeface(typeface1);
        txtNumQuest.setTypeface(typeface3);
        question.setTypeface(typeface2);
        result.setTypeface(typeface1);
        buttonA.setTypeface(typeface2);
        buttonB.setTypeface(typeface2);
        buttonC.setTypeface(typeface2);
        buttonD.setTypeface(typeface2);

        txtNumQuest.setText("Question " + numQuest);


       getNewQuestion();

        next.setOnClickListener(this);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);



        return v;
    }

    public void getNewQuestion(){
        int random = getRandomNumber();
        HistoryItem item = db.get(random);
        HistoryItem item1 = db.get(random + 5);
        HistoryItem item2 = db.get(random + 10);
        HistoryItem item3 = db.get(random + 15);
        question.setText(item.getContent());
        answer = getRandomAnwser();

        if(answer == 1){
            buttonA.setText(item.getDay_month());
            buttonB.setText(item1.getDay_month());
            buttonC.setText(item2.getDay_month());
            buttonD.setText(item3.getDay_month());
        }else if(answer == 2){
            buttonB.setText(item.getDay_month());
            buttonA.setText(item1.getDay_month());
            buttonC.setText(item2.getDay_month());
            buttonD.setText(item3.getDay_month());
        }else if(answer == 3){
            buttonA.setText(item2.getDay_month());
            buttonB.setText(item1.getDay_month());
            buttonD.setText(item3.getDay_month());
            buttonC.setText(item.getDay_month());
        }else if(answer == 4){
            buttonD.setText(item.getDay_month());
            buttonA.setText(item3.getDay_month());
            buttonB.setText(item1.getDay_month());
            buttonC.setText(item2.getDay_month());
        }

    }

    @Override
    public void onClick(View view) {
        if(view == next){
            setDefault();
            numQuest ++;
            txtNumQuest.setText("Question "+ numQuest);
            statePlay = true;
            result.setVisibility(View.GONE);

            getNewQuestion();
        }

        if(view == buttonA){
            if(statePlay == true){
                changeColorAnswer();
                statePlay = false;
                next.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);

                if(answer == 1){
                    result.setText("Đáp án chính xác ");
                }else{
                    result.setText("Đáp án sai");
                }
            }
        }

        if(view == buttonB){
            if(statePlay == true){
                changeColorAnswer();
                statePlay = false;
                next.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                if(answer == 2){
                    result.setText("Đáp án chính xác ");
                }else{
                    result.setText("Đáp án sai");
                }
            }
        }

        if(view == buttonC){
            if(statePlay == true){
                changeColorAnswer();
                statePlay = false;
                next.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);
                if(answer == 3){
                    result.setText("Đáp án chính xác ");
                }else{
                    result.setText("Đáp án sai");
                }
            }
        }

        if(view == buttonD){
            if(statePlay == true){
                changeColorAnswer();
                statePlay = false;
                next.setVisibility(View.VISIBLE);
                result.setVisibility(View.VISIBLE);

                if(answer == 4){
                    result.setText("Đáp án chính xác ");
                }else{
                    result.setText("Đáp án sai");
                }
            }
        }

    }

    public int getRandomNumber(){
        Random rand = new Random();
        return  rand.nextInt(1500) + 2;
    }

    public int getRandomAnwser(){
        Random rand = new Random();
        return  rand.nextInt(4) + 1;
    }

    public void changeColorAnswer(){
        if(answer == 1){
            buttonA.setBackground(getResources().getDrawable(R.drawable.rounded_button1));
        } else if (answer == 2){
            buttonB.setBackground(getResources().getDrawable(R.drawable.rounded_button1));
        }else if (answer == 3){
            buttonC.setBackground(getResources().getDrawable(R.drawable.rounded_button1));
        }else if (answer == 4){
            buttonD.setBackground(getResources().getDrawable(R.drawable.rounded_button1));
        }
    }

    public void setDefault(){
        buttonA.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        buttonB.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        buttonC.setBackground(getResources().getDrawable(R.drawable.rounded_button));
        buttonD.setBackground(getResources().getDrawable(R.drawable.rounded_button));
    }

}

