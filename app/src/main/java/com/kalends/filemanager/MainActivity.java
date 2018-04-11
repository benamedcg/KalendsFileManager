package com.kalends.filemanager;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kalends.filemanager.pojo.FileInfo;
import com.kalends.filemanager.util.AllSort;
import com.kalends.filemanager.util.FileAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {
    //根路径
    private static final String ROOT_PATH = "/";
    //菜单栏目
    private Toolbar toolbar;
    //具体文件名称
    private TextView showtv;
    //显示目录下的文件
    private ListView listView;
    //储存文件名
    private List<FileInfo> fileList;
    //当前目录名称
    private File[] files;
    //适配器
    private ArrayAdapter<FileInfo> adapter;
    //栈
    private Stack<String> nowPathStack = new Stack<>();

    //排序规则
    private AllSort allSort;
    //初始化个控件
    public void initial(){
        allSort = new AllSort(0);
        toolbar = findViewById(R.id.toolbar);
        listView = findViewById(R.id.list_item);
        showtv = findViewById(R.id.text_view);
        //加载菜单
        setSupportActionBar(toolbar);
        //加载根目录下的所以文件名
        View headView = new View(this);
        headView.setLayoutParams(new ListView.LayoutParams(ListView.LayoutParams.MATCH_PARENT,
                ((int)getResources().getDimension(R.dimen.abc_action_bar_default_height_material)+15)));
        listView.addHeaderView(headView);
        //nowPathStack.push(ROOT_PATH);
        nowPathStack.push(Environment.getExternalStorageDirectory().toString());
        showFileName(Environment.getExternalStorageDirectory().toString());

    }

    //设置ListView 的个选项
    public void listViewSet(){
        adapter = new FileAdapter(MainActivity.this,R.layout.file_item,fileList);
        listView.setAdapter(adapter);
        //添加ListView 的 item的监听
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d("position",String.valueOf(position));
                if (nowPathStack.size() != 0) {
                    nowPathStack.push("/"+fileList.get(position-1).getName());
                }
                //获取当前路径
                String path = getPathString();
                showFileName(path);
            }
        });

    }

    //添加文件名
    public void showFileName(String path){
        Log.d("path",path);
        showtv.setText(path);
        showtv.setTextColor(this.getResources().getColor(R.color.colorWhite));
        showtv.setTextSize(15);
        fileList = new ArrayList<FileInfo>();
        File file = new File(path);
        //File file = Environment.getExternalStorageDirectory();
        Log.d("file size",String.valueOf(file.length()));
        files = file.listFiles();

        if ((files == null || files.length == 0)){
            listViewSet();
            return;
        }

        //给fileList赋值
        for (File f:files){
            if (f.getName().startsWith(".")){
                //过滤隐藏文件
                continue;
            }
            if(f.getName().endsWith("mp3")){
                fileList.add(new FileInfo(f,R.drawable.music));
            }else if(f.getName().endsWith("apk")){
                fileList.add(new FileInfo(f,R.drawable.apk));
            }else if(f.isDirectory()){
                fileList.add(new FileInfo(f,R.drawable.folder));
            }else {
                fileList.add(new FileInfo(f,R.drawable.txt));
            }
        }
        //按文件名次排序
        Collections.sort(fileList,allSort);
        //设置ListView
        listViewSet();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化方法
        initial();
    }

    //为菜单添加菜单项
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    //菜单项的点击事件
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.back:
                nowPathStack.pop();
                String path = getPathString();
                showFileName(path);
                break;
            case R.id.add:
                Toast.makeText(this,"add",Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Toast.makeText(this,"search",Toast.LENGTH_SHORT).show();
                break;
            default:
        }

        return true;
    }

    //获取文件路径并且存储在栈中
    private String getPathString(){

        Stack<String> temp = new Stack<String>();
        temp.addAll(nowPathStack);
        String result = "";
        while (temp.size() >0){
            result = temp.pop() + result;
        }
        return result;
    }


}
