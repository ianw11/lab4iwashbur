package edu.calpoly.android.lab4;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * This class binds the visual JokeViews and the data behind them (Jokes).
 */
public class JokeListAdapter extends BaseAdapter {

	/** The application Context in which this JokeListAdapter is being used. */
	private Context m_context;

	/** The data set to which this JokeListAdapter is bound. */
	private List<Joke> m_jokeList;

	/**
	 * Parameterized constructor that takes in the application Context in which
	 * it is being used and the Collection of Joke objects to which it is bound.
	 * m_nSelectedPosition will be initialized to Adapter.NO_SELECTION.
	 * 
	 * @param context
	 *            The application Context in which this JokeListAdapter is being
	 *            used.
	 * 
	 * @param jokeList
	 *            The Collection of Joke objects to which this JokeListAdapter
	 *            is bound.
	 */
	public JokeListAdapter(Context context, List<Joke> jokeList) {
		this.m_context = context;
		this.m_jokeList = jokeList;
	}

	public int getCount() {
		return this.m_jokeList.size();
	}

	public Object getItem(int position) {
		return this.m_jokeList.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		JokeView jokeView = null;
		
		if (convertView == null) {
			jokeView = new JokeView(m_context, this.m_jokeList.get(position));
		}
		else {
			jokeView = (JokeView)convertView;
		}
		jokeView.setJoke(this.m_jokeList.get(position));
		return jokeView;
	}
}
