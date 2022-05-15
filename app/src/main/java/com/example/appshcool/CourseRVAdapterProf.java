package com.example.appshcool;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CourseRVAdapterProf extends RecyclerView.Adapter<CourseRVAdapterProf.ViewHolder> {
    // creating variables for our ArrayList and context
    private ArrayList<CouresProf> coursesArrayList;
    private Context context;
    FirestoreRecyclerAdapter adapter;

    // creating constructor for our adapter class
    public CourseRVAdapterProf(ArrayList<CouresProf> coursesArrayList, Context context) {
        this.coursesArrayList = coursesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseRVAdapterProf.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // passing our layout file for displaying our card item
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_coursprof, parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CouresProf courses = coursesArrayList.get(position);
        holder.courseNameTV.setText(courses.getCourseName());
        holder.courseDurationTV.setText(courses.getCourseDuration());
        holder.courseDescTV.setText(courses.getCourseDescription());
        Glide.with(context)
                .load(courses.getThumb_image())
                .placeholder(R.mipmap.ic_person)
                .centerCrop()
                .into(holder.mImage);
        holder.envoyerMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // define Intent object
                // with action attribute as ACTION_SEND
                Intent intent = new Intent(Intent.ACTION_SEND);

                // add receiver to intent using putExtra function
                intent.putExtra(Intent.EXTRA_EMAIL,
                        new String[] { courses.getCourseDescription() });

                // set type of intent
                intent.setType("message/rfc822");

                // startActivity with intent with chooser
                // as Email client using createChooser function
                context.startActivity(
                        Intent
                                .createChooser(intent,
                                        "Choisir une app d'emailing :"));
            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list.
        return coursesArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our text views.
        private final TextView courseNameTV;
        private final TextView courseDurationTV;
        private final TextView courseDescTV;
        CircleImageView mImage;

        TextView envoyerMail ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDurationTV = itemView.findViewById(R.id.idTVCourseDuration);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            mImage= itemView.findViewById(R.id.im_personne);
            envoyerMail = itemView.findViewById(R.id.idTVCourseDescription);

// here we are adding on click listener
// for our item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // after clicking of the item of recycler view.
                    // we are passing our course object to the new activity.
                    CouresProf courses = coursesArrayList.get(getAdapterPosition());

                    // below line is creating a new intent.
                    Intent i = new Intent(context, updateCourseProf.class);

                    // below line is for putting our course object to our next activity.
                    i.putExtra("course", courses);

                    // after passing the data we are starting our activity.
                    context.startActivity(i);
                }
            });
        }
    }
}

