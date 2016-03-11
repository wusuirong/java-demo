package com.sherwin.examples.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 鎻愪緵zip鏂囦欢镄勮В铡嬬缉鎺ュ彛锛?AdapterZipFile锛?
 * 杈揿叆锛歾ipFileName锛坺ip鏂囦欢镄勭粷瀵硅矾寰勶级锛宱utputDirectory锛坺ip鏂囦欢瑙ｅ帇缂╁悗镄勫瓨鏀捐矾寰勶级 杈揿嚭锛?璇存槑锛氩垵濮嫔寲鍑芥暟
 * unZipFile锛?杈揿叆锛氭棤 杈揿嚭锛?璇存槑锛氲В铡嬬缉zip鏂囦欢锛岃В铡嬬缉鍚庡瓨鏀惧埌outputDirectory璺缎涓?GetZipFileList锛?
 * 杈揿叆锛氭棤 杈揿嚭锛歭ist 璇存槑锛氲В铡嬬缉鍚庣殑鏂囦欢鍒楄〃缁濆璺缎瀛樻斁鍦ˋrrayList锷ㄦ€佹暟缁勪腑
 */
public abstract class ZipUtils {

	/**
	 * 瑙ｅ帇缂╂枃浠?
	 * 濡傛灉outputDierctory涓簄ull锛岃В铡嫔埌zipFileName鏂囦欢镄勫綋鍓岖洰褰曚笅
	 * @param zipFileName zip鏂囦欢镄勭粷瀵硅矾寰?
	 * @param outputDirectory zip鏂囦欢瑙ｅ帇缂╁悗镄勫瓨鏀捐矾寰?
	 */
	public static void unZipFile(String zipFileName, String outputDirectory) {
		if (zipFileName == null) {
			return;
		}

		if (outputDirectory == null) {
			outputDirectory = zipFileName.substring(0, zipFileName.replace('\\', '/').lastIndexOf("/"));
		}

		File file = new File(zipFileName);
		if (!file.exists()) {
			return;
		}
		createDirectory(outputDirectory, "");

		try {
			ZipFile zipFile = new ZipFile(zipFileName);
			Enumeration entries = zipFile.entries();
			ZipEntry zipEntry = null;
			while (entries.hasMoreElements()) {
				zipEntry = (ZipEntry) entries.nextElement();
				if (zipEntry.isDirectory()) {
					String name = zipEntry.getName();
					name = name.substring(0, name.length() - 1);
					File f = new File(outputDirectory + File.separator + name);
					f.mkdir();
				} else {
					String fileName = zipEntry.getName();
					fileName = fileName.replace('\\', '/');
					// 鏂囦欢鍦ㄥ灞傜洰褰曚笅 杩欐椂闇€瑕佸垱寤虹洰褰曞锛歞1/d2/1.txt
					if (fileName.indexOf("/") != -1) {
						createDirectory(outputDirectory, fileName.substring(0, fileName.lastIndexOf("/")));
						fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
					}

					File f = new File(outputDirectory + File.separator + zipEntry.getName());

					f.createNewFile();
					InputStream in = zipFile.getInputStream(zipEntry);
					FileOutputStream out = new FileOutputStream(f);

					byte[] by = new byte[4096];
					int c;
					while ((c = in.read(by)) != -1) {
						out.write(by, 0, c);
					}
					out.close();
					in.close();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// 鍒涘缓鐩綍
	private static void createDirectory(String directory, String subDirectory) {
		String dir[];
		File fl = new File(directory);
		try {
			if (subDirectory == "" && fl.exists() != true)
				fl.mkdirs();
			else if (subDirectory != "") {
				dir = subDirectory.replace("\\", "/").split("/");
				for (int i = 0; i < dir.length; i++) {
					File subFile = new File(directory + File.separator + dir[i]);
					if (!subFile.exists())
						subFile.mkdir();
					directory += File.separator + dir[i];
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
