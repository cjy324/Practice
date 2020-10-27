package ArrayListPractice;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Article {

	public int id;
	public String title;
	public String body;
	public String regDate;
	
	public Article(int id, String title, String body) {
	
		this.id = id;
		this.title = title;
		this.body = body;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd / HH:mm:ss");
		
		this.regDate = sdf.format(cal.getTime());
		
		
	}
	
}