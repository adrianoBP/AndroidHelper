package generic.adriano.androidhelper.Activities;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import generic.adriano.androidhelper.Additions.CustomListviewElement;
import generic.adriano.androidhelper.R;

public class CustomListviewActivity extends AppCompatActivity {

    // Class elements
    Context classContext;
    View classView;
    List<CustomListviewElement> myElements;
    CustomAdapterListView customAdapterListView;

    // View elements declaration
    ListView lvElements;
    Button bAddElement;
    EditText etElementText;

    public CustomListviewActivity(Context context, View view){

        classContext = context;
        classView = view;
        myElements = new ArrayList<>();

        ((Activity)classContext).setTitle("Custom ListView");

        // View elements initialization
        lvElements = classView.findViewById(R.id.lvCustomListview);
        bAddElement = classView.findViewById(R.id.bAddCLVelement);
        etElementText = classView.findViewById(R.id.etCLVtext);

        customAdapterListView = new CustomAdapterListView();
        lvElements.setAdapter(customAdapterListView);

        // View elements listeners
        bAddElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomListviewElement customListviewElement = new CustomListviewElement();
                customListviewElement.myString = etElementText.getText().toString();
                myElements.add(customListviewElement);
                customAdapterListView.notifyDataSetChanged();
            }
        });
    }
    public class CustomAdapterListView extends BaseAdapter {

        @Override
        public int getCount() {
            return myElements.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = ((Activity)classContext).getLayoutInflater().inflate(R.layout.layout_adapter_listview, null);

            final TextView tvText = view.findViewById(R.id.tvCLVtext);
            Button bButton = view.findViewById(R.id.bCLVbutton);
            ConstraintLayout clParent = view.findViewById(R.id.clCLVparent);

            tvText.setText(myElements.get(i).myString);

            clParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(classContext, tvText.getText(), Toast.LENGTH_SHORT).show();
                }
            });

            bButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myElements.get(i).myString = "";
                    tvText.setText("");
                }
            });
            return view;
        }
    }


}