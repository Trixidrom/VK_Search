package com.example.vksearch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{
    private List<String[]> list;
    //private static int viewHolderCount;
    private int usersItems;
    private String i;
    private Context main;
    public UsersAdapter (int usersItems, List<String[]> list, Context main){
        this.usersItems = usersItems;
        this.list = list;
        this.main = main;
        //viewHolderCount = 0;
    }



    @NonNull
    @NotNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {
        //тут создаем вью холдеры, сколько поместится на экран + пеще пара за экран
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.user_list_item;//нашли ID хмл файла

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);//сделали вью

        UsersViewHolder viewHolder = new UsersViewHolder(view);//обернули в вью холдер

        //viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UsersViewHolder usersViewHolder, int i) {
        //тут меняем значения новым холдерам
        String[] arr = list.get(i);
        usersViewHolder.bind(arr, main);
    }

    @Override
    public int getItemCount() {
        //возвращает общее кол-во элементов списка
        return usersItems;
    }

    class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView name, bData, city;
        ImageView avatar;

        public UsersViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_name);
            bData = itemView.findViewById(R.id.tv_bdata);
            city = itemView.findViewById(R.id.tv_city);
            avatar = itemView.findViewById(R.id.iv_avatar);


        }

        void bind(String arr[], Context main){
            this.name.setText(arr[0] +" "+ arr[1]);
            this.bData.setText(arr[3]);
            this.city.setText(arr[5]);
            Picasso.with(main)
                    .load(arr[4])
                    .placeholder(R.drawable.load)
                    .error(R.drawable.error)
                    .into(avatar);
        }
    }
}
