package com.pifss.bbadmin;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PIFSS on 3/30/2017.
 */

public class DonorsAdapter extends BaseAdapter{

    private final LayoutInflater inflater;


    Activity context;
    List<Donor> donorList;
    List<OnSiteDonor> onSiteList;

    public DonorsAdapter(Activity context, List<Donor> donerList, List<OnSiteDonor> onSiteList) {
        this.context = context;
        this.donorList = donerList;
        this.onSiteList = onSiteList;
        inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return donorList.size()+onSiteList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final View v;
        v = inflater.inflate(R.layout.one_cell_priodinfo, null);
        TextView txtName = (TextView) v.findViewById(R.id.txtName);
        TextView txtType = (TextView) v.findViewById(R.id.txtDonorType);
//        ImageView imgGender = (ImageView) v.findViewById(R.id.imgGender);
        ImageView imgBloodType = (ImageView) v.findViewById(R.id.imgBloodType);




        if (position<donorList.size()) {
            Donor donor = donorList.get(position);

            txtName.setText(donor.getFirstName() + " " + donor.getLastName());
            txtType.setText("      ");


            if (donor.getBloodType().equalsIgnoreCase("o+") || donor.getBloodType().equalsIgnoreCase("O+")) {
                imgBloodType.setImageResource(R.mipmap.o_plus);
            } else if (donor.getBloodType().equalsIgnoreCase("o-") || donor.getBloodType().equalsIgnoreCase("O-")) {
                imgBloodType.setImageResource(R.mipmap.o_minus);
            } else if (donor.getBloodType().equalsIgnoreCase("b+") || donor.getBloodType().equalsIgnoreCase("B+")) {
                imgBloodType.setImageResource(R.mipmap.b_plus);
            } else if (donor.getBloodType().equalsIgnoreCase("b-") || donor.getBloodType().equalsIgnoreCase("B-")) {
                imgBloodType.setImageResource(R.mipmap.b_minus);
            } else if (donor.getBloodType().equalsIgnoreCase("ab-") || donor.getBloodType().equalsIgnoreCase("AB-")) {
                imgBloodType.setImageResource(R.mipmap.ab_minus);
            } else if
                    (donor.getBloodType().equalsIgnoreCase("ab+") || donor.getBloodType().equalsIgnoreCase("AB+")) {
                imgBloodType.setImageResource(R.mipmap.ab_plus);
            } else if (donor.getBloodType().equalsIgnoreCase("a-") || donor.getBloodType().equalsIgnoreCase("A-")) {
                imgBloodType.setImageResource(R.mipmap.a_minus);
            } else if (donor.getBloodType().equalsIgnoreCase("a+") || donor.getBloodType().equalsIgnoreCase("a+")) {
                imgBloodType.setImageResource(R.mipmap.a_plus);
            }

//            if (donor.getGender().equalsIgnoreCase("F") || donor.getGender().equalsIgnoreCase("f")) {
//                imgGender.setImageResource(R.mipmap.ic_female);
//            } else if (donor.getGender().equalsIgnoreCase("M") || donor.getGender().equalsIgnoreCase("m")) {
//                imgGender.setImageResource(R.mipmap.ic_male);
//            }
        }
        else{
            OnSiteDonor onsite = onSiteList.get(position%onSiteList.size());
            txtName.setText(onsite.getName());
            txtType.setText(onsite.getBloodType());
            txtType.setText("onsite");

               if (onsite.getBloodType().equalsIgnoreCase("o+")|| onsite.getBloodType().equalsIgnoreCase("O+")){
                   imgBloodType.setImageResource(R.mipmap.o_plus);
               }
               else if (onsite.getBloodType().equalsIgnoreCase("o-")|| onsite.getBloodType().equalsIgnoreCase("O-")){
                   imgBloodType.setImageResource(R.mipmap.o_minus);
               }
               else if (onsite.getBloodType().equalsIgnoreCase("b+")|| onsite.getBloodType().equalsIgnoreCase("B+")){
                   imgBloodType.setImageResource(R.mipmap.b_plus);
               }
               else if (onsite.getBloodType().equalsIgnoreCase("b-")|| onsite.getBloodType().equalsIgnoreCase("B-")){
                   imgBloodType.setImageResource(R.mipmap.b_minus);
               }
               else if (onsite.getBloodType().equalsIgnoreCase("ab-")|| onsite.getBloodType().equalsIgnoreCase("AB-")){
                   imgBloodType.setImageResource(R.mipmap.ab_minus);
               }
               else if
                       (onsite.getBloodType().equalsIgnoreCase("ab+")|| onsite.getBloodType().equalsIgnoreCase("AB+")){
                   imgBloodType.setImageResource(R.mipmap.ab_plus);
               }
               else if (onsite.getBloodType().equalsIgnoreCase("a-")|| onsite.getBloodType().equalsIgnoreCase("A-")){
                   imgBloodType.setImageResource(R.mipmap.a_minus);
               }
               else if (onsite.getBloodType().equalsIgnoreCase("a+")|| onsite.getBloodType().equalsIgnoreCase("a+")){
                   imgBloodType.setImageResource(R.mipmap.a_plus);
               }

//               if (onsite.getGender().equalsIgnoreCase("F") || onsite.getGender().equalsIgnoreCase("f")){
//                   imgGender.setImageResource(R.mipmap.ic_female);
//               }
//               else if (onsite.getGender().equalsIgnoreCase("M") || onsite.getGender().equalsIgnoreCase("m")){
//                   imgGender.setImageResource(R.mipmap.ic_male);
//               }
        }

        final int position2 = position;
        RelativeLayout cell = (RelativeLayout) v.findViewById(R.id.oneCellLayout);

        cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DonorInfo.class);

                if (position2<donorList.size()) {
                    Donor donor = donorList.get(position);
                    i.putExtra("name", donor.getFirstName()+" "+donor.getLastName());
                    i.putExtra("civilID", donor.getCivilId());
                    i.putExtra("gender", donor.getGender());
                    i.putExtra("bloodType", donor.getBloodType());
                    i.putExtra("phone", donor.getPhoneNumber());
                    i.putExtra("email", donor.getEmail());

                    v.getContext().startActivity(i);
                }

                else{
                    OnSiteDonor onsite = onSiteList.get(position%onSiteList.size());
                    i.putExtra("name", onsite.getName());
                    i.putExtra("civilID", onsite.getCivilId());
                    i.putExtra("gender", onsite.getGender());
                    i.putExtra("bloodType", onsite.getBloodType());
                    i.putExtra("phone", onsite.getPhone());
                    i.putExtra("email", onsite.getEmail());

                    v.getContext().startActivity(i);
                }
            }
        });


        return v;
    }
}
