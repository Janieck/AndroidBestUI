package paxsz.com.androidbestui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Msg> mMsgList = new ArrayList<>();
    private RecyclerView msg_recycler_view;
    private EditText input_text;
    private Button send;
    private MsgAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initMsgs(); //初始化消息数据
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msg_recycler_view.setLayoutManager(layoutManager);
        mAdapter = new MsgAdapter(mMsgList);
        msg_recycler_view.setAdapter(mAdapter);
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        mMsgList.add(msg1);
        Msg msg2 = new Msg("Hello .Who is that?", Msg.TYPE_SENT);
        mMsgList.add(msg2);
        Msg msg3 = new Msg("This  is Tom. Nice talking to you", Msg.TYPE_RECEIVED);
        mMsgList.add(msg3);
    }

    private void initView() {
        msg_recycler_view = (RecyclerView) findViewById(R.id.msg_recycler_view);
        input_text = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                String content = input_text.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    mMsgList.add(msg);
                    mAdapter.notifyItemInserted(mMsgList.size() - 1);//当有新消息时
                    //刷新ListView的显示
                    msg_recycler_view.scrollToPosition(mMsgList.size() - 1);
                    //将ListView定位到最后一行
                    input_text.setText("");
                }
                break;
        }
    }

}
