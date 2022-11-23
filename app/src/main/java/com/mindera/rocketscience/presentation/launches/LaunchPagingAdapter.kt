package com.mindera.rocketscience.presentation.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog.Builder
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindera.rocketscience.R
import com.mindera.rocketscience.databinding.ItemLaunchBinding
import com.mindera.rocketscience.domain.entities.Launch
import com.mindera.rocketscience.presentation.launches.LaunchPagingAdapter.LaunchViewHolder
import com.mindera.rocketscience.utils.convertTimestampToDate
import com.mindera.rocketscience.utils.convertTimestampToDays
import com.mindera.rocketscience.utils.getCircularProgressDrawable

class LaunchPagingAdapter(private val webView: WebView) : PagingDataAdapter<Launch, LaunchViewHolder>(COMPARATOR) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
		return LaunchViewHolder(ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false))
	}

	override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
		val currentItem = getItem(position) ?: return
		holder.bind(currentItem)

		holder.cardView.setOnClickListener {
			val context = holder.itemView.context

			Builder(context).apply {
				setTitle(context.getString(R.string.open))
				setItems(dialogOptions) { _, which ->
					when (which) {
						0 -> currentItem.links.article?.let { webView.loadUrl(it) }
						1 -> currentItem.links.video?.let { webView.loadUrl(it) }
						2 -> currentItem.links.wikipedia?.let { webView.loadUrl(it) }
					}
				}
				show()
			}
		}
	}

	class LaunchViewHolder(private val binding: ItemLaunchBinding) : RecyclerView.ViewHolder(binding.root) {
		val cardView = binding.cardLaunch

		fun bind(launch: Launch) {
			binding.apply {
				Glide.with(itemView)
					.load(launch.links.url)
					.placeholder(getCircularProgressDrawable(itemView.context))
					.into(imgLaunchItem)

				lblLaunchItemMission.text = launch.name
				lblLaunchItemDate.text = convertTimestampToDate(launch.date)
				lblLaunchItemRocket.text = launch.rocket.name
				lblLaunchItemTime.text = convertTimestampToDays(launch.date)

				if (launch.launchSuccess == true)
					imgLaunchItemMissionResult.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_check))
				else
					imgLaunchItemMissionResult.setImageDrawable(ContextCompat.getDrawable(itemView.context, R.drawable.ic_error))
			}
		}
	}

	companion object {
		val dialogOptions = arrayOf("Article", "Video", "Wikipedia")

		private val COMPARATOR = object : DiffUtil.ItemCallback<Launch>() {
			override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean =
				oldItem.flightNumber == newItem.flightNumber

			// Data classes automatically generate an equals method.
			override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean =
				oldItem == newItem
		}
	}

}