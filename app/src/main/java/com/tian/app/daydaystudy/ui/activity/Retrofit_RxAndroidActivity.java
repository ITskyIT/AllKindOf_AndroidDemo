package com.tian.app.daydaystudy.ui.activity;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.tian.app.daydaystudy.R;
import com.tian.app.daydaystudy.bean.CookGson;
import com.tian.app.daydaystudy.interfaces.APIService;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
@ContentView(R.layout.activity_retrofit__rx_android)
public class Retrofit_RxAndroidActivity extends BaseActivity implements Callback<CookGson> {
    private List<String> list = new ArrayList<>();
    @ViewInject(R.id.textView)
    TextView tv;

    @Override
    protected void init() {
        list.add("a");
        list.add("b");
        list.add("c");
        extractMethod();
        //observerCreate1();
        //observerCreate2();
        //observerCreate3();
        // observableJust();
        //observableRange();
        //observableRepeat();
        //案例
        // observableAn();
        //ObservableObject();
        //ObservableFilter();
        // ObservableTake();
        //去重  .distinct()
        //间隔时间 interval()
        //延迟   timer()
        observableDistinct();
    }

    /**+
     * 去重
     */
    private void observableDistinct() {
        Observable.just(1, 2, 2, 3, 4, 5, 5).distinct().subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Toast.makeText(Retrofit_RxAndroidActivity.this, "收到命令:"+integer, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 输出个数
     */
    private void ObservableTake() {
        Observable.just(1, 2, 3, 4, 5).take(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i("打印结果", "收到命令:" + integer);
            }
        });
    }

    /**
     * 通过条件进行过滤
     */
    private void ObservableFilter() {
        Observable.just(1, 2, 3, 4, 5).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return integer > 2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i("打印结果", "收到命令:" + integer);
            }
        });
    }

    /**
     * 传递集合
     */
    private void ObservableObject() {
        Observable.from(list).map(new Func1<String, String>() { //map  还有flatMap
            @Override
            public String call(String s) {  //加工
                return "666" + s;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("打印结果", "收到命令:" + s); //循环打印
            }
        });
    }

    private void observableAn() {
        //lamba表达式   需要jdk1.8
       /* Observable.just("1", "2", "3", "4", "5")
                .map(Integer::parseInt)
                .filter(s->s>1)
                .distinct()
                .take(3)
                .subscribe(System.out::print);*/
    }

    /**
     * 循环执行
     */
    private void observableRepeat() {
        Observable.just(0, 1).repeat(5)
                .subscribeOn(AndroidSchedulers.mainThread()) //线程切换，不指定就在当前线程执行
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.i("打印结果", "收到命令:" + integer);
                    }
                });
    }

    /**
     * 类似for循环
     */
    private void observableRange() {
        Observable.range(0, 10).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i("打印结果", "收到命令:" + integer);
            }
        });
    }

    /**
     * 依次发射
     */
    private void observableJust() {
        Observable.just(1, 2, 3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.i("打印结果", "收到命令:" + integer);
            }
        });
    }

    /**
     * 简化
     */
    private void observerCreate3() {
        Observable.just("最简单").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("打印结果", "收到命令:" + s);
            }
        });
    }

    private void observerCreate2() {
        Observable<String> obser = Observable.just("我只想发一个语句");
        Action1<String> action = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.i("打印结果", "收到命令:" + s);
            }
        };
        obser.subscribe(action);
    }

    private void observerCreate1() {
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.i("打印结果", "onCompleted()");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("打印结果", "收到命令:" + s);
            }
        };
        Observable<String> obser = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("发出命令");
                subscriber.onCompleted();
            }
        });
        obser.subscribe(subscriber);
    }

    public void extractMethod() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://apis.juhe.cn")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIService service = retrofit.create(APIService.class);
        Call<CookGson> call = service.getList("7bd376d3a88fc6a1714298679f5171f5");
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<CookGson> call, Response<CookGson> response) {
        tv.setText(response.body().getReason()+"\n"+response.body().getResult().toString());
    }

    @Override
    public void onFailure(Call<CookGson> call, Throwable t) {
        Toast.makeText(this, "" + call.request().url(), Toast.LENGTH_SHORT).show();
    }
}
