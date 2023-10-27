package com.driuft.random_pets_starter;


import android.view.View;

import java.util.List;

class PokemonAdapter(private val petList:List<String>) : RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val petImage: ImageView = view.findViewById(R.id.pokeImage)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_item, parent, false)
        return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imageUrl = petList[position]

        Glide.with(holder.itemView)




        .load(imageUrl)
        .centerCrop()
        .into(holder.petImage)
        }

        override fun getItemCount() = petList.size
        }
