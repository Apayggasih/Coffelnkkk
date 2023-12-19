package com.uaspm2.coffelnkk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.google.firebase.database.getValue
import com.google.firebase.storage.storage
import com.uaspm2.coffelnkk.databinding.ItemKafeBinding
import com.uaspm2.coffelnkk.databinding.ItemListBeansBinding
import com.uaspm2.coffelnkk.databinding.ItemListVertikalBinding
import com.uaspm2.coffelnkk.databinding.ItemRecipeBinding
import java.io.Serializable

class BeansAdapter(
    val onItemClick: (post: DeskripsiInfo) -> Unit,
) : ListAdapter<DeskripsiInfo, BeansAdapter.ViewHolder>(DeskripsiInfoDiffCallback()) {

    init {
        val database = Firebase.database.getReference("beans").get().addOnSuccessListener {
            val list = it.getValue<List<DeskripsiInfo>>()!!
            submitList(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBeansBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        val binding: ItemListBeansBinding,
        val onItemClick: (post: DeskripsiInfo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: DeskripsiInfo) {
            binding.itemJudul.setText(post.title)
            binding.itemDeskripsi.setText(post.content)
            Firebase.storage.getReference(post.image).downloadUrl.addOnSuccessListener{
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.itemGambar)
            }
            binding.root.setOnClickListener {
                onItemClick(post)
            }
        }
    }

}

class ManualBrewAdapter(
    val onItemClick: (post: DeskripsiInfo) -> Unit,
) : ListAdapter<DeskripsiInfo, ManualBrewAdapter.ViewHolder>(DeskripsiInfoDiffCallback()) {

    init {
        val database = Firebase.database.getReference("manualbrew").get().addOnSuccessListener {
            val list = it.getValue<List<DeskripsiInfo>>()!!
            submitList(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListVertikalBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }

    class ViewHolder(
        val binding: ItemListVertikalBinding,
        val onItemClick: (post: DeskripsiInfo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: DeskripsiInfo) {
            binding.itemJudul.setText(post.title)
            binding.itemDeskripsi.setText(post.content)
            Firebase.storage.getReference(post.image).downloadUrl.addOnSuccessListener{
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.itemGambar)
            }
            binding.root.setOnClickListener {
                onItemClick(post)
            }
        }
    }
}

data class DeskripsiInfo(
    var image: String = "",
    var title: String = "",
    var rating: Int = 0,
    var type: String = "",
    var typee: String = "",
    var category: String = "",
    var content: String = "",
    var cara: List<String> = listOf(),
    var bahan: List<String> = listOf()
): Serializable

class RecipeAdapter(
    category: String,
    private val onItemClick: (post: DeskripsiInfo) -> Unit,
) : ListAdapter<DeskripsiInfo, RecipeAdapter.ViewHolder>(DeskripsiInfoDiffCallback()) {

    private val recipeListLiveData = MutableLiveData<List<DeskripsiInfo>>()

    init {
        val databaseReference = Firebase.database.getReference("recipe").child(category)

        // Use addValueEventListener for realtime updates
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = mutableListOf<DeskripsiInfo>()

                for (childSnapshot in snapshot.children) {
                    // Periksa apakah childSnapshot memiliki nilai
                    val deskripsiInfo = childSnapshot.getValue(DeskripsiInfo::class.java)
                    deskripsiInfo?.let {
                        list.add(it)
                    }
                }

                recipeListLiveData.postValue(list)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        val binding: ItemRecipeBinding,
        val onItemClick: (post: DeskripsiInfo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: DeskripsiInfo) {
            binding.itemJudul.setText(post.title)
            Firebase.storage.getReference(post.image).downloadUrl.addOnSuccessListener {
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.itemGambar)
            }
            binding.root.setOnClickListener {
                onItemClick(post)
            }
        }
    }

    fun observeRecipeList(owner: LifecycleOwner, observer: Observer<List<DeskripsiInfo>>) {
        recipeListLiveData.observe(owner, observer)
    }
}

class LogoAdapter(
    val onItemClick: (post: DeskripsiInfo) -> Unit,
) : ListAdapter<DeskripsiInfo, LogoAdapter.ViewHolder>(DeskripsiInfoDiffCallback()) {

    init {
        val database = Firebase.database.getReference("manualbrew").get().addOnSuccessListener {
            val list = it.getValue<List<DeskripsiInfo>>()!!
            submitList(list)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemKafeBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        val binding: ItemKafeBinding,
        val onItemClick: (post: DeskripsiInfo) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: DeskripsiInfo) {
            binding.itemJudul.setText(post.title)
            Firebase.storage.getReference(post.image).downloadUrl.addOnSuccessListener{
                Glide.with(binding.root.context)
                    .load(it)
                    .into(binding.itemGambar)
            }
            binding.root.setOnClickListener {
                onItemClick(post)
            }
        }
    }
}


class DeskripsiInfoDiffCallback : DiffUtil.ItemCallback<DeskripsiInfo>() {
    override fun areItemsTheSame(oldItem: DeskripsiInfo, newItem: DeskripsiInfo): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DeskripsiInfo, newItem: DeskripsiInfo): Boolean {
        return oldItem == newItem
    }
}