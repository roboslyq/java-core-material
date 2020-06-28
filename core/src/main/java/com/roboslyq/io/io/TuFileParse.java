package com.roboslyq.io.io;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TU定长文件解析
 */
public class TuFileParse {

    //    private static ExecutorService executor = Executors.newFixedThreadPool(4);
    // 结果文件保存路径，可以从参数中传递
    private static String RES_FILE_PATH = "D:\\logs\\tu\\";
    // 当前位置
    private static int currIndex = 85;
    // 头部长度
    private static int HEAD_SIZE = 85;
    //
    private static String RES_FILE_SUFFIX = ".dat";
    // 分割符
    private static String DELIMITER = "|";
    // 报表日期
    private static String date;
    // TU文件名称
    private static String fileName;
    // 计数器（行号）
    private static AtomicInteger countNum = new AtomicInteger(0);
    // 模块名称: 一共5个模块，对应5个不同文件名称
    private static Map<String, String> MODULE_SEQ = new HashMap<>();
    // Segments：每1个文件格式一样，由7个组成。segments数组
    private static String[] SEGS = {"NA", "AL", "AD", "PH", "AC", "LM", "RI"};
    // 约定，每1个Segments对应一个编号
    private static Map<String, String> SEGMENT_SEQ = new HashMap<>();
    // 每一个Segments对应的字段(TU已经规定好格式，不能悠 )
    private static Map<String, String[]> SEGMENT_FIELD = new HashMap<>();
    private static String[] NA_LIST = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"};
    private static String[] AL_LIST = {"03", "05", "06", "07", "08", "09", "10"};
    private static String[] AD_LIST = {"01", "02", "03", "04"};
    private static String[] PH_LIST = {"01", "02", "03", "04", "05"};
    private static String[] AC_LIST = {"01", "02", "04", "05", "07", "08", "09", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "30", "32", "33", "34", "35"};
    private static String[] LM_LIST = {"01", "02"};
    private static String[] RI_LIST = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
    //结果
    private static Map<String, List<String>> result = new HashMap<>();

    static {
        MODULE_SEQ.put("cif088d.dat", "02");
        MODULE_SEQ.put("cif089rp.dat", "03");
        MODULE_SEQ.put("cif089rn.dat", "04");
        MODULE_SEQ.put("cif089r1.dat", "05");
        MODULE_SEQ.put("cif089r2.dat", "06");

        SEGMENT_SEQ.put("NA", "01");
        SEGMENT_SEQ.put("AL", "02");
        SEGMENT_SEQ.put("AD", "03");
        SEGMENT_SEQ.put("PH", "04");
        SEGMENT_SEQ.put("AC", "05");
        SEGMENT_SEQ.put("LM", "06");
        SEGMENT_SEQ.put("RI", "07");

        SEGMENT_FIELD.put("NA", NA_LIST);
        SEGMENT_FIELD.put("AL", AL_LIST);
        SEGMENT_FIELD.put("AD", AD_LIST);
        SEGMENT_FIELD.put("PH", PH_LIST);
        SEGMENT_FIELD.put("AC", AC_LIST);
        SEGMENT_FIELD.put("LM", LM_LIST);
        SEGMENT_FIELD.put("RI", RI_LIST);

        result.put("NA", new ArrayList<>());
        result.put("AL", new ArrayList<>());
        result.put("AD", new ArrayList<>());
        result.put("PH", new ArrayList<>());
        result.put("AC", new ArrayList<>());
        result.put("LM", new ArrayList<>());
        result.put("RI", new ArrayList<>());
    }

    public static void main(String[] args) throws IOException {
        fileName = args[0];
        date = args[1];
        TuFileParse fileDemo = new TuFileParse();
//        fileDemo.parseFile("D:\\IdeaProjects_community\\java-core-material\\core\\src\\main\\java\\com\\roboslyq\\io\\io\\cif089rn.dat");
        fileDemo.parseFile("D:\\IdeaProjects_community\\java-core-material\\core\\src\\main\\java\\com\\roboslyq\\io\\io\\cif088d.dat");
    }

    public void parseFile(String filePath) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(filePath)));
        String  completeStr = fileReader.readLine();
        if(Objects.isNull(completeStr) || completeStr.length() <= HEAD_SIZE + 2){
            System.out.println("文件长度小于85,请检查文件大小");
        }
        String srcdata = completeStr.substring(HEAD_SIZE);
        // 按每一笔Loan拆分，得到每一笔Loan的详情，包含7个segments
        String[] resArray = srcdata.split("ES02\\*\\*");
        Arrays.stream(resArray).forEach(loan -> parseLoan(loan, 0, ""));
        writeResult(this.result);
        System.out.println("finished");
    }

    /**
     * 解析每一笔Loan
     *
     * @param loan
     */
    public void parseLoan(String loan, int curIndex, String na_lineno) {
        if (curIndex == loan.length()){
            return;
        }
//      # the current segment name: NA,AL,AD,PH,AC,LM,RI
        String segName = loan.substring(curIndex, curIndex + 2);
        int curCountNum = countNum.getAndIncrement();
        int segLength = Integer.parseInt(loan.substring(curIndex + 2, curIndex + 4));
        String segmentLine = loan.substring(curIndex + 4, curIndex + 4 + segLength);
        String[] fileds = SEGMENT_FIELD.get(segName);
        StringBuilder sb = new StringBuilder();
        sb.append(date).append(DELIMITER).append(curCountNum).append(DELIMITER);
        if (segName.equals(SEGS[0])) {
            na_lineno = segmentLine;
        } else {
            sb.append(na_lineno).append(DELIMITER);
        }
        sb.append(segmentLine).append(DELIMITER);

        curIndex = curIndex + 4 + segLength;
        // 循环处理各种域
        for (String field : fileds) {
            if (curIndex == loan.length()) {
                break;
            }
            String fieldInLoan = loan.substring(curIndex, curIndex + 2);
            if (!fieldInLoan.equals(field)) {
                sb.append(DELIMITER);
            } else {
                int fieldLen = Integer.parseInt(loan.substring(curIndex + 2, curIndex + 2 + 2));
                if (fieldLen == 0) {
                    sb.append(DELIMITER);
                    curIndex += 4;
                } else {
                    String fieldValue = loan.substring(curIndex + 4, curIndex + 4 + fieldLen);
                    curIndex = curIndex + 4 + fieldLen;
                    sb.append(fieldValue).append(DELIMITER);
                }
            }
        }
        result.get(segName).add(sb.toString());
        //递归解析
        parseLoan(loan, curIndex, na_lineno);
    }

    public void writeResult(Map<String,List<String>> content) throws IOException {
        for(String seg :SEGS){
            List<String> list =  content.get(seg);
            fileWriter(seg,list);
        }
    }

    /**
     * 使用字符流
     */
    public void fileWriter(String seg, List<String> content) throws IOException {
        File file = new File(RES_FILE_PATH + getResultFileName(seg));
        file.deleteOnExit();
        boolean createNewFileResult = file.createNewFile();
        if (!createNewFileResult) {
            System.out.println("文件创建失败!!");
            return;
        }
        // 覆盖写
        // FileWriter out = new FileWriter(file);
        // 追加写
        FileWriter out = new FileWriter(file, true);
        for (String line : content) {
            out.write(line + "\n");
        }
        // 必须刷新,否则部分数据无法写回文件。
        // 注意,是部分数据,当达到缓冲区存储值,会自动 刷新缓冲区。
        out.flush();
    }

    /**
     * 根据seg获取结果文件路径
     * @param seg
     * @return
     */
    public String getResultFileName(String seg){
        return seg + "-" + MODULE_SEQ.get(fileName) + SEGMENT_SEQ.get(seg) + date + RES_FILE_SUFFIX;
    }
}
