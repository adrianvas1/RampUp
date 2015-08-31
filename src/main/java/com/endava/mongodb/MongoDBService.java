package com.endava.mongodb;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class MongoDBService {

	MongoClient mongoClient = new MongoClient("localhost", 27017);
	@SuppressWarnings("deprecation")
	DB db = mongoClient.getDB("mydb");
	DBCollection coll = db.getCollection("test1");
	private OutputStream os;

	// CREATE - POST
	public void create(String name) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		BasicDBObject doc = new BasicDBObject();
		doc.put("name", name);
		doc.put("date", dateFormat.format(date));
		coll.insert(doc);
	}

	// READ - GET
	public String read(String name) {
		BasicDBObject whereQuery = new BasicDBObject();
		whereQuery.put("name", name);
		DBCursor cursor = coll.find(whereQuery);
		while (cursor.hasNext()) {
			return cursor.next().toString();
		}
		return "no data found";
	}

	// UPDATE - PATCH
	public void update(String name) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		coll.update(new BasicDBObject("name", name), new BasicDBObject("$set",
				new BasicDBObject("date", dateFormat.format(date))));
	}

	// DELETE - DELETE
	public void delete(String name) {
		coll.remove(new BasicDBObject("name", name));
	}

	// UPLOAD FILE
	public void writeToFile(InputStream is, String uploadedFileLocation) throws IOException {
		os = new FileOutputStream(new File(uploadedFileLocation));
		byte[] buffer = new byte[1048];
		int bytes = 0;
		while ((bytes = is.read(buffer)) != -1) {
			os.write(buffer, 0, bytes);
			os.flush();
            os.close();
		}
	}
}
