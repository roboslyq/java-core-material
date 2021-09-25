package com.roboslyq.tools.compare;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VersionCompare {
	private static final String DIFF_REPORT_HEAD = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>diff report</title><style type=\"text/css\">body {background:#FFFFFF;} .from {border:1px solid #A67901;background:#EAEAEA; margin:10px} .to {border:1px solid #A67901;background:#EAEAEA; margin:10px}</style></head><body>";
	private static final String DIFF_REPORT_TAIL = "</body></html>";
	
	private File fromRootDir;
	private File toRootDir;
	private File diffList;
	private File diffReport;
	private VersionCompareFilenameFilter filter;
	
	public VersionCompare() {
		
	}
	
	public VersionCompare(String fromRootDir, String toRootDir, String project, String outputDir) {
		this.fromRootDir = new File(fromRootDir + project);
		this.toRootDir = new File(toRootDir + project);
		this.diffList = new File(outputDir + "diffList_" + project + ".txt");
		this.diffReport = new File(outputDir + "diffReport_" + project + ".html");

		if (diffList.exists()) {
			diffList.delete();
		}
		if (diffReport.exists()) {
			diffReport.delete();
		}
	}
	
	public void compare() throws IOException {
		appendFile(diffReport, DIFF_REPORT_HEAD);
		
		compare(fromRootDir);		
		if (diffList.exists()) {
			appendFile(diffReport, DIFF_REPORT_TAIL);
		} else if (diffReport.exists()) {
			diffReport.delete();
		}
	}
	
	public void compare(File f) throws IOException {
		if (!f.exists()) {
			return;
		}
		String fileName = f.getName();
		if ("CVS".equals(fileName) || fileName.startsWith(".") || fileName.endsWith(".class")) {
			return;
		}
		
		if (f.isDirectory()) {
			/*
			if (",deployment,".indexOf(f.getParentFile().getName()) != -1 && ",profile4M99,profile4SIT,profile4SIT1,profile4SIT2,profile4UAT1,profile4UAT2,profile4Test,".indexOf(f.getName()) != -1) {
				return;
			}
			*/
			/*
			if (f.getName().indexOf("demo-") != -1) {
				return;
			}
			*/
		}
		
		if(f.isDirectory()) {
			File[] files = f.listFiles(filter);
			for (int i = 0; i < files.length; i++) {
				compare(files[i]);
			}
		} else {
			String form = f.getAbsolutePath();
			String to = getToPath(form);
			File t = new File(to);
			int res = compare(f, t);
			if (res > 0) {
				//appendFile(diffList, f.getAbsolutePath() + "\r\n");
				
				if (res == 2) {
					appendFile(diffList, "Add   :\t" + f.getAbsolutePath() + "\r\n");
				} else {
					appendFile(diffList, "Modify:\t" + f.getAbsolutePath() + "\r\n");
				}
				
			}
		}
	}
	
	public int compare(File f, File t) throws IOException {
		if(!t.exists()) {
			appendFile(diffReport, diffReport(f, t).toString());
			return 2;
		}
		
		if (!FileUtils.contentEquals(f, t)) {
			//appendFile(diffReport, diffReport(f, t).toString());
			/*
			if (isSrcFile(f.getName()) && isSrcFile(t.getName())) {
				int i = compareContent(f, t, "UTF-8");
				if (i != 0) {
					appendFile(diffReport, deltaReport(f, t, "UTF-8").toString());
				}
				return i;
			} else {
				return 1;
			}
			*/
			/*
			if (isSrcFile(f.getName()) && isSrcFile(t.getName())) {
				appendFile(diffReport, deltaReport(f, t, "UTF-8").toString());
			}
			*/
			
			if (isSrcFile(f.getName()) && isSrcFile(t.getName())) {
				//if (compareContent(f, t, "UTF-8") != 0) {
					appendFile(diffReport, diffReport(f, t).toString());
					appendFile(diffReport, deltaReport(f, t, "UTF-8").toString());
				//}
			} else {
				appendFile(diffReport, diffReport(f, t).toString());
			}
			return 1;
		}
		
		return 0;
	}
	
	private int compareContent(File f, File t, String encoding) throws IOException {		
		FileInputStream fInput = new FileInputStream(f);
		FileInputStream tInput = new FileInputStream(t);
		
		String fStr = removeComment(IOUtils.toString(fInput, encoding));
		String tStr = removeComment(IOUtils.toString(tInput, encoding));

		fInput.close();
		tInput.close();
		
		return fStr.compareTo(tStr);
	}
	
	private StringBuffer diffReport(File f, File t) throws IOException {
		StringBuffer buffer = new StringBuffer("<hr />");
		boolean fExists = f.exists();
		boolean tExists = t.exists();
		if (!fExists && !tExists) {
			return buffer;
		}
		if (fExists && !tExists) {
			buffer.append("<b>Add " + f.getName() + "</b><br />");
	        buffer.append("original file: No Such File<br />");
	        buffer.append("revised  file: " + f.getAbsolutePath() + "<br />");
			return buffer;
		}
		if (!fExists && tExists) {
			buffer.append("<b>Delete " + f.getName() + "</b><br />");
	        buffer.append("original file: " + t.getAbsolutePath() + "<br />");
	        buffer.append("revised  file: No Such File<br />");
			return buffer;
		}
		
        buffer.append("<b>Modify " + f.getName() + "</b><br />");
        buffer.append("original file: " + t.getAbsolutePath() + "<br />");
        buffer.append("revised  file: " + f.getAbsolutePath() + "<br />");
		
		return buffer;
	}
	
	private StringBuffer deltaReport(File f, File t, String encoding) throws IOException {
		StringBuffer buffer = new StringBuffer();
		
		FileInputStream fInput = new FileInputStream(f);
		FileInputStream tInput = new FileInputStream(t);
		
		String brush = getBrush(f.getName());
		
		List<String> revised = IOUtils.readLines(fInput, encoding);
        List<String> original  = IOUtils.readLines(tInput, encoding);

		Patch<String> patch = DiffUtils.diff(original, revised);
        for (Delta delta: patch.getDeltas()) {
        	int position = delta.getOriginal().getPosition();
        	buffer.append("<div>").append("\n");
        	buffer.append(delta.getType()).append(",position:").append(position).append("<br />").append("\n");
        	
        	buffer.append("<div class=\"from\">").append("\n");
        	buffer.append("<pre class=\"brush: ").append(brush).append("; toolbar: false; first-line: ").append(position).append("\">").append("\n");
        	for (Object line: delta.getOriginal().getLines()) {
        		buffer.append(filterHtmlTag((String)line)).append("\n");
	        }
        	buffer.append("</pre>").append("\n");
        	buffer.append("</div>").append("\n");
        	
        	buffer.append("<div class=\"to\">").append("\n");
        	buffer.append("<pre class=\"brush: ").append(brush).append("; toolbar: false; first-line: ").append(position ).append("\">").append("\n");
        	for (Object line: delta.getRevised().getLines()) {
        		buffer.append(filterHtmlTag((String)line)).append("\n");
	        }
        	buffer.append("</pre>").append("\n");
        	buffer.append("</div>").append("\n");
        	
        	buffer.append("</div>").append("\n");
        }

		fInput.close();
		tInput.close();
		
		return buffer;
		
	}
	
	private String getToPath(String fromPath) {
		String fromRootPath = fromRootDir.getAbsolutePath();
		String toRootPath = toRootDir.getAbsolutePath();
		int i = fromPath.indexOf(fromRootPath);
		String toPath = toRootPath + fromPath.substring(i+fromRootPath.length());
		return toPath;
	}
	
	private void appendFile(File file, String content) {
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF-8"));
			fileWriter.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	private boolean isSrcFile(String filename) {
		if (".java,.biz,.mvc,.xml,.html,.jsp,.js,.css,.sh,.run,.sql,.properties,".indexOf(FilenameUtils.getExtension(filename)) != -1) {
			return true;
		}
		return false;
	}
	
	private String getBrush(String filename) {
		String ext = FilenameUtils.getExtension(filename);
		if (".java,.js,.css,".indexOf(ext) != -1) {
			return ext;
		} else {
			return "xml";
		}
	}
	
	public static String removeComment(String str) {
		String content = str;
		// remove java comment
		content = content.replaceAll("\\/\\/[^\\n]*|\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*+\\/", "");
		// remove jsp comment
		//content = content.replaceAll("<%--[^<^%^-^-]*--%>", "");
		// remove html comment
		//content = content.replaceAll("<!--[^<^!^-^-]*-->", "");
		content = content.replaceAll("\n", "");
		return content;
	}
	
	public static String filterHtmlTag( String input )
	{
		if ( input == null )
		{
			return null;
		}
		else
		{
			StringBuffer result = new StringBuffer();
			char[] charArray = input.toCharArray();
			int size = charArray.length;
			for ( int i = 0; i < size; i++ )
			{
				char current = charArray[i];
				switch ( current ) {
					case '<' :
						result.append( "&lt;" );
						break;
					case '>' :
						result.append( "&gt;" );
						break;
						
					case '\'' :
						//result.append( "&acute;" );
						result.append( "&#39;" );
						break;
					case '"' :
						result.append( "&quot;" );
						break;
					default :
						result.append( current );
						break;
				}
			}
			return result.toString();
		}
	}
	
	public static void main(String[] args) {
		try {
			
			String outputDir = "D:\\diff\\res\\";
			String fromRootDir = "D:\\diff\\source_file\\";
			String toRootDir = "D:\\diff\\to_file\\";
			
			String[] projects = {"pro1"};
			for (int i = 0; i < projects.length; i++) {
				VersionCompare vc = new VersionCompare(fromRootDir, toRootDir, projects[i], outputDir);
				vc.compare();
			}
			
			/*
			fromRootDir = "D:\\emp\\src_prod\\prod_20121117\\icbsz_uat1_20121019.src\\";
			toRootDir = "D:\\emp\\src_prod\\prod_20120922\\icbsz_prod_20120922.src\\";
			VersionCompare vc = new VersionCompare(fromRootDir, toRootDir, "", outputDir);
			vc.compare();
			*/
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class VersionCompareFilenameFilter implements FilenameFilter {

	private Pattern pattern;
	public VersionCompareFilenameFilter(String regx) {
		if (regx != null && regx.length() > 0) {
			pattern = Pattern.compile(regx);
		}
	}
	public boolean accept(File file, String s) {
		if (pattern == null) {
			return true;
		}
		Matcher m = pattern.matcher(s);
		return m.matches();
	}
	
}