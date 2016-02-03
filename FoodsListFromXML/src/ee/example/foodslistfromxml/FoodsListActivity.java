package ee.example.foodslistfromxml;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.HashMap;

public class FoodsListActivity extends ListActivity
{
	final String KEY_NAME_NAME = "name";
	final String KEY_NAME_CALORIES = "calories";
	
	private static final String TAG = "MainActivity";
	ArrayList<HashMap<String, String>> alData = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> map;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		try
		{
			XmlPullParser foodsParser = getResources().getXml(R.xml.foods);
			int eventType = foodsParser.getEventType();
			while (eventType != XmlPullParser.END_DOCUMENT) 
			{
				if (eventType==XmlPullParser.START_TAG &&
						(foodsParser.getName().contains(KEY_NAME_NAME) || foodsParser.getName().contains(KEY_NAME_CALORIES))) 
				{
					String sTagName = foodsParser.getName();
					foodsParser.next();
					switch (sTagName)
					{
						case KEY_NAME_NAME:
							map = new HashMap<String, String>();
							map.put(KEY_NAME_NAME, foodsParser.getText());
							break;
						case KEY_NAME_CALORIES:
							map.put(KEY_NAME_CALORIES, foodsParser.getText());
							alData.add(map);						
							break;
					}
				}
				eventType = foodsParser.next();
			}
		}
		catch (Throwable t)
		{
			Log.v(TAG, "Error XML-file loading: " + t.toString());
			Toast.makeText(this, "Error XML-file loading: " + t.toString(), Toast.LENGTH_LONG)
			.show();
		}
		SimpleAdapter adapter = new SimpleAdapter(this, alData, android.R.layout.simple_list_item_2, 
		        new String[] { KEY_NAME_NAME, KEY_NAME_CALORIES }, 
		        new int[] { android.R.id.text1, android.R.id.text2 });
		setListAdapter(adapter);
	}
}