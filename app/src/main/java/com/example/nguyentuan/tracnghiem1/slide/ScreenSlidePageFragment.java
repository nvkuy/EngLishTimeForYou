package com.example.nguyentuan.tracnghiem1.slide;


import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.nguyentuan.tracnghiem1.CSDL.DBHelper;
import com.example.nguyentuan.tracnghiem1.R;
import com.example.nguyentuan.tracnghiem1.question.Question;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScreenSlidePageFragment extends Fragment {

    final String DB_NAME = "CSDL.sqlite";
    SQLiteDatabase database;
    ArrayList<Question> arr_Ques;
    public static final String ARG_PAGE = "page";
    public static final String ARG_CHECKANSWER = "checkAnswer";
    public int mPageNumber; // vị trí trang hiện tại
    public int checkAns;   // biến kiểm tra

    TextView tvNum, tvQuestion;
    RadioGroup radioGroup;
    RadioButton radA, radB, radC, radD;


    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);

        start(rootView);
        return rootView;
    }

    public void start(ViewGroup rootView) {
        tvNum = (TextView) rootView.findViewById(R.id.tvNum);
        tvQuestion = (TextView) rootView.findViewById(R.id.tvQuestion);
        radA = (RadioButton) rootView.findViewById(R.id.radA);
        radB = (RadioButton) rootView.findViewById(R.id.radB);
        radC = (RadioButton) rootView.findViewById(R.id.radC);
        radD = (RadioButton) rootView.findViewById(R.id.radD);
        radioGroup = (RadioGroup) rootView.findViewById(R.id.radGroup);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        arr_Ques = new ArrayList<Question>();
        ScreenSlideActivity slideActivity = (ScreenSlideActivity) getActivity();
        arr_Ques = slideActivity.getData();
        mPageNumber = getArguments().getInt(ARG_PAGE);
        checkAns = getArguments().getInt(ARG_CHECKANSWER);
        database = DBHelper.initDatabase(getActivity(), DB_NAME);

    }

    public static ScreenSlidePageFragment create(int pageNumber, int checkAnswer) {
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putInt(ARG_CHECKANSWER, checkAnswer);
        fragment.setArguments(args);
        return fragment;
    }

//    public Question getItem(int position) {
//        return arr_Ques.get(position);
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvNum.setText("Câu " + (mPageNumber + 1));
        tvQuestion.setText(arr_Ques.get(mPageNumber).getQuestion());
        radA.setText(getItem(mPageNumber).getAns_a());
        radB.setText(getItem(mPageNumber).getAns_b());
        radC.setText(getItem(mPageNumber).getAns_c());
        radD.setText(getItem(mPageNumber).getAns_d());

        //xử lí sự kiện nộp bài

        if (checkAns != 0) {
            radA.setClickable(false);
            radB.setClickable(false);
            radC.setClickable(false);
            radD.setClickable(false);
            getCheckAns(getItem(mPageNumber).getResult().toString());
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getItem(mPageNumber).choiceID = checkedId;
                getItem(mPageNumber).setTraloi(getChoiceFromID(checkedId));

            }
        });

    }

    public Question getItem(int posotion) {
        return arr_Ques.get(posotion);
    }

    //Lấy giá trị (vị trí) radiogroup chuyển thành đáp án A/B/C/D
    private String getChoiceFromID(int ID) {
        if (ID == R.id.radA) {
            return "A";
        } else if (ID == R.id.radB) {
            return "B";
        } else if (ID == R.id.radC) {
            return "C";
        } else if (ID == R.id.radD) {
            return "D";
        } else return "";
    }

    //Hàm kiểm tra câu đúng, nếu câu đúng thì đổi màu background radiobutton tương ứng
    private void getCheckAns(String ans) {
        if (ans.equals("A")) {
            radA.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("B")) {
            radB.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("C")) {
            radC.setBackgroundColor(Color.GREEN);
        } else if (ans.equals("D")) {
            radD.setBackgroundColor(Color.GREEN);
        }
//        switch (ans) {
//            case "A":
//                radA.setBackgroundColor(Color.GREEN);
//                break;
//            case "B":
//                radA.setBackgroundColor(Color.GREEN);
//                break;
//            case "C":
//                radA.setBackgroundColor(Color.GREEN);
//                break;
//            case "D":
//                radA.setBackgroundColor(Color.GREEN);
//                break;
//        }
    }
}



