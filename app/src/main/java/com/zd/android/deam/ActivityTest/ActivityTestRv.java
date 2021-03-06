package com.zd.android.deam.ActivityTest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.zd.android.deam.ActivityTest.adapter.TestAdapter;
import com.zd.android.deam.R;
import com.zd.android.deam.appDeam.ui.api.ApiBase;
import com.zd.android.deam.appDeam.ui.api.Apiserver;
import com.zd.android.deam.mvp.bean.HistoryInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ActivityTestRv extends AppCompatActivity {

    private static final String TAG = "MY_INFO";
    private RecyclerView recycleView;
    private TestAdapter mAdapter;
    private List<String> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rv);


        recycleView = (RecyclerView) findViewById(R.id.mytest);
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(4, 1));
        mAdapter = new TestAdapter(data);
        recycleView.setAdapter(mAdapter);
        init();

        MyItemCallback myItemCallback = new MyItemCallback(new ItemListener() {
            @Override
            public void onSwiped(int adapterPosition) {

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {

                mAdapter.notifyItemMoved(srcPosition,targetPosition);
                return false;
            }
        });
        MyItemTouchHelper myItemTouchHelper = new MyItemTouchHelper(myItemCallback);
        myItemTouchHelper.attachToRecyclerView(recycleView);

//        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
//            @Override
//            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//                //拖拽
//                int tMove = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
//                //滑动删除  0 --不能删除
//                int tdeleta = 0;
//                return makeMovementFlags(tMove, tdeleta);
//            }
//
//            @Override
//            public boolean isLongPressDragEnabled() {
//                return false;
//            }
//
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                // 拖拽
//                // 更新数据、适配器
//                Collections.swap(data, viewHolder.getAdapterPosition(), target.getAdapterPosition());
//                mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//                //侧滑删除，一般删除数据源，通知适配器更新
//            }
//        });
//        helper.attachToRecyclerView(recycleView);

//        testRxJava();
//        testRxjava();
        testRx();
    }

    private void init() {
        for (int i = 0; i < 30; i++) {
            data.add("test" + i);
        }
        mAdapter.notifyDataSetChanged();
    }


    private class MyItemTouchHelper extends ItemTouchHelper {
        /**
         * Creates an ItemTouchHelper that will work with the given Callback.
         * <p>
         * You can attach ItemTouchHelper to a RecyclerView via
         * {@link #attachToRecyclerView(RecyclerView)}. Upon attaching, it will add an item decoration,
         * an onItemTouchListener and a Child attach / detach listener to the RecyclerView.
         *
         * @param callback The Callback which controls the behavior of this touch helper.
         */
        public MyItemTouchHelper(Callback callback) {
            super(callback);
        }
    }



    private void testRxJava() {
        //创建一个被观察者
        Observable<Object> observable = Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
                Log.d(TAG, "subscribe: ======observable--subscribe  start");
                e.onNext(1);
                e.onNext(2);
//                e.onComplete();
//                e.onError(new IndexOutOfBoundsException("test"));
                Log.d("MY_INFO", "subscribe: ======observable--subscribe  success");
            }
        });

        //创建一个观察者
        Observer<Object> observer = new Observer<Object>() {

            /** onError方法和onComplete方法只能执行一个，不可能同时执行 **/

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: =====================" + d);
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: ====================" + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ====================" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ========================");
            }
        };

        //被观察者与观察者建立订阅关系
        observable.subscribe(observer);

        /**
         * log信息：
         * D/MY_INFO: onSubscribe: =====================null
         * D/MY_INFO: subscribe: ======observable--subscribe  start
         * D/MY_INFO: onNext: ====================1
         * D/MY_INFO: onNext: ====================2
         * D/MY_INFO: onComplete: ========================
         * D/MY_INFO: subscribe: ======observable--subscribe  success
         */
    }


    //上面代码连写
    private void testRxjava() {

        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> e) throws Exception {
//                Log.d(TAG, "subscribe: ======observable--subscribe  start");
//                e.onNext(1);
//                e.onNext(2);
//                e.onComplete();
//                Log.d(TAG, "subscribe: ======observable--subscribe  success");

                Log.d(TAG, "subscribe: ======observable--subscribe  start");
                Log.d(TAG, "subscribe  emit ============ 1");
                e.onNext(1);
                Log.d(TAG, "subscribe  emit ============ 2");
                e.onNext(2);
                Log.d(TAG, "subscribe  emit ============ 3");
                e.onNext(3);
                Log.d(TAG, "subscribe  emit ========= complete");
                e.onComplete();
                Log.d(TAG, "subscribe  emit ============ 4");
                e.onNext(4);
                Log.d(TAG, "subscribe: ======observable--subscribe  success");
            }
        }).subscribe(new Observer<Object>() {
            private Disposable mdisposable;
            private int i = 0;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: =====================" + d);
                mdisposable = d;
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext: ====================" + o);
                i++;
                if (i == 2) {
                    mdisposable.dispose();
                    Log.d(TAG, "onNext: ====mdisposable " + mdisposable.isDisposed());
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ====================" + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ========================");
            }
        });

//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: onSubscribe: =====================null
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe: ======observable--subscribe  start
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe  emit ============ 1
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: onNext: ====================1
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe  emit ============ 2
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: onNext: ====================2
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: onNext: ====mdisposable true  //事件发射器被处理，后续不在发射，直接将subscribe方法以后的log全部打印
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe  emit ============ 3
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe  emit ========= complete
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe  emit ============ 4
//        04-01 16:04:07.952 30244-30244/com.zd.android.deam D/MY_INFO: subscribe: ======observable--subscribe  success

    }

    private void testRx() {
        Apiserver.Factory.getApiserver()
                .gethis(ApiBase.APP_KEY, "0102")
                .subscribeOn(Schedulers.io())  //异步订阅
                .observeOn(AndroidSchedulers.mainThread())  //UI线程更新
                .subscribe(new Observer<HistoryInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe: =============");
                    }

                    @Override
                    public void onNext(HistoryInfo historyInfo) {
                        Log.d(TAG, "onNext: =================" + historyInfo.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: ================");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: =================");
                    }
                });


    }


    public interface ItemListener{

        /**
         * 当某个Item被滑动删除的时候
         *
         * @param adapterPosition item的position
         */
        void onSwiped(int adapterPosition);

        /**
         * 当两个Item位置互换的时候被回调
         *
         * @param srcPosition    拖拽的item的position
         * @param targetPosition 目的地的Item的position
         * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
         */
        boolean onMove(int srcPosition, int targetPosition);

    }

    public class MyItemCallback extends ItemTouchHelper.Callback {
        private ItemListener itemListener;

        public MyItemCallback(ItemListener itemListener) {
            this.itemListener = itemListener;
        }

        public void setItemListener(ItemListener itemListener) {
            this.itemListener = itemListener;
        }

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            int toMove = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            int toDelete = 0;
            return makeMovementFlags(toMove, toDelete);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            Log.d(TAG, "onMove: ==========" + recyclerView.getChildItemId(viewHolder.itemView));
            if (itemListener!=null) {
                itemListener.onMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
            }

            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.d(TAG, "onSwiped: ===========" + direction);

            if (itemListener!=null) {
                itemListener.onSwiped(viewHolder.getAdapterPosition());
            }
        }
    }

}
