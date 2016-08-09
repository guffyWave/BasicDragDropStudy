package com.gufran.basicdragdropstudy;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imgView, scenaryImageView;
    RelativeLayout mainRelativeLayout;
    String TAG = "GUFRAN";
    TextView textView;

    @SuppressLint("NewApi")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = (ImageView) findViewById(R.id.imgView);
        scenaryImageView = (ImageView) findViewById(R.id.scenaryImageView);
        textView = (TextView) findViewById(R.id.textView);

        imgView.setTag("IMAGE_VIEW");

        mainRelativeLayout = (RelativeLayout) findViewById(R.id.mainRelativeLayout);

        imgView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item clipDataItem = new ClipData.Item((CharSequence) v.getTag());
                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, clipDataItem);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(imgView);
                v.startDrag(dragData, myShadow, null, 0);
                imgView.setVisibility(View.GONE);
                return true;
            }
        });

        scenaryImageView.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                switch (event.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        Log.d(TAG, "onDrag: ACTION_DRAG_STARTED ");
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        Log.d(TAG, "onDrag: ACTION_DRAG_ENTERED ");
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        Log.d(TAG, "onDrag: ACTION_DRAG_EXITED ");
                        break;
                    case DragEvent.ACTION_DRAG_LOCATION:
                        int x_cord = (int) event.getX();
                        int y_cord = (int) event.getY();
                        //Log.d(TAG, "onDrag: ACTION_DRAG_LOCATION ");
                        textView.setText("Dragged At (" + x_cord + "," + y_cord + ")");
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        Log.d(TAG, "onDrag: ACTION_DRAG_ENDED ");
                        break;
                    case DragEvent.ACTION_DROP:
                        x_cord = (int) event.getX();
                        y_cord = (int) event.getY();
                        Log.d(TAG, "onDrag: ACTION_DROP at (" + x_cord + "," + y_cord + ")");
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }
}
