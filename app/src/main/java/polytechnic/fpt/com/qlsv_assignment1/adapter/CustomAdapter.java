package polytechnic.fpt.com.qlsv_assignment1.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import polytechnic.fpt.com.qlsv_assignment1.R;
import polytechnic.fpt.com.qlsv_assignment1.model.Class_model;

public class CustomAdapter extends ArrayAdapter<Class_model> {
    private Context context;
    private int resource;
    private List<Class_model> listClass;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<Class_model> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listClass = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.row_item_listview, parent, false);
            viewHolder = new ViewHolder();
            
            viewHolder.tvId = (TextView) convertView.findViewById(R.id.tv_listclass_id);
            viewHolder.tvKeyClass = (TextView) convertView.findViewById(R.id.tv_listclass_keyclass);
            viewHolder.tvCLassName = (TextView) convertView.findViewById(R.id.tv_listclass_classname);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Class_model classModel = listClass.get(position);
        viewHolder.tvId.setText(classModel.getmId()+"");
        viewHolder.tvKeyClass.setText(classModel.getmKeyClass());
        viewHolder.tvCLassName.setText(classModel.getmClassName());

        return convertView;
    }
    public class ViewHolder{
        private TextView tvId;
        private TextView tvKeyClass;
        private TextView tvCLassName;

    }
}
