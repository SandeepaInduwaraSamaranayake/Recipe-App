package com.example.recipe_app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Sandeepa Induwara Samaranayake
 * Email - sandeepa.20210302@iit.ac.lk
 * IIT ID - 20210302
 * UOW ID - w1867067
 * Mobile Application Development Coursework II
 * L5 Semester 2
 * Link to demonstration video - https://drive.google.com/drive/folders/1VWpAeftr-887FDzSGDSJAB0ZthNivF2J?usp=sharing
 */

/**
 * class called ImageDescriptionAdapter that extends the RecyclerView.Adapter class.
 * defines a RecyclerView adapter for displaying a list of image-description pairs.
 *
 * @param pairs The adapter expects to receive a list of ImageDescriptionPair objects via its constructor,
 * where each object contains an image and its description.
 *
 * The ImageDescriptionAdapter class is responsible for adapting a list of ImageDescriptionPair
 * objects to be displayed in a RecyclerView.
 */
class ImageDescriptionAdapter(private val pairs: List<ImageDescriptionPair>) :
    RecyclerView.Adapter<ImageDescriptionAdapter.ViewHolder>() {

    /**
     * the ViewHolder class holds references to the views that will be used to display the data for
     * each item in the RecyclerView.
     *
     *  It has two properties: imageView and descriptionTextView, which are references to the
     *  ImageView and TextView views, respectively.
     *
     * @param itemView the View object which contains imageview and description_text_view
     *
     * In this case, the ViewHolder contains an ImageView and a TextView to display the image and
     * its description, respectively.
     */
    class ViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView )
    {
        val imageView: ImageView = itemView.findViewById( R.id.image_view )
        val descriptionTextView: TextView = itemView.findViewById( R.id.description_text_view )
    }

    /**
     * The onCreateViewHolder method inflates the layout for each item in the list and returns a new
     * ViewHolder object that holds the inflated views.
     * @param parent The parent ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds the inflated views.
     */
    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int ): ViewHolder {
        val view = LayoutInflater.from( parent.context ).inflate( R.layout.image_description_pair, parent, false )
        return ViewHolder( view )
    }

    /**
     * The onBindViewHolder method is called when a view holder is bound to a position in the RecyclerView.
     * It gets the ImageDescriptionPair object at the current position and sets the imageView's bitmap to the image in the ImageDescriptionPair object and the descriptionTextView's text to the description in the ImageDescriptionPair object.
     * @param holder The ViewHolder to be bound.
     * @param position The position of the ViewHolder in the adapter.
     */
    override fun onBindViewHolder( holder: ViewHolder, position: Int ) {
        val pair = pairs[ position ]
        holder.imageView.setImageBitmap( pair.image )
        holder.descriptionTextView.text = pair.description
    }

    /**
     * This method returns the number of pairs in the adapter.
     * @return The number of pairs in the adapter.
     */
    override fun getItemCount(): Int = pairs.size
}
