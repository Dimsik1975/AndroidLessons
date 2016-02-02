package ee.example.currencylistsample;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MainActivity extends ListActivity 
{
	final String KEY_NAME_CURRENCY = "currency";
	final String KEY_NAME_RATE = "rate";

	private String[] currencies_array;
	private String[] rates_array;

	ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		currencies_array = getResources().getStringArray(R.array.currencies);
		rates_array = getResources().getStringArray(R.array.rates);
		
	    Map<String, Object> m;
	    for (int i = 0; i < currencies_array.length; i++)
	    {
	    	m = new HashMap<String, Object>();
	    	m.put(KEY_NAME_CURRENCY, currencies_array[i]);
	    	m.put(KEY_NAME_RATE, rates_array[i]);
	    	data.add(m);
	    }		
	    String[] from = { KEY_NAME_CURRENCY, KEY_NAME_RATE };
	    int[] to = { R.id.textView1, R.id.textView2 };
	    SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.layout_list_item, from, to);
	    this.setListAdapter(sAdapter);
	}
	
	@Override 
	protected void onListItemClick (ListView l, View v, int position, long id)
	{
		Toast.makeText(this, "Clicked currency: " + currencies_array[position] + 
				", rate = " + rates_array[position], 
				Toast.LENGTH_SHORT).show();
	}
} 