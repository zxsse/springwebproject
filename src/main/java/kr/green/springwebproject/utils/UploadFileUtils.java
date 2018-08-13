package kr.green.springwebproject.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	public static String uploadFile(String uploadPath, String originalName, byte[] 	
			fileData)throws Exception{
		//동일한 파일을 업로드 했을 때 구분하기위한 고유 범용 식별자를 생성
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() +"_" + originalName;
		//서버에 저장할 파일이름설정하는데 고유범용식별자_파일명
		String savedPath = calcPath(uploadPath);
		//서버에 지정한폴더의 저장하기위해 만든폴더(하위폴더)경로
		//하위폴더가없으면 서버에 지정한폴더에 많은 파일이 업로드되어 성능저하 따라서 하위 폴더를 생성해서 파일들을 분할관리
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		return uploadFileName;
	}
	
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		//file.separator : \\(windows)
		String yearPath = File.separator+cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		// \\2018\\06
		String datePath = monthPath + File.separator 
            + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		// \\2018\\06\\28
		makeDir(uploadPath, yearPath, monthPath, datePath);
		//폴더생성  : 서버경로밑에 년도폴더 생성 그밑에 달폴더 생성 그밑에 일폴더 생성 
		return datePath;
 
	}
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(paths[paths.length-1]).exists()) //일폴더가 생성되어잇으면 작업하지않고 종료 
			return;
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			if( !dirPath.exists())
				dirPath.mkdir();
		}
	}
	private static String makeIcon(String uploadPath, String path, String fileName)
        	throws Exception{
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}