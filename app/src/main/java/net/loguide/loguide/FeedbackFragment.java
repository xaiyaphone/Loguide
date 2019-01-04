package net.loguide.loguide;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackFragment extends Fragment {
    private EditText name;
    private EditText email;
    private EditText subject;
    private EditText message;

    private Button feedback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feedback,container,false);

         name = view.findViewById(R.id.name_feedback);
         email = view.findViewById(R.id.email_feddback);
         subject = view.findViewById(R.id.subject_feedback);
         message = view.findViewById(R.id.message_feedback);

         feedback = view.findViewById(R.id.btn_feedback);

         feedback.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 send_click();
             }
         });

        return view;
    }
    private void send_click (){
        if (name.getText().toString().equals("")){
            name.setError("Please enter your name");
        }else if (email.getText().toString().equals("")){
            email.setError("Please enter your email");
        }else if (subject.getText().toString().equals("")){
            subject.setError("Please enter your subject");
        }else if (message.getText().toString().equals("")){
            message.setError("Please enter your message");
        }else{
            Intent i = new Intent(Intent.ACTION_SENDTO);
            i.setData(Uri.parse("mailto:loguideceitsw18@gmail.com"));
            //i.putExtra(Intent.EXTRA_EMAIL,new String[]{"loguideceitsw18@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString());
            i.putExtra(Intent.EXTRA_TEXT,"dear Loguide team,\n" + message.getText().toString()+"\n regards, "
                    + name.getText().toString());

            try {
                startActivity(Intent.createChooser(i,"send mail"));
            }catch (android.content.ActivityNotFoundException ex){
                Toast.makeText(getActivity(),"No mail app found",Toast.LENGTH_SHORT).show();
            }catch(Exception ex){
                Toast.makeText(getActivity(),"unexpected error"+ ex.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }


}
