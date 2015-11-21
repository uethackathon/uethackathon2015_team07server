
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


        int random = getRandomNumber();
        HistoryItem item = db.get(random);
        question.setText(item.getContent());
        answer = getRandomAnwser();

        if(answer == 1){
            buttonA.setText(item.getDay_month());
        }else if(answer == 2){
            buttonB.setText(item.getDay_month());
        }else if(answer == 3){
            buttonC.setText(item.getDay_month());
        }else if(answer == 4){
            buttonD.setText(item.getDay_month());
        }

        next.setOnClickListener(this);
        buttonA.setOnClickListener(this);
        buttonB.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonD.setOnClickListener(this);



        return v;
    }

    @Override
    public void onClick(View view) {
        if(view == next){
            numQuest ++;
            txtNumQuest.setText("Question "+ numQuest);
            statePlay = true;
            result.setVisibility(View.GONE);
            int random = getRandomNumber();
            HistoryItem item = db.get(random);
            question.setText(item.getContent());

            answer = getRandomAnwser();

            if(answer == 1){
                buttonA.setText(item.getDay_month());
            }else if(answer == 2){
                buttonB.setText(item.getDay_month());
            }else if(answer == 3){
                buttonC.setText(item.getDay_month());
            }else if(answer == 4){
                buttonD.setText(item.getDay_month());
            }
        }

        if(view == buttonA){
            if(statePlay == true){
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

}

