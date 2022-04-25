package twenty20.example.baghchal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BaghChalLogic game;
    TextView outside,turn_text,remaining_text;
    int[] ids={R.id.i0,R.id.i1,R.id.i2,R.id.i3,R.id.i4,R.id.i5,R.id.i6,R.id.i7,R.id.i8,R.id.i9,R.id.i10,R.id.i11,R.id.i12,R.id.i13,R.id.i14,R.id.i15,
            R.id.i16,R.id.i17,R.id.i18,R.id.i19,R.id.i20,R.id.i21,R.id.i22,R.id.i23,R.id.i24};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outside=findViewById(R.id.outside);
        turn_text=findViewById(R.id.turn);
        remaining_text=findViewById(R.id.remaining);
        game=new BaghChalLogic(MainActivity.this,outside,turn_text,this,remaining_text);
        for(int i=0;i<25;i++)
        {
            ImageView imgg=findViewById(ids[i]);
            imgg.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {

        game.make_move((ImageView) view);
        String winner=game.checkwinner();
        if(winner.equals("goats"))
        {
            Toast.makeText(MainActivity.this,"Goats Win",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,GameOver.class);
            i.putExtra("winner","Goats");
            startActivity(i);
            finish();
        }
        if(winner.equals("lions"))
        {
            Toast.makeText(MainActivity.this,"Lions Win",Toast.LENGTH_SHORT).show();
            Intent i=new Intent(MainActivity.this,GameOver.class);
            i.putExtra("winner","Lions");
            startActivity(i);
            finish();
        }


    }
}