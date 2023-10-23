package first_led_project.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import first_led_project.dao.MovieDAO;

// db에 있는 포스터와 스틸컷 url을 img 폴더에 모두 다운로드 받아줌
public class DataDownload {
		private MovieDAO dao = MovieDAO.getInstance();
		private static ArrayList<String> arr = new ArrayList<>();
		private static InputStream in;
		private static OutputStream out;
		private static Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		
		
		
		public void stillcutDownload() {
			for(int i = 1; i <= 19; i++){
				map.put("movie"+i, dao.getStillcut("movie"+i));
			}
			int posterNum = 1;
			String fileName = "stillcut";
			File rootFolder = new File("./img");
			File folder = new File("./img/stillcut");
			if(!rootFolder.exists()) {
				rootFolder.mkdir();
			}
			if(!folder.exists()) {
				folder.mkdir();
			}
			try {
				for(int i=1; i<=map.size(); i++) {
					ArrayList<String> stillcut = map.get("movie"+i);
					for(String src : stillcut) {
						URL url = new URL(src);
						URLConnection urlConn = url.openConnection();
						String conType = urlConn.getContentType();
						String[] conT = conType.split("/");
						if(conT[1].equals("jpeg")) {
							conT[1] = "jpg";
						}
						in = urlConn.getInputStream();
						out = new FileOutputStream("./img/stillcut/"+fileName+"_"+i+"_"+posterNum+"."+conT[1]);
						int data;
						while((data = in.read()) != -1) {
							out.write(data);
						}
						in.close();
						out.close();
						
						posterNum++;
					} 
					posterNum = 1;
				}
			}catch (Exception e) {
					e.printStackTrace();
			}finally {
				try {
					if(in!=null)in.close();
					if(out!=null)out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		public void posterDownload() {
			arr = dao.getData("movie_poster");
			int posterNum = 1;
			String fileName = "poster";
			File rootFolder = new File("./img");
			File folder = new File("./img/poster");
			if(!rootFolder.exists()) {
				rootFolder.mkdir();
			}
			if(!folder.exists()) {
				folder.mkdir();
			}
			try {
				for(String src : arr) {
					URL url = new URL(src);
					URLConnection urlConn = url.openConnection();
					String conType = urlConn.getContentType();
					String[] conT = conType.split("/");
					if(conT[1].equals("jpeg")) {
						conT[1] = "jpg";
					}
					in = urlConn.getInputStream();
					out = new FileOutputStream("./img/poster/"+fileName+"_"+posterNum+"."+conT[1]);
					int data;
					while((data = in.read()) != -1) {
						out.write(data);
					}
					in.close();
					out.close();
					
					posterNum++;
				} 
			}catch (Exception e) {
					e.printStackTrace();
			}finally {
				try {
					if(in!=null)in.close();
					if(out!=null)out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		}
		
		public DataDownload() {
			posterDownload();
			stillcutDownload();
		}
		
		
		public static void main(String[] args) {
			DataDownload dataDownload = new DataDownload();
		}
	
}
