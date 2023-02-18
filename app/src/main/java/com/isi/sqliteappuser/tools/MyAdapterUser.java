package com.isi.sqliteappuser.tools;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.isi.sqliteappuser.R;
import com.isi.sqliteappuser.entities.User;


public class MyAdapterUser extends BaseAdapter {

	private ArrayList<User> users;
	private LayoutInflater myInflater;
	
	public MyAdapterUser(Context context, ArrayList<User> users)
	{
		this.myInflater = LayoutInflater.from(context);
		this.users = users;
	}
	
	@Override
	public int getCount() {
		return this.users.size();
	}

	@Override
	public Object getItem(int arg0) {
		return this.users.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public static class ViewHolder {
		TextView textid;
		TextView textemail;
		TextView textpwd;
		TextView textetat;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		if (convertView == null)
		{
			convertView = myInflater.inflate(R.layout.listitem_user, null);
			holder = new ViewHolder();
			holder.textid = (TextView) convertView.findViewById(R.id.txtid);
			holder.textemail = (TextView) convertView.findViewById(R.id.txtemail);
			holder.textpwd = (TextView) convertView.findViewById(R.id.txtpwd);
			holder.textetat = (TextView) convertView.findViewById(R.id.txtetat);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.textid.setText(users.get(position).getId()+"");
		holder.textemail.setText(users.get(position).getEmail());
		holder.textpwd.setText(users.get(position).getPassword());
		holder.textetat.setText(users.get(position).getEtat()+"");

		return convertView;
		
	}

}
