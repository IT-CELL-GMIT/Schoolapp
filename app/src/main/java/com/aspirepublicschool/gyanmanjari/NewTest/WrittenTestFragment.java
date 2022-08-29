package com.aspirepublicschool.gyanmanjari.NewTest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aspirepublicschool.gyanmanjari.Common;
import com.aspirepublicschool.gyanmanjari.MainActivity;
import com.aspirepublicschool.gyanmanjari.R;
import com.aspirepublicschool.gyanmanjari.Test.TestTimer;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WrittenTestFragment extends Fragment {

    View v;
    private static final String TAG = "WrittenTestFragment";
    static TabLayout wrttabLayout;
    ViewPager wrtviewPager;
    TabNewWrittenAdapter adapter;
    static ArrayList<TestWrittenQuestion> testQuestionArrayList=new ArrayList<>();
    ArrayList<FinalWrittenAnswer> finalAnswers=new ArrayList<>();
    String tst_id,sub;
    TextView wrttxttimer;
    public static TextView wrttxtquestion,wrttxtposmark,wrttxtnegmark,wrttxtsub,wrttxtmaxmark;
    String[] mDataset;
    int hours,min;
    int duration,sec;
    long result;
    public static int ansques=0;
    public static int unanswered=0;
    private DBHelperNewWritten mydb;
    Context ctx;
    static boolean dataflags=false;
    Handler handler;
    pyCountDown timer,countDownTimeronResume;
    String time,pos,neg,type,reg,irreg,maxreg;
    Button wrtBtnsubmit,wrtbtnabort;

    public WrittenTestFragment(String tst_id, String sub, String hours, String min, String pos, String neg, String type, String reg, String irreg, String maxreg) {
        // Required empty public constructor
        this.tst_id= tst_id;
        this.sub = sub;
        this.hours = Integer.parseInt(hours);
        this.min = Integer.parseInt(min);
        this.pos = pos;
        this.neg = neg;
        this.type = type;
        this.reg = reg;
        this.irreg = irreg;
        this.maxreg = maxreg;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        v =  inflater.inflate(R.layout.fragment_written_test, container, false);

        ctx = getActivity();

        wrttabLayout=v.findViewById(R.id.wrttabLayout);
        wrtviewPager=v.findViewById(R.id.wrtviewPager);
        wrttxtmaxmark=v.findViewById(R.id.wrttxtmaxmark);

        /*tst_id=getActivity().getIntent().getExtras().getString("tst_id");
        sub=getActivity().getIntent().getExtras().getString("sub");
        hours=Integer.parseInt(getActivity().getIntent().getExtras().getString("hours"));
        min=Integer.parseInt(getActivity().getIntent().getExtras().getString("min"));
        pos=getActivity().getIntent().getExtras().getString("pos");
        neg=getActivity().getIntent().getExtras().getString("neg");
        type=getActivity().getIntent().getExtras().getString("type");
        reg=getActivity().getIntent().getExtras().getString("reg");
        irreg=getActivity().getIntent().getExtras().getString("irreg");
        maxreg=getActivity().getIntent().getExtras().getString("maxreg");
        Log.d(TAG, "onCreateView: "+maxreg);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(type);*/
        duration=(hours*60)+min;
        sec=duration*60;
        result = TimeUnit.SECONDS.toMillis(sec);

        Log.d(TAG, "onCreateView: "+maxreg);

        Log.d("!!!",""+sec);
        allocatememory();
        mydb = new DBHelperNewWritten(ctx);
        wrttxttimer.setText(wrttxttimer.getText() + String.valueOf(result));
        wrttxtposmark.setText(pos);
        wrttxtnegmark.setText(neg);
        wrttxtsub.setText(sub);
        Log.d(TAG, "onCreateViewsub: "+sub);
        Log.d(TAG, "onCreateViewmaxreg: "+maxreg);
        wrttxtmaxmark.setText(maxreg);

        Cursor rs = mydb.getData(tst_id);
        if (rs.moveToFirst())
        {
            dataflags=true;
            getAnswerBefore(tst_id);
            ArrayList<TestTimer> array_list = mydb.getAllCotacts(tst_id);
            for(int i=0; i < array_list.size(); i++)

                if(array_list.get(i).getTest_id().equals(tst_id)) {
                    Log.d("###",array_list.get(i).getTime());
                    timer=(pyCountDown) new pyCountDown(Long.parseLong(array_list.get(i).getTime()), 1000).start();
                }
                else {
                }
        }else {
            // record not found
            if(mydb.insertContact(tst_id, String.valueOf(result))) {
             /*   Toast.makeText(getApplicationContext(), "done",
                        Toast.LENGTH_SHORT).show();*/
                timer=(pyCountDown) new pyCountDown(result, 1000).start();
            }
            SendRequest();
        }

        if (!rs.isClosed())  {
            rs.close();
        }

        wrtBtnsubmit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setMessage("Are you sure, You wanted to submit test?..");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1)
                            {
                                timer.cancel();
                                saveData();
                            }
                        });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        alertDialogBuilder.setCancelable(true);
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

//        wrtbtnabort.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
//                alertDialogBuilder.setMessage("Are you sure, You wanted to Abort test?..");
//                alertDialogBuilder.setPositiveButton("Yes",
//                        new DialogInterface.OnClickListener()
//                        {
//                            @Override
//                            public void onClick(DialogInterface arg0, int arg1)
//                            {
//                                timer.cancel();
//                                startActivity(new Intent(ctx, ViewNewTestToday.class));
//                                getActivity().finish();
//                            }
//                        });
//
//                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener()
//                {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        alertDialogBuilder.setCancelable(true);
//                    }
//                });
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//            }
//        });
        return v;
    }

    private void allocatememory()
    {
        wrttxttimer=(TextView) v.findViewById(R.id.wrttxttimer);
        wrtBtnsubmit=(Button) v.findViewById(R.id.wrttoolbar_overflow_menu_button);
//        wrtbtnabort=(Button) v.findViewById(R.id.wrttoolbar_overflow_abort_button);
        wrttxtquestion=(TextView) v.findViewById(R.id.wrttxtquestion);/*
        txttotal=(TextView) v.findViewById(R.id.wrttxttotal);
        txttotalunanswered=(TextView) v.findViewById(R.id.wrttxttotalunanswered);
        txtunanswered=(TextView) v.findViewById(R.id.wrttxtunanswered);*/
        wrttxtposmark=(TextView) v.findViewById(R.id.wrttxtposmark);
        wrttxtnegmark=(TextView) v.findViewById(R.id.wrttxtnegmark);
        wrttxtsub=(TextView) v.findViewById(R.id.wrttxtsub);
    }

    private void saveData()
    {
//        Common.progressDialogShow(ctx);
        finalAnswers.clear();
        ArrayList<TestWrittenAnswer> answers = mydb.getAllTestData(tst_id);
        final String test = new Gson().toJson(answers);
        Log.d("test",test);
        String Webserviceurl= Common.GetWebServiceURL()+"submitTestNewWritten.php";
        //String Webserviceurl="https://www.zocarro.com/zocarro_mobile_app/ws/submitTest.php";
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ctx);
        final String sc_id = preferences.getString("sc_id","none").toUpperCase();
        final String stu_id = preferences.getString("stu_id","none").toUpperCase();
        final String class_id = preferences.getString("class_id","none").toUpperCase();
        StringRequest request=new StringRequest(StringRequest.Method.POST,Webserviceurl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                Log.d("!!!",response );
//                Common.progressDialogDismiss(ctx);

                try
                {
                    JSONObject object = new JSONObject(response);
                    if (object.getString("message").equals("fail"))
                    {
                        Toast.makeText(ctx, "Sorry!! Test is not Submitted",Toast.LENGTH_LONG).show();
                    }
                    else if(object.getString("message").equals("Success"))
                    {
                        Toast.makeText(ctx,"Test Submitted Successfully" ,Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(ctx, MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                } catch (JSONException e)
                {
                    // Toast.makeText(TestTine.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Common.progressDialogDismiss(ctx);
                Log.d("mess", error.toString());
                Toast.makeText(ctx,error.toString() ,Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<>();
                data.clear();
                data.put("test",test);
                data.put("sc_id", sc_id);
                data.put("stu_id",stu_id );
                data.put("cid",class_id );
                data.put("tst_id",tst_id );
                Log.d("PPP", data.toString());
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(5000,3,1));
        Volley.newRequestQueue(ctx).add(request);
    }

    private class pyCountDown extends CountDownTimer {

        public pyCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            long seconds = millisUntilFinished / 1000;
            long minutes = seconds / 60;
            long hours = minutes / 60;
            long days = hours / 24;
            time =  hours % 24 + ":" + minutes % 60 + ":" + seconds % 60;
            wrttxttimer.setText("Time remaining: " + time);
//            Log.d(TAG, "onTickwritten: "+time);
            if(mydb.updateContact(tst_id, String.valueOf(millisUntilFinished)))
            {
                /*Toast.makeText(getApplicationContext(), "Update",
                        Toast.LENGTH_SHORT).show();*/
            }
        }
        @Override
        public void onFinish() {
            wrttxttimer.setText("Done...!");
            saveData();
        }
    }

    private void SendRequest() {
//        Common.progressDialogShow(ctx);
        String Webserviceurl= Common.GetWebServiceURL()+"testquestionNewWritten.php";
        //String Webserviceurl="https://www.zocarro.com/zocarro_mobile_app/ws/testquestion.php";
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        final String class_id = mPrefs.getString("class_id","none");
        final String sc_id = mPrefs.getString("sc_id","none");
        final String stu_id = mPrefs.getString("stu_id","none");
        StringRequest request=new StringRequest(StringRequest.Method.POST,Webserviceurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//                    Common.progressDialogDismiss(ctx);
                    Log.d("aaa",response);
                    testQuestionArrayList.clear();
                    JSONArray array=new JSONArray(response);
                    int total=array.getJSONObject(0).getInt("total");
                    if(total==0)
                    {
                        Toast.makeText(ctx,"No Question",Toast.LENGTH_LONG).show();
                    }
                    else {
                        wrttxtquestion.setText(""+total);
                        mDataset=new String[array.length()-1];
                        for(int i=1;i<array.length();i++)
                        {                            /*  {
                            "qid": "QST1",
                                "question": "Test Question ?",
                                "a": "A",
                                "b": "B",
                                "c": "C",
                                "d": "D"
                        },*/
                            JSONObject object=array.getJSONObject(i);
                            testQuestionArrayList.add(new TestWrittenQuestion(object.getString("qid"),
                                    object.getString("question"),
                                    object.getString("ans"),
                                    object.getString("sign"),
                                    object.getString("q_img"),
                                    tst_id,object.getString("subject"),
                                    object.getBoolean("mark")));
                        }

                        for(int j=0;j<testQuestionArrayList.size();j++)
                        {
                            if(dataflags==false)
                            {
                                if (mydb.inserttest(testQuestionArrayList.get(j).getTst_id(),
                                        testQuestionArrayList.get(j).getQ_id(),
                                        testQuestionArrayList.get(j).getQuestion(),
                                        testQuestionArrayList.get(j).getSign(),
                                        testQuestionArrayList.get(j).getSub()
                                        ,0, "Not Set"))
                                {

                                }
                            }
                        }
                        for (int k = 0; k <testQuestionArrayList.size(); k++)
                        {
                            wrttabLayout.addTab(wrttabLayout.newTab());
                            TextView tv=(TextView) LayoutInflater.from(ctx).inflate(R.layout.custom_view,null);
                            tv.setTextColor(Color.parseColor("#FFFFFF"));
                            wrttabLayout.getTabAt(k).setCustomView(tv).setText("" + (k+1));
                        }
                        adapter = new TabNewWrittenAdapter
                                (getActivity().getSupportFragmentManager(), wrttabLayout.getTabCount());
                        wrtviewPager.setAdapter(adapter);
                        wrtviewPager.setOffscreenPageLimit(1);
                        wrtviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(wrttabLayout));
                        wrttabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
                        {

                            @Override
                            public void onTabSelected(TabLayout.Tab tab)
                            {
                                wrtviewPager.setCurrentItem(tab.getPosition());
                            }
                            @Override
                            public void onTabUnselected(TabLayout.Tab tab)
                            {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab)
                            {

                            }
                        });
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(ctx, ""+error, Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> data=new HashMap<>();
                data.put("sc_id",sc_id);
                data.put("cid",class_id);
                data.put("tst_id",tst_id);
                Log.d("data", data.toString());
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(3000,3,1));
        Volley.newRequestQueue(ctx).add(request);
    }

    private void getAnswerBefore(final String tst_id)
    {
//        Common.progressDialogShow(ctx);
        String Webserviceurl= Common.GetWebServiceURL()+"getAnswergivenBeforeNewWritten.php";
        //String Webserviceurl="https://www.zocarro.com/zocarro_mobile_app/ws/testquestion.php";
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(ctx);
        final String class_id = mPrefs.getString("class_id","none");
        final String sc_id = mPrefs.getString("sc_id","none");
        final String stu_id = mPrefs.getString("stu_id","none");
        StringRequest request=new StringRequest(StringRequest.Method.POST,Webserviceurl, new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
//                    Common.progressDialogDismiss(ctx);

                    Log.d("aaa",response);
                    testQuestionArrayList.clear();
                    JSONArray array=new JSONArray(response);
                    int total=array.getJSONObject(0).getInt("total");
                    if(total==0)
                    {
                        Toast.makeText(ctx,"No Question",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        wrttxtquestion.setText(""+total);
                        mDataset=new String[array.length()-1];
                        for(int i=1;i<array.length();i++)
                        {                            /*  {
                            "qid": "QST1",
                                "question": "Test Question ?",
                                "a": "A",
                                "b": "B",
                                "c": "C",
                                "d": "D"
                        },*/

                            JSONObject object=array.getJSONObject(i);
                            testQuestionArrayList.add(new TestWrittenQuestion(object.getString("qid"),
                                    object.getString("question"),
                                    object.getString("ans"),
                                    object.getString("sign"),
                                    object.getString("q_img"),
                                    tst_id,object.getString("subject"),
                                    object.getBoolean("mark")));
                        }
                        for (int k = 0; k <testQuestionArrayList.size(); k++) {
                            wrttabLayout.addTab(wrttabLayout.newTab());
                            TextView tv=(TextView) LayoutInflater.from(ctx).inflate(R.layout.custom_view,null);
                            tv.setTextColor(Color.parseColor("#FFFFFF"));
                            wrttabLayout.getTabAt(k).setCustomView(tv).setText("" + (k+1));
                        }
                        adapter = new TabNewWrittenAdapter(getActivity().getSupportFragmentManager(), wrttabLayout.getTabCount());
                        wrtviewPager.setAdapter(adapter);
                        wrtviewPager.setOffscreenPageLimit(1);
                        wrtviewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(wrttabLayout));
                        wrttabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                            @SuppressLint("ResourceAsColor")
                            @Override
                            public void onTabSelected(TabLayout.Tab tab) {
                                wrtviewPager.setCurrentItem(tab.getPosition());
                            }

                            @Override
                            public void onTabUnselected(TabLayout.Tab tab) {

                            }

                            @Override
                            public void onTabReselected(TabLayout.Tab tab) {

                            }
                        });
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ctx, ""+error, Toast.LENGTH_LONG).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data=new HashMap<>();
                data.put("sc_id",sc_id);
                data.put("cid",class_id);
                data.put("tst_id",tst_id);
                data.put("stu_id",stu_id);
                Log.d("data", data.toString());
                return data;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(3000,3,1));
        Volley.newRequestQueue(ctx).add(request);
    }
}