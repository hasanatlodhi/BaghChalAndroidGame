package twenty20.example.baghchal;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class BaghChalLogic {

    int outside_goats=20,remaining_goat=20;
    int [][] possible_moves,kill_moves,killing_array;
    ImageView last_pos_lion,last_pos_goat;
    boolean lion_selected=false,goat_selected=false;

    Activity activity;
    Context con;
    String turn="goat";
    TextView outside,turn_text,remaining_text;
    int[] ids={R.id.i0,R.id.i1,R.id.i2,R.id.i3,R.id.i4,R.id.i5,R.id.i6,R.id.i7,R.id.i8,R.id.i9,R.id.i10,R.id.i11,R.id.i12,R.id.i13,R.id.i14,R.id.i15,
            R.id.i16,R.id.i17,R.id.i18,R.id.i19,R.id.i20,R.id.i21,R.id.i22,R.id.i23,R.id.i24};
    BaghChalLogic(Context con, TextView outside, TextView turn_text, Activity activity,TextView remaining_text)
    {
            this.con=con;
            this.outside=outside;
            this.turn_text=turn_text;
            this.activity=activity;
            this.remaining_text=remaining_text;
        possible_moves= new int[][]{{},{2,6,7},{1,3,7},{2,7,8,9,4},{3,9,5},{4,9,10},{1,7,11},{6,8,1,2,3,12,11,13},
        {3,7,9,13},{3,4,5,8,10,13,14,15},{5,9,15},{6,16,12,7,17},{11,13,7,17},
        {7,8,9,12,14,17,18,19},{9,13,15,19},{9,10,14,19,20},
        {17,11,21},{11,12,13,16, 18,21,22,23},{13,17,19,23},
        {13,14,15,18,20,23,24,25},{15,19,25},{16,17,22},
        {17,21,23},{17,18,19,22,24},{23,19,25},{24,19,20}
        };
        kill_moves=new int[][]{{},{11,3,13},{4,12},{1,11,13,5,15}, {2,14},{3,15,13},
        {8, 16} ,{9,19,17}, {6,10, 18},{7,19,17},{20,8},{21,13,23,3,1},{2,22,14},{1,3,5,11,15,21,23,25}, {4,24, 12},
        {5,25,13,3,23}, {6,18}, {7,9,19}, {8,16,20},{7,9,17}, {10,18}, {11,13,23},{12,24},{13,21,25, 15,11},{14,22},{13,15,23}};

        killing_array= new int[][]{{},{6, 2, 7}, {3, 7}, {2, 7, 8, 4, 9}, {3, 9}, {4, 10, 9},
        {7, 11}, {8, 13, 12}, {7, 9, 13}, {8, 14, 13}, {15, 9}, {16, 12, 17, 7, 6}, {7, 17, 13},
        {7, 8, 9, 12, 14, 17, 18, 19}, {9, 19, 13}, {10, 20, 14, 9, 19}, {11, 17}, {12, 13, 18}, {13, 17, 19}, {13, 14, 18}, {15, 19}, {16, 17, 22}, {17, 23}, {18, 22, 24, 19, 17}, {19, 23}, {19, 20, 24}
        };
    }

    void make_move(ImageView view )
    {

        if(turn.equals("goat"))
        {
            if(outside_goats>0)
            {
                if(!view.getTag().toString().equals("empty"))
                {
                    Toast.makeText(con, "Please Choose empty space", Toast.LENGTH_SHORT).show();
                    return;
                }
                view.setImageResource(R.drawable.goat);
                outside_goats--;
                outside.setText(""+outside_goats);
                turn_text.setText("Turn: Lion");
                view.setTag("goat");
                turn="lion";
            }
            else
            {
                if (!goat_selected && view.getTag().toString().equals("goat"))
                {
                    goat_selected=true;
                    last_pos_goat=view;
                }
                else if(view.getTag().toString().equals("goat") && goat_selected)
                {
                    last_pos_lion=view;
                }
                else if(goat_selected && view.getTag().toString().equals("empty"))
                {
                    int lastindex=0,newindex=0,i=1;
                    for (int id: ids) {
                        if(last_pos_goat.getId()==id)
                        {
                            lastindex=i;
                        }
                        if(view.getId()==id)
                        {
                            newindex=i;
                        }

                        i++;

                    }
                    boolean valid=checkpossiblemoves(lastindex,newindex);
                    if(valid)
                    {
                        last_pos_goat.setImageDrawable(null);
                        last_pos_goat.setTag("empty");
                        view.setTag("goat");
                        view.setImageResource(R.drawable.goat);
                        goat_selected=false;
                        turn="lion";
                        turn_text.setText("Turn: Lion");
                    }

                    else {
                        goat_selected=false;
                        Toast.makeText(con, "Invalid move", Toast.LENGTH_SHORT).show();
                    }
                }
                else if(goat_selected && !view.getTag().toString().equals("empty"))
                {
                    goat_selected=false;

                    Toast.makeText(con, "Invalid Move", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(con, "Please select Goat", Toast.LENGTH_SHORT).show();
                }
            }
        }
        else
        {
            if(view.getTag().toString().equals("lion") && !lion_selected)
            {
                lion_selected=true;
                last_pos_lion=view;
            }
            else if(view.getTag().toString().equals("lion") && lion_selected)
            {
                last_pos_lion=view;
            }
            else if(lion_selected && view.getTag().toString().equals("empty"))
            {
                int lastindex=0,newindex=0,i=1;
                for (int id: ids) {
                    if(last_pos_lion.getId()==id)
                    {
                        lastindex=i;
                    }
                    if(view.getId()==id)
                    {
                        newindex=i;
                    }

                    i++;

                }

                boolean valid=checkpossiblemoves(lastindex,newindex);
                if(valid)
                {
                    last_pos_lion.setImageDrawable(null);
                    last_pos_lion.setTag("empty");
                    view.setTag("lion");
                    view.setImageResource(R.drawable.lion);
                    lion_selected=false;
                    turn="goat";
                    turn_text.setText("Turn: Goat");
                }
                else {
                    int j=0;
                    for (int no:kill_moves[lastindex]) {

                        if (no==newindex)
                        {
                            int killed=killing_array[lastindex][j];
                            ImageView v=this.activity.findViewById(ids[killed-1]);
                            if(!v.getTag().toString().equals("goat"))
                            {
                                Toast.makeText(con, "Invalid Move", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            v.setImageDrawable(null);
                            v.setTag("empty");
                            last_pos_lion.setImageDrawable(null);
                            last_pos_lion.setTag("empty");
                            view.setTag("lion");
                            view.setImageResource(R.drawable.lion);
                            lion_selected=false;
                            turn="goat";
                            turn_text.setText("Goat");

                            Toast.makeText(con, "Goat Killed", Toast.LENGTH_SHORT).show();
                            remaining_goat--;
                            remaining_text.setText("Remaining Goats: "+remaining_goat);
                            return;
                        }
                        j++;
                        }

                    lion_selected=false;
                    Toast.makeText(con, "Invalid move", Toast.LENGTH_SHORT).show();
                }
            }
            else if(lion_selected && !view.getTag().toString().equals("empty"))
            {
                lion_selected=false;

                Toast.makeText(con, "Invalid Move", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(con, "Please select lion", Toast.LENGTH_SHORT).show();
            }

        }


    }

    boolean checkpossiblemoves(int lastindex, int newindex){
        int [] posmoves=possible_moves[lastindex];
        boolean valid=false;
        for (int no:posmoves) {
            if (no==newindex)
            {
                valid=true;
                break;
            }
        }
        return  valid;

    }
    String checkwinner() {
        if (remaining_goat < 1) {
            return "lions";
        }

        int[] lionpositions = new int[4];
        int j = 0;
        for (int i = 0; i < ids.length; i++) {
            ImageView vv = this.activity.findViewById(ids[i]);
            if (vv.getTag().equals("lion")) {
                lionpositions[j++] = i + 1;
            }
        }

        for (int pos : lionpositions) {
            int[] allmoves = possible_moves[pos];
            int[] allkillmoves = kill_moves[pos];
            for (int move : allmoves) {
                ImageView vv = this.activity.findViewById(ids[move-1]);
                if(vv.getTag().toString().equals("empty"))
                {
                    return "none";
                }
            }
            for (int idd : allkillmoves) {
                ImageView vv = this.activity.findViewById(ids[idd-1]);
                if(vv.getTag().toString().equals("empty"))
                {
                    return "none";
                }
            }

        }
        return "goats";
    }
}
