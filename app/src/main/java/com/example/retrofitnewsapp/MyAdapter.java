//package com.example.retrofitnewsapp;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.squareup.picasso.Picasso;
//
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.net.URL;
//import java.util.List;
//
//
//public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
//    private List<NewsList> newsList;
//    private View.OnClickListener mOnItemClickListener;
//    Context context;
//    String image,title;
//    public MyAdapter(Context context, List<NewsList> newsList) {
//        this.context=context;
//        this.newsList = newsList;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_news, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        NewsList ld = newsList.get(position);
//        image=ld.image;
//        title=ld.getHeadline();
//        Picasso.with(holder.newsphoto.getContext())
//                .load(image)
//                .into(holder.newsphoto);
//        holder.headline.setText(title);
//        holder.brief_summary.setText(ld.getBrief_summary());
//        holder.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                URL url = null;
//                Uri uri = null;
//                try {
//                    url = new URL(image);
//
//                    try {
//                        uri = Uri.parse( url.toURI().toString() );
//                    } catch (URISyntaxException e) {
//                        e.printStackTrace();
//                    }
//                } catch (MalformedURLException e) {
//                    e.printStackTrace();
//                }
//
//                // Uri uri = Uri.parse(image+"");
//                Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                sendIntent.setType("text/plain");
//                sendIntent.putExtra(Intent.EXTRA_TEXT, title+"");
//
//                // (Optional) Here we're setting the title of the content
//                sendIntent.putExtra(Intent.EXTRA_TITLE, "Send message");
////                String app_url = " https://play.google.com/store/apps/details?id=my.example.javatpoint";
////                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT,app_url);
////                sharingIntent.putExtra(android.content.Intent.EXTRA_STREAM, Uri.fromFile(file));
//                // (Optional) Here we're passing a content URI to an image to be displayed
//                //  sendIntent.setDataAndType(uri,"image/*");
//                //  sendIntent.setData(uri);
//                sendIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//
//                //   sendIntent.setPackage("com.whatsapp");
//                Log.e("23", "onClick: " );
//                sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//// Show the Sharesheet
//                try {
//                    context.startActivity(Intent.createChooser(sendIntent, "Send mail"));
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(context, "There are no email applications installed.", Toast.LENGTH_SHORT).show();
//                }
//                //context.startActivity(sendIntent);
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return newsList.size();
//    }
//
//    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
//        mOnItemClickListener = itemClickListener;
//    }
//
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private TextView headline, brief_summary;
//        ImageView newsphoto,share;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            newsphoto = (ImageView) itemView.findViewById(R.id.newsphoto);
//            headline = (TextView) itemView.findViewById(R.id.headline);
//            brief_summary = (TextView) itemView.findViewById(R.id.shortdescription);
//            share=(ImageView)itemView.findViewById(R.id.share);
//            itemView.setTag(this);
//            itemView.setOnClickListener(mOnItemClickListener);
//        }
//    }
//}
//
