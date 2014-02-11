package edu.calpoly.android.lab4;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class JokeView extends LinearLayout implements OnCheckedChangeListener {

	/** Radio buttons for liking or disliking a joke. */
	private RadioButton m_vwLikeButton;
	private RadioButton m_vwDislikeButton;
	
	/** The container for the radio buttons. */
	private RadioGroup m_vwLikeGroup;

	/** Displays the joke text. */
	private TextView m_vwJokeText;
	
	/** The data version of this View, containing the joke's information. */
	private Joke m_joke;
	
	/** Interface between this JokeView and the database it's stored in. */
	private OnJokeChangeListener m_onJokeChangeListener;

	/**
	 * Basic Constructor that takes only an application Context.
	 * 
	 * @param context
	 *            The application Context in which this view is being added. 
	 *            
	 * @param joke
	 * 			  The Joke this view is responsible for displaying.
	 */
	public JokeView(Context context, Joke joke) {
		super(context);
		LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.joke_view, this, true);
		this.m_vwJokeText = (TextView)findViewById(R.id.jokeTextView);
		this.m_vwLikeButton = (RadioButton)findViewById(R.id.likeButton);
		this.m_vwDislikeButton = (RadioButton)findViewById(R.id.dislikeButton);
		this.m_vwLikeGroup = (RadioGroup)findViewById(R.id.ratingRadioGroup);
		this.m_vwLikeGroup.setOnCheckedChangeListener(this);
		this.setJoke(joke);
		
		m_onJokeChangeListener = null;
	}

	/**
	 * Mutator method for changing the Joke object this View displays. This View
	 * will be updated to display the correct contents of the new Joke.
	 * 
	 * @param joke
	 *            The Joke object which this View will display.
	 */
	public void setJoke(Joke joke) {
		this.m_joke = joke;
		this.m_vwJokeText.setText(m_joke.getJoke());
		if(this.m_joke.getRating() == Joke.LIKE) {
			this.m_vwLikeButton.setChecked(true);
		}
		else if(this.m_joke.getRating() == Joke.DISLIKE) {
			this.m_vwDislikeButton.setChecked(true);
		}
		else if(this.m_joke.getRating() == Joke.UNRATED) {
			this.m_vwLikeGroup.clearCheck();
		}
	}

	public Joke getJoke() {
	   return m_joke;
	}
	
 	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (checkedId == R.id.likeButton) {
			this.m_joke.setRating(Joke.LIKE);
			notifyOnJokeChangeListener();
		}
		else if (checkedId == R.id.dislikeButton) {
			this.m_joke.setRating(Joke.DISLIKE);
			notifyOnJokeChangeListener();
		}
		else if (checkedId == -1) {
			this.m_joke.setRating(Joke.UNRATED);
			notifyOnJokeChangeListener();
		}
	}
	
	/**
	 * Mutator method for changing the OnJokeChangeListener object this JokeView
	 * notifies when the state its underlying Joke object changes.
	 * 
	 * It is possible and acceptable for m_onJokeChangeListener to be null, you
	 * should allow for this.
	 * 
	 * @param listener
	 *            The OnJokeChangeListener object that should be notified when
	 *            the underlying Joke changes state.
	 */
	public void setOnJokeChangeListener(OnJokeChangeListener listener) {
		m_onJokeChangeListener = listener;
	}

	/**
	 * This method should always be called after the state of m_joke is changed.
	 * 
	 * It is possible and acceptable for m_onJokeChangeListener to be null, you
	 * should test for this.
	 * 
	 * This method should not be called if setJoke(...) is called, since the
	 * internal state of the Joke object that m_joke references is not be
	 * changed. Rather, m_joke reference is being changed to reference a
	 * different Joke object.
	 */
	protected void notifyOnJokeChangeListener() {
		if (m_onJokeChangeListener != null) {
		   m_onJokeChangeListener.onJokeChanged(this, m_joke);
		}
	}

	/**
	 * Interface definition for a callback to be invoked when the underlying
	 * Joke is changed in this JokeView object.
	 */
	public static interface OnJokeChangeListener {

		/**
		 * Called when the underlying Joke in a JokeView object changes state.
		 * 
		 * @param view
		 *            The JokeView in which the Joke was changed.
		 * @param joke
		 *            The Joke that was changed.
		 */
		public void onJokeChanged(JokeView view, Joke joke);
	}
}
